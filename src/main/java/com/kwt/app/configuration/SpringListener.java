package com.kwt.app.configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

@WebListener
public class SpringListener extends ContextLoaderListener {
	 @Override
	    public void contextInitialized(ServletContextEvent event) {
	        event.getServletContext().setInitParameter(ContextLoader.CONTEXT_CLASS_PARAM, "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
	        event.getServletContext().setInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, "com.kwt.app.configuration.AppConfigContext");
	        super.contextInitialized(event);
	    }
}
