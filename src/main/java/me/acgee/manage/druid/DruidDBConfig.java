package me.acgee.manage.druid;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidDBConfig {
    private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);
    @Autowired
    private DruidConfig dc;
    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(dc.getUrl());
        datasource.setUsername(dc.getUsername());
        datasource.setPassword(dc.getPassword());
        datasource.setDriverClassName(dc.getDriverClassName());

        //configuration
        datasource.setInitialSize(dc.getInitialSize());
        datasource.setMinIdle(dc.getMinIdle());
        datasource.setMaxActive(dc.getMaxActive());
        datasource.setMaxWait(dc.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(dc.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(dc.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(dc.getValidationQuery());
        datasource.setTestWhileIdle(dc.isTestWhileIdle());
        datasource.setTestOnBorrow(dc.isTestOnBorrow());
        datasource.setTestOnReturn(dc.isTestOnReturn());
        datasource.setPoolPreparedStatements(dc.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(dc.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(dc.getFilters());
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter : {0}", e);
        }
        datasource.setConnectionProperties(dc.getConnectionProperties());
        return datasource;
    }
    @Bean 
    public ServletRegistrationBean druidServlet() { 
      ServletRegistrationBean reg = new ServletRegistrationBean(); 
      reg.setServlet(new StatViewServlet()); 
      reg.addUrlMappings("/druid/*"); 
      //reg.addInitParameter("allow", "127.0.0.1"); //白名单 
      //reg.addInitParameter("deny",""); //黑名单 
      reg.addInitParameter("loginUsername", "admin"); 
      reg.addInitParameter("loginPassword", "admin"); 
      return reg; 
    } 

    @Bean public FilterRegistrationBean filterRegistrationBean() { 
      FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(); 
      filterRegistrationBean.setFilter(new WebStatFilter()); 
      filterRegistrationBean.addUrlPatterns("/*"); 
      filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"); 
      return filterRegistrationBean; 
     }
}
