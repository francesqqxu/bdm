package com.etop.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.etop.basic.controller.BaseController;
import com.etop.pojo.User;
import com.etop.service.UserService;
import com.etop.utils.VerifyCode;

/**
 * @类名: HomeController
 * @描述: 处理用户登录登出的控制器
 * @作者 frances.xu@huateng.com
 * @日期 2016年1月20日 
 */
@Controller
public class HomeController extends BaseController {
	private final static Logger log = Logger.getLogger(HomeController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login.html", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String loginForm(Model model,@ModelAttribute("message") String message, HttpServletRequest request) {
		if (!StringUtils.isEmpty(message))
			model.addAttribute(message);
		HttpSession session = request.getSession();
		model.addAttribute("message", session.getAttribute("message"));
		session.removeAttribute("message");
		model.addAttribute("user", new User());
		System.out.println("login_1");
		System.out.println("model:" + model);
		System.out.println("message:" + message);
		log.info("login.html");
		return "/login";
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public String login(@Valid User user, BindingResult bindingResult,
			Model model, RedirectAttributes attr, HttpServletRequest request) {
		HttpSession session = request.getSession();
		// session为空时跳转到登录界面
		if (session.getAttribute(VerifyCode.VERIFY_TYPE_COMMENT) == null
				|| session.getAttribute(VerifyCode.VERIFY_TYPE_COMMENT) == "") {
			addMessage(attr, "session为空");
			log.info("session为空");
			return "redirect:/";
		}
		String code = ((String) request.getSession().getAttribute(
				VerifyCode.VERIFY_TYPE_COMMENT)).toLowerCase();
		String submitCode = WebUtils.getCleanParam(request, "checkcode");
		if (StringUtils.isEmpty(code) || StringUtils.isEmpty(submitCode)
				|| !StringUtils.equals(code, submitCode.toLowerCase())) {
			addMessage(attr, "验证码错误");
			session.setAttribute("message", "验证码错误");
			log.info("验证码错误");
			return "redirect:/";
		}
		try {
			if (bindingResult.hasErrors() || user.getUsername().isEmpty()
					|| user.getPassword().isEmpty()) {
				addMessage(attr, "用户名或密码错误");
				session.setAttribute("message", "用户名或密码错误");
				log.info("用户名或密码错误");
				return "redirect:/login.html";
			}
			System.out.println("管理用户登录");
			// 使用shiro管理登录
			SecurityUtils.getSubject().login(
					new UsernamePasswordToken(user.getUsername(), user
							.getPassword()));
			// 获取所有用户信息，权限由前端shiro标签控制
			List<User> userList = userService.getAllUser();
			System.out.println(userList);
			model.addAttribute("userList", userList);
			user = userService.findByName(user.getUsername());
			model.addAttribute("user", user);
			session.setAttribute("user", user);
			System.out.println("管理用户登录success");
			log.info("成功登录");
			System.out.println("密码:======================"+user.getPassword());
			return "/mainBoard";
		} catch (AuthenticationException e) {
			addMessage(attr, "用户名或密码错误");
			session.setAttribute("message", "用户名或密码错误");
			log.info("用户名或密码错误");
			return "redirect:/login.html";
		}
	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String logout(RedirectAttributes attr) {
		// 使用权限管理工具进行用户的退出，注销登录
		SecurityUtils.getSubject().logout();
		addMessage(attr, "您已安全退出");
		log.info("安全退出");
		return "redirect:/login.html";
	}

	@RequestMapping("/403.html")
	//@ResponseBody
	public String unauthorizedRole() {
		log.info("跳转到403页面");
		return "403";
	}

	@RequestMapping("/verifyCode.html")
	public void verifyCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 生成验证码，写入用户session
		String verifyCode = VerifyCode.generateTextCode(
				VerifyCode.TYPE_NUM_UPPER, 4, "null");
		request.getSession().setAttribute(VerifyCode.VERIFY_TYPE_COMMENT,
				verifyCode);
		System.out.println("verifyCode=" + verifyCode);
		// 输出验证码给客户端
		response.setContentType("image/jpeg");
		/*
		 * textCode 文本验证码 width 图片宽度 height 图片高度 interLine 图片中干扰线的条数
		 * randomLocation 每个字符的高低位置是否随机 backColor 图片颜色，若为null，则采用随机颜色 f
		 * oreColor字体颜色，若为null，则采用随机颜色 lineColor 干扰线颜色，若为null，则采用随机颜色
		 */
		BufferedImage bim = VerifyCode.generateImageCode(verifyCode, 65, 22, 8,
				true, Color.WHITE, Color.BLACK, null);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bim, "JPEG", out);
		log.info("验证码已经生成并存入session中");
		try {
			out.flush();
			log.info("刷新验证码图片");
		} finally {
			out.close();
			log.info("关闭验证码流");
		}
	}
}