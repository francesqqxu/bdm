package com.etop.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
	
	
	public static boolean isAjax(HttpServletRequest  request) {
		
		 if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
		            .getHeader("X-Requested-With")!= null && request  
		            .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {  
			 return true;
		 }
		 else {
			 return false;
		 }
	}
	
	public static void sendJson(HttpServletResponse response, String jsonString) throws  IOException{
		  response.setContentType("text/html; charset=utf-8");
		  PrintWriter out = response.getWriter();
		  out.print(jsonString);
		  out.flush();
		  out.close();
		
	}
	
	public static void  issueRedirect(HttpServletRequest request, HttpServletResponse response, String unauthorizedUrl){
		
		
		
	}
	

}
