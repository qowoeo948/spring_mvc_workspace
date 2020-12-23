package test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class TestController implements Controller{
	private Student student;
	private Dog dog;
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setDog(Dog dog) {
		this.dog = dog;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		student.study1();
		student.study2();
		student.study3();
		student.study4();
		 * */
		dog.haveFood1();
		dog.haveFood2();
		dog.haveFood3();
		
		return null;
	}

}
