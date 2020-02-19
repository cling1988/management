package com.kwt.app;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.kwt.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return LoginPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		// add your configuration here
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext ();
		ctx.scan("com.kwt.app");
//		ctx.scan("com.kwt.app.configuration");
//		ctx.scan("com.kwt.app.service");
	    ctx.refresh();

	    getComponentInstantiationListeners().add(new SpringComponentInjector(this,ctx));
	}
}
