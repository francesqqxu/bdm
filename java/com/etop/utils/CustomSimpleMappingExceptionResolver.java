package com.etop.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import net.sf.json.JSONObject;

public class CustomSimpleMappingExceptionResolver extends  
SimpleMappingExceptionResolver {  

@Override  
protected ModelAndView doResolveException(HttpServletRequest request,  
    HttpServletResponse response, Object handler, Exception ex) {  
	
// Expose ModelAndView for chosen error view.  
String viewName = determineViewName(ex, request);  
if (viewName != null) {// JSP格式返回  
    if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
            .getHeader("X-Requested-With")!= null && request  
            .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {  
        // 如果不是异步请求  
        // Apply HTTP status code for error views, if specified.  
        // Only apply it if we're processing a top-level request.  
        Integer statusCode = determineStatusCode(request, viewName);  
        if (statusCode != null) {  
            applyStatusCodeIfPossible(request, response, statusCode);  
        }  
        return getModelAndView(viewName, ex, request);  
    } else {// JSON格式返回  
        try {  
            response.setContentType("charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");       	
            PrintWriter writer = response.getWriter();  
            JSONObject json = new JSONObject();
//            json.put("total", 0);
//            json.put("rows", "[ ]");
            json.put("success", false);
            json.put("msg", ex.getMessage());
        
            writer.write(json.toString());
            writer.flush();  
            
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return  new ModelAndView();
       
    }  
} else {  
    return null;  
}  
}  
}  