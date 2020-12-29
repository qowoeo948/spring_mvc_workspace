package com.koreait.mylegacy.controller.emp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.mylegacy.model.domain.Dept;
import com.koreait.mylegacy.model.domain.Emp;
import com.koreait.mylegacy.model.service.JdbcEmpService;
import com.koreait.mylegacy.model.service.MybatisEmpService;

//component-scan대상이 되려면 어노테이션을 지정해야 한다

@Controller
public class EmpController {
	private static final Logger logger=LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	private JdbcEmpService empService;
	
	@Autowired
	private MybatisEmpService mybatisEmpService;

	
	//사원등록 폼요청
	@RequestMapping("/emp/registform")
	public String registForm() {
		//저장할 것이 없고, 그냥 뷰만 반환하고싶을때는 String도 가능 
		return "emp/regist_form";
	}
	
	//사원등록 요청을 처리하는 메서드
	@RequestMapping(value="/emp/regist", method=RequestMethod.POST)
	public String regist(Dept dept, Emp emp) {
		//파라미터 받아와 출력해보기!!
		//log4j라는 라이브러리는 우리가 개발시 디버그목적으로 사용하는 콘솔 출력보다도 훨씬 다양하며 
		//복잡한 기능을 지원하는 로그 라이브러리이다. 
		//특히 출력 로그 레벨이라는 기능을 둬서, 개발자가 원하는 레벨을 선택하여 출력을 제어할 수 있도록 지원
		// ALL(모든로깅) < DEBUG(확인) < INFO(강조) < WARN(경고) < ERROR(오류) < FATAL(심각한 오류) < OFF(로깅해제)
		
		//WARN 수준으로 세팅
		logger.debug(""+dept.getDeptno());
		logger.debug(dept.getDname());
		logger.debug(dept.getLoc());
		
		logger.info(""+emp.getEmpno());
		logger.debug(emp.getEname());
		logger.debug(""+emp.getSal());
		
		//System.out.println(""+emp.getDept().getDeptno());
		
		//DB에 등록!!!
		//부서등록과 사원등록이라는 두개의 업무가 모두 성공되어야, 전체를 성공으로 간주하는 트랜잭션 상황!!!
		
		//서비스에게 사원등록 요청!!!
		emp.setDept(dept); //emp와 부서를 합체!!!
		
		int result = mybatisEmpService.regist(emp);
		logger.debug("등록결과 "+result);
		
		return "redirect:/emp/list";
	}
	
	//사원목록
	@RequestMapping(value="/emp/list", method=RequestMethod.GET)
	public ModelAndView selectAll() {
		ModelAndView mav = new ModelAndView();
			
		//3단계 일시킨다 
		List empList = mybatisEmpService.selectAll();
		logger.debug("empList : "+empList);
		
		//4단계: 저장
		mav.addObject("empList", empList);
		mav.setViewName("emp/list");
		
		return mav;
	}
}




