package com.qs.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ReturnInfo {
	 public static String send(HttpServletResponse response,String result){		 		 		 		 		 
		 response.setContentType("application/json");
			try {
				PrintWriter out = response.getWriter();			  
				out.write(result);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		return null;		 
	 }
}
