package com.nibbledebt.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UrlRewriteFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getRequestURI();
		if(path.startsWith("/tos") ){
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect("https://uat.dwolla.com"+path);
			return;
		}
		if ( path.startsWith("/Content") || path.startsWith("/distil_identify_cookie.html") || path.startsWith("/ga") || path.startsWith("/Fi") || path.startsWith("/public")) {
			request.getRequestDispatcher("/dwolla" + path).forward(request, response);
		} else {
			chain.doFilter(request, response); // Do your business stuff here for all paths other than /specialpath.
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
