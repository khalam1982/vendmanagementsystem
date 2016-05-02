/**
 * VMSWebActionFilter.java
 * Purpose: VMS web action filter servlet
 * @author ramasap1
 */
package com.centrica.vms.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

/**
 * Methods for VMS web action filter servlet
 * @see Filter
 */
public class VMSWebActionFilter implements Filter {

	private Logger logger = ESAPI.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering doFilter method");
		String servletPath = ((HttpServletRequest) servletRequest)
				.getServletPath();
		logger.info(Logger.EVENT_UNSPECIFIED,"servletPath " + servletPath);
		if (!servletPath.equals("/services")
				&& !servletPath.equals("/VMSHome")&& !servletPath.equals("/Welcome.jsp")) {
			HttpSession session = ((HttpServletRequest) servletRequest)
					.getSession(false);
			if (session != null && !session.isNew()) {
				handleServletRequest(servletRequest, servletResponse, chain,servletPath);
			} else {
				/* Unit test servlet */
				if(servletPath.equals("/VendMgmtSysTest") || servletPath.equals("/SchedulerTest")){
					chain.doFilter(servletRequest, servletResponse);
				}
				logger.info(Logger.EVENT_UNSPECIFIED,"User has not logged in");
			}

		}else if(servletPath.equals("/services")){
			chain.doFilter(servletRequest, servletResponse);
		}else{
			handleServletRequest(servletRequest, servletResponse, chain,servletPath);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving doFilter method");

	}

	/**
	 * Method to validate the method and scheme used in the request.
	 * @param servletRequest
	 * @param servletResponse
	 * @param chain
	 * @param servletPath
	 * @throws IOException
	 * @throws ServletException
	 */
	private void handleServletRequest(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain,String servletPath)
			throws IOException, ServletException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering handleServletRequest method");
		//CEN_13 start
		if(servletRequest.getScheme().equals("https")){ //CEN_13 end
			//CEN_13 start
			if(servletPath.contains(".html") || servletPath.contains(".gif") || servletPath.contains(".js") || servletPath.contains(".png") || servletPath.contains(".css") || servletPath.equals("/MenuPage") || servletPath.equals("/Welcome.jsp")
					|| servletPath.equals("/black") || servletPath.equals("/TitlePage.jsp") || servletPath.contains(".bmp") || isValidMethod(((HttpServletRequest) servletRequest).getMethod())){
				chain.doFilter(servletRequest, servletResponse);	
			}else{
				logger.info(Logger.EVENT_UNSPECIFIED,"Unsupported method");
			}
			//CEN_13 end
		}else{ //CEN_13 start
			logger.info(Logger.EVENT_UNSPECIFIED,"Unsupported scheme");
		} //CEN_13 end
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving handleServletRequest method");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {

		// TODO Auto-generated method stub

	}
	
	private Boolean isValidMethod(String method){
		logger.debug(Logger.EVENT_SUCCESS,"Entering isValidMethod method");
		Boolean isValid = Boolean.FALSE;
		if(method.equals("POST")){
			isValid = Boolean.TRUE;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving isValidMethod method");
		return isValid;
	}

}
