/**
 * StubInitializerServlet.java
 * Purpose: Stub initialization servlet
 * @author ramasap1
 */
package com.centrica.vms.servlet;

import javax.servlet.http.HttpServlet;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

/**
 * Methods for Stub initialization servlet
 * @see HttpServlet
 */
public class StubInitializerServlet extends HttpServlet {
	
	private Logger logger = ESAPI.getLogger(getClass().getName());

	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init(){
		
	}

}
