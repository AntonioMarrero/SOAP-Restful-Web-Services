package com.revature.soap.webservices.soapcoursemanagement.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.revature.courses.CourseDetails;
import com.revature.courses.GetCourseDetailsRequest;
import com.revature.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoint {
	
	//method
	//input - GetCourseDetailsRequest
	//output - GetCourseDetailsResponse
	//http://revature.com/courses
	//GetCourseDetailsRequest
	
	@PayloadRoot(namespace="http://revature.com/courses",
			localPart="GetCourseDetailRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest
		(@RequestPayload GetCourseDetailsRequest request) {
		
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(request.getId());
		courseDetails.setName("Microservices Course");
		courseDetails.setDescription("That would be a wonderful course!");		
		
		response.setCourseDetails(courseDetails );
		
		return response;
	}
}
