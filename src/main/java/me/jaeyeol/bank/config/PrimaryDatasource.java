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
import org.springframework.context.annotation.Primary;

@Configuration
//@MapperScan(value = "mappers.xml")
//@MapperScan(basePackages = "me.jaeyeol.bank.dao1.IAcctDAO1", sqlSessionFactoryRef="db1SqlSessionFactory")
public class PrimaryDatasource {
	
	@Primary
	@Bean(name="db1DataSource")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name="db1SqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("db1DataSource") DataSource db1DataSource, ApplicationContext applicationContext ) throws Exception {
		
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		
		sessionFactory.setDataSource(db1DataSource);
//		sessionFactory.setTypeAliasesPackage("me.jaeyeol.bank.dto.AcctMapper.xml, me.jaeyeol.bank.dto.HistMapper.xml"); 
//		sessionFactory.setConfigLocation(applicationContext.getResource("classpath:META-INF/mybatis/mybatis-config.xml"));
		sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mappers/xml/*.xml"));  //TODO
	        return sessionFactory.getObject();
		
	}
	
	@Primary
	@Bean(name="db1SqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("db1SqlSessionFactory")SqlSessionFactory db1SqlSessionFactory) throws Exception{
	        return new SqlSessionTemplate(db1SqlSessionFactory);
	    }
	 
//   @Bean(name = "db1transactionManager")
//	@Primary
//    public PlatformTransactionManager transactionManager(@Qualifier("db1DataSource") DataSource db1DataSource) {
//        return new DataSourceTransactionManager(db1DataSource);
//    }
	
}
