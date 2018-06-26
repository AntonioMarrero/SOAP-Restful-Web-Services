package com.revature.soap.webservices.soapcoursemanagement.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable Spring Web Services
@EnableWs
//Spring Configuration
@Configuration
public class WebServiceConfig {

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
}
