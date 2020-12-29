package test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * ���ϰ� ���� ��û�� �����⵵ ���� ������ �� ������ �����ϴ� ������ �̺�Ʈ��
 * ������ �� �ִ� ServletContextListener�� �̿��Ͽ�, ServletContext��ü��
 * ���𰡸� �۾��غ���!!
 * */
public class MyContextListener implements ServletContextListener{

	@Override
	//���ø����̼��� �ʱ�ȭ �� ��.. ȣ��Ǵ� �޼���
	public void contextInitialized(ServletContextEvent sc) {
		System.out.println("���ø����̼��� ���� ���۰� �Բ� �����ƾ�~");
		//���ø����̼��� ������ ������ ����, ���Ǻ��ٵ� �ξ� ���� ��ư��� ServletContext��
		//����Ͻ� ��ü��(service,dao���)�� ����
		ServletContext context = sc.getServletContext();
		context.setAttribute("msg", "�� �������");
		
		String obj = context.getInitParameter("contextConfigLocation");
		System.out.println("���ø����̼� ������ �� �޸𸮿� �÷��� ����� "+obj);
	}
	@Override
	//���ø����̼��� ���� �ɶ�.. ȣ��Ǵ� �޼���
	public void contextDestroyed(ServletContextEvent sc) {
		System.out.println("���ø����̼��� ���� ���۰� �Բ� ����ƾ�~");
	}

}
