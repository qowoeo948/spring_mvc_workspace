package com.koreait.bootgradle;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration //spring bean��� �� �����ϴ� Ŭ����
public class AppConfig {
	@Autowired
	private ApplicationContext applicationContext;
	
	
	//mybatis SqlSessionFactory ���� 
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactory =  new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource); //mybatis�� mysql�� ����ϰԵ�..
		sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:com/koreait/bootgradle/mybatis/config/config.xml"));//mybatis ���������� ��ġ
		return sqlSessionFactory.getObject();
	}
	
	//������ ���� ��ü�� SqlSessionTemplate ���� 
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	//hibernate, jpa, jdbc, mybatis ��� ��� Ʈ����� �������� �ֻ��� ��ü�� ����غ���
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}



