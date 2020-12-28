package com.koreait.mylegacy.controller.emp;

import java.util.List;

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
		
		System.out.println(""+dept.getDeptno());
		System.out.println(dept.getDname());
		System.out.println(dept.getLoc());
		
		System.out.println(""+emp.getEmpno());
		System.out.println(emp.getEname());
		System.out.println(""+emp.getSal());
		//System.out.println(""+emp.getDept().getDeptno());
		
		//DB�� ���!!!
		//�μ���ϰ� �������̶�� �ΰ��� ������ ��� �����Ǿ��, ��ü�� �������� �����ϴ� Ʈ����� ��Ȳ!!!
		
		//���񽺿��� ������ ��û!!!
		emp.setDept(dept); //emp�� �μ��� ��ü!!!
		
		int result = empService.regist(emp);
		System.out.println("��ϰ�� "+result);
		
		return "redirect:/emp/list";
	}
	
	//������
	@RequestMapping(value="/emp/list",method=RequestMethod.GET)
	public ModelAndView selectAll() {
		ModelAndView mav = new ModelAndView();
		
		//3�ܰ� �Ͻ�Ų��.
		//�׳� jdbc���� �Ѱ�
		//List empList =empService.selectAll();
		//mybatis�� �Ѱ�
		List empList =mybatisEmpService.selectAll();
		
		//4�ܰ� ����
		mav.addObject("empList",empList);
		mav.setViewName("emp/list");
		
		return mav;
	}
}