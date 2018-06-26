package com.revature.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.bean.Course;

@Component
public class CourseDetailsService {
	
	private static List<Course> courses = new ArrayList<>();
	
	
	static {
		Course course1 = new Course(1, "Srping", "10 Steps");
		courses.add(course1);
		
		Course course2 = new Course(2, "Srping MVC", "10 Examples");
		courses.add(course2);
		
		Course course3 = new Course(3, "Srping Boot", "6k Students");
		courses.add(course3);
		
		Course course4 = new Course(4, "Maven", "Most popular maven course");
		courses.add(course4);
	}
	
	
	//course -1
	public Course findById(int id) {
		for(Course course : courses) {
			if(course.getId()==id)
				return course;
		}
		return null;
	}
	
	//courses
	public List<Course> findAll() {
		return courses;
	}
	
	//deletecourse
	public int deleteById(int id) {
		
		Iterator<Course> iterator = courses.iterator();
		while(iterator.hasNext()) {
			Course course = iterator.next();
			if(course.getId()==id) {
				iterator.remove();
				return 1;
			}
		}
		return 0;
	}
	
	//updating course & new course
	
	

}
