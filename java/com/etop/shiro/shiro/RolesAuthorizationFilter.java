package com.etop.shiro;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.apache.shiro.util.CollectionUtils;

import com.etop.utils.JsonTools;
import com.etop.utils.ViewResult;



public class RolesAuthorizationFilter extends AuthorizationFilter {
	
	
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {  
		  
        HttpServletRequest httpRequest = (HttpServletRequest) request;  
        HttpServletResponse httpResponse = (HttpServletResponse) response;  
  
        Subject subject = getSubject(request, response);  
  
        if (subject.getPrincipal() == null) {  
            if (com.etop.utils.WebUtils.isAjax(httpRequest)) {  
            	com.etop.utils.WebUtils.sendJson(httpResponse, JsonTools.toJsonString(new ViewResult("false",
                        "您尚未登录或登录时间过长,请重新登录!")));  
            } else {  
                saveRequestAndRedirectToLogin(request, response);  
            }  
        } else {  
            if (com.etop.utils.WebUtils.isAjax(httpRequest)) {  
            	com.etop.utils.WebUtils.sendJson(httpResponse, JsonTools.toJsonString( new ViewResult("false",  
                        "您没有足够的权限执行该操作!")));  
            } else {  
                String unauthorizedUrl = getUnauthorizedUrl();  
                if (StringUtils.hasText(unauthorizedUrl)) {  
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);  
                } else {  
                    WebUtils.toHttp(response).sendError(401);  
                }  
            }  
        }  
        return false;  
    }  
	 public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)  
	            throws IOException {  
	  
	        Subject subject = getSubject(request, response);  
	        String[] rolesArray = (String[]) mappedValue;  
	  
	        if (rolesArray == null || rolesArray.length == 0) {  
	            // no roles specified, so nothing to check - allow access.  
	            return true;  
	        }  
	  
	        Set<String> roles = CollectionUtils.asSet(rolesArray);  
	        for (String role : roles) {  
	            if (subject.hasRole(role)) {  
	                return true;  
	            }  
	        }  
	        return false;  
	    }  
	  
}
