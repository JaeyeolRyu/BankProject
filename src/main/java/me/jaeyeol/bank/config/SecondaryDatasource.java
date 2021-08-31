package me.jaeyeol.bank.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@MapperScan(value = "mappers.xmlTwo")
//@MapperScan(basePackages="me.jaeyeol.bank.dao2.IAcctDAO2",sqlSessionFactoryRef="db2SqlSessionFactory")
public class SecondaryDatasource {

	@Bean(name="db2DataSource")
	@ConfigurationProperties(prefix="spring.datasource2")
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="db2SqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("db2DataSource") DataSource db2DataSource, ApplicationContext applicationContext ) throws Exception {
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		
		sessionFactory.setDataSource(db2DataSource);
		sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mappers/xmlTwo/*.xml"));
	        return sessionFactory.getObject();
		
	}
	
	@Bean(name="db2SqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory db2sqlSessionFactory) {
	        return new SqlSessionTemplate(db2sqlSessionFactory);
	    }
	 
//	@Bean(name="db2transactionManager")
//	public PlatformTransactionManager transactionManager( @Qualifier("db2DataSource") DataSource db2DataSource) {
//	    return new DataSourceTransactionManager(db2DataSource);
//	}
	
}
