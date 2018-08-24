package com.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.common.tool.SystemContext;

public class SessionCounter implements HttpSessionListener {
	
	private static int activeSessions =0;

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		ServletContext ctx = event.getSession( ).getServletContext( );  
		HttpSession session = event.getSession();
        Integer numSessions = (Integer) ctx.getAttribute("numSessions");  
        if (numSessions == null) {  
            numSessions = new Integer(1);  
        }  
        else {  
            int count = numSessions.intValue( );  
            numSessions = new Integer(count + 1);  
        }  
        ctx.setAttribute("numSessions", numSessions);
        System.out.println("静态资源路径："+SystemContext.getProperty("static.path"));
        session.setAttribute("staticPath", SystemContext.getProperty("static.path"));
        

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext ctx=se.getSession().getServletContext();  
		 Integer numSessions = (Integer)ctx.getAttribute("numSessions");  
		if(numSessions == null)  {
		            numSessions = new Integer(0);  
		        }  
		        else {  
		            int count = numSessions.intValue( );  
		            numSessions = new Integer(count - 1);  
		        }  
		        ctx.setAttribute("numSessions", numSessions);

	}

}
