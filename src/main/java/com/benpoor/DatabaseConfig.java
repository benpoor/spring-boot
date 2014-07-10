package com.benpoor;

import com.benpoor.common.PageInterceptor;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author shensw
 * @version 1.0.0
 * @Description
 * @date 2014/7/10.16:25
 */
@Configuration
@EnableConfigurationProperties({ConnectionSettings.class})
@MapperScan(basePackages="com.benpoor.persistence"/*, sqlSessionFactoryRef="mySessionFactory"*/)
public class DatabaseConfig {
    @Autowired
    private ConnectionSettings connectionSettings;

    @Autowired
    private DataSource dataSource;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(connectionSettings.getDriver());
//        dataSource.setUrl(connectionSettings.getUrl());
//        dataSource.setUsername(connectionSettings.getUsername());
//        dataSource.setPassword(connectionSettings.getPassword());

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/huajiemu");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean/*(name="mySessionFactory")*/
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        //设置数据源
        sessionFactory.setDataSource(dataSource());

        //设置拦截器属性
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("pageSqlId", ".*Page$");
        sessionFactory.setConfigurationProperties(properties);

        //设置拦截器
        PageInterceptor pageInterceptor = new PageInterceptor();

        PageInterceptor[] interceptors = new PageInterceptor[1];
        interceptors[0] = pageInterceptor;
        sessionFactory.setPlugins(interceptors);

        //设置别名
        sessionFactory.setTypeAliasesPackage("com.benpoor.model");
        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
