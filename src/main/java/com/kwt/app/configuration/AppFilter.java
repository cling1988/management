package com.kwt.app.configuration;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.apache.wicket.protocol.http.WicketFilter;


@WebFilter(value = "/*", initParams = {
		@WebInitParam(name = "applicationClassName", value = "com.kwt.app.WicketApplication"),
		@WebInitParam(name = "filterMappingUrlPattern", value = "/*")
})
public class AppFilter extends WicketFilter {

}
