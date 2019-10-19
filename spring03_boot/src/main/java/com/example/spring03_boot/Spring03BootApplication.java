package com.example.spring03_boot;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// 스프링 부트 애플리케이션 빈 (시작클래스)
@SpringBootApplication
// mapper 자동스캔
@MapperScan("com.example.spring03_boot.model")
public class Spring03BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring03BootApplication.class, args);
	}
	
	// javax.sql.DataSource
	// 의존관계: DataSource --> SqlSessionFactory --> SqlSessionTemplate --> SqlSession
	@Bean //자바코드로 Bean을 등록
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		
		// 데이터 소스 설정(application.properties 참조)
		bean.setDataSource(dataSource);
		
		// xml mapper를 사용할 경우 아래 코드 추가
		// import org.springframework.core.io.Resource;
		// Resource[] res = new PathMatchingResourcePatternResolver()
		// .getResources("classpath:mappers/*Mapper.xml");
		// bean.setMapperLocations(res);
		
		return bean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory factory) {
		return new SqlSessionTemplate(factory);
	}
}
