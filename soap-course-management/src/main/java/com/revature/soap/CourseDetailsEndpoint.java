package com.revature.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.revature.bean.Course;
import com.revature.courses.CourseDetails;
import com.revature.courses.GetCourseDetailsRequest;
import com.revature.courses.GetCourseDetailsResponse;
import com.revature.service.CourseDetailsService;

@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService service;
	
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
		
		Course course = service.findById(request.getId());
		
		return mapCourse(course);
	}

	private GetCourseDetailsResponse mapCourse(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		CourseDetails courseDetails = new CourseDetails();
		
		courseDetails.setId(course.getId());
		
		courseDetails.setName(course.getName());
		
		courseDetails.setDescription(course.getDescription());		
		
		response.setCourseDetails(courseDetails );
		
		return response;
	}
}
