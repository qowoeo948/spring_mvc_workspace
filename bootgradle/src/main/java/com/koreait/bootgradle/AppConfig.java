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

@Configuration //spring bean등록 및 관리하는 클래스
public class AppConfig {
	@Autowired
	private ApplicationContext applicationContext;
	
	
	//mybatis SqlSessionFactory 세팅 
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactory =  new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource); //mybatis가 mysql을 사용하게됨..
		sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:com/koreait/bootgradle/mybatis/config/config.xml"));//mybatis 설정파일의 위치
		return sqlSessionFactory.getObject();
	}
	
	//쿼리문 수행 객체인 SqlSessionTemplate 설정 
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	//hibernate, jpa, jdbc, mybatis 등등 모든 트랜잭션 관리자의 최상위 객체를 사용해보자
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}



