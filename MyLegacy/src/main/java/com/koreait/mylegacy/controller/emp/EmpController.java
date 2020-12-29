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

//component-scan����� �Ƿ��� ������̼��� �����ؾ� �Ѵ�

@Controller
public class EmpController {
	private static final Logger logger=LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	private JdbcEmpService empService;
	
	@Autowired
	private MybatisEmpService mybatisEmpService;

	
	//������ ����û
	@RequestMapping("/emp/registform")
	public String registForm() {
		//������ ���� ����, �׳� �丸 ��ȯ�ϰ�������� String�� ���� 
		return "emp/regist_form";
	}
	
	//������ ��û�� ó���ϴ� �޼���
	@RequestMapping(value="/emp/regist", method=RequestMethod.POST)
	public String regist(Dept dept, Emp emp) {
		//�Ķ���� �޾ƿ� ����غ���!!
		//log4j��� ���̺귯���� �츮�� ���߽� ����׸������� ����ϴ� �ܼ� ��º��ٵ� �ξ� �پ��ϸ� 
		//������ ����� �����ϴ� �α� ���̺귯���̴�. 
		//Ư�� ��� �α� �����̶�� ����� �ּ�, �����ڰ� ���ϴ� ������ �����Ͽ� ����� ������ �� �ֵ��� ����
		// ALL(���α�) < DEBUG(Ȯ��) < INFO(����) < WARN(���) < ERROR(����) < FATAL(�ɰ��� ����) < OFF(�α�����)
		
		//WARN �������� ����
		logger.debug(""+dept.getDeptno());
		logger.debug(dept.getDname());
		logger.debug(dept.getLoc());
		
		logger.info(""+emp.getEmpno());
		logger.debug(emp.getEname());
		logger.debug(""+emp.getSal());
		
		//System.out.println(""+emp.getDept().getDeptno());
		
		//DB�� ���!!!
		//�μ���ϰ� �������̶�� �ΰ��� ������ ��� �����Ǿ��, ��ü�� �������� �����ϴ� Ʈ����� ��Ȳ!!!
		
		//���񽺿��� ������ ��û!!!
		emp.setDept(dept); //emp�� �μ��� ��ü!!!
		
		int result = mybatisEmpService.regist(emp);
		logger.debug("��ϰ�� "+result);
		
		return "redirect:/emp/list";
	}
	
	//������
	@RequestMapping(value="/emp/list", method=RequestMethod.GET)
	public ModelAndView selectAll() {
		ModelAndView mav = new ModelAndView();
			
		//3�ܰ� �Ͻ�Ų�� 
		List empList = mybatisEmpService.selectAll();
		logger.debug("empList : "+empList);
		
		//4�ܰ�: ����
		mav.addObject("empList", empList);
		mav.setViewName("emp/list");
		
		return mav;
	}
}




