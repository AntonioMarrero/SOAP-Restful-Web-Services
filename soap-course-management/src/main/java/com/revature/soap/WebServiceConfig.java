package com.revature.soap;

import java.util.Collections;
import java.util.List;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable Spring Web Services
@EnableWs
//Spring Configuration
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{

	//MessageDispatcherServlet
		//ApplicationContext
	//url -> /ws/*	
	@Bean
	public ServletRegistrationBean messageDispatcherSevlet(ApplicationContext context) {				
		MessageDispatcherServlet messageDispatcherSevlet = new MessageDispatcherServlet();
		messageDispatcherSevlet.setApplicationContext(context);
		messageDispatcherSevlet.setTransformWsdlLocations(true);		
		return new ServletRegistrationBean(messageDispatcherSevlet, "/ws/*");
	}
	
	// /ws/course.wsdl		
	// course-details.xsd
	@Bean(name="courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();		
		//PortType - CoursePort
		definition.setPortTypeName("CoursePort");
		//Namespace - http://revature.com/courses
		definition.setTargetNamespace("http://revature.com/courses");
		// /ws
		definition.setLocationUri("/ws");
		//schema
		definition.setSchema(coursesSchema);
		
		return definition;
	}
	
	@Bean
	public XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}
	
	@Bean
	public XwsSecurityInterceptor securityInterceptor() {
		XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
		//Callback Handler -> SimplePasswordValidationCallbackHandler
		securityInterceptor.setCallbackHandler(callbackHandler());
		//Security Policy -> securityPolicy.xml
		securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));	
		return securityInterceptor;
	}
	
	private SimplePasswordValidationCallbackHandler callbackHandler() {
		
		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
		
		handler.setUsersMap(Collections.singletonMap("user", "password"));
		
		return handler;
	}

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}

	
	//XwsSecurityInterceptor		
	//Interceptors.add -> XwsSecurityInterceptor
	
}
