package com.office.common;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * spring boot 集成 mybatis 的基本接入口
 * 	1)创建数据源
 * 	2)创建SqlSessionFactory
 * @author Administrator
 *
 */
@Configuration
@MapperScan(basePackages="com.office.mapper")
public class MyBatisConfig {

	@Autowired
	private Environment env;
	
	/**
	 * 创建数据源
	 * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错 
	 * @return
	 * @throws Exception
	 */
	@Bean
	public DataSource getDataSource() throws Exception{
		Properties props=new Properties();
		props.put("driverClassName", env.getProperty("jdbc.driverClassName"));
		props.put("url", env.getProperty("jdbc.url"));
		props.put("username", env.getProperty("jdbc.username"));
		props.put("password", env.getProperty("jdbc.password"));
		return DruidDataSourceFactory.createDataSource(props);
	}
	
	
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{
		SqlSessionFactoryBean sfb=new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource);//指定数据源（这个必须有，否则报错）
		//下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
		sfb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
		sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置
		return sfb.getObject();
	}
}
