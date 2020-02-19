package com.kwt.app.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.kwt.app", entityManagerFactoryRef="entityManagerFactory")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class AppConfigContext {

	@Value("${db.url}")
	private String db_url;

	@Value("${db.username}")
	private String db_username;

	@Value("${db.password}")
	private String db_password;

	@Value("${db.driver}")
	private String db_driver;

	@Value("${hibernate.dialect}")
	private String hibernate_dialect;

	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernate_hbm2ddl_auto;

	@Value("${hibernate.show_sql}")
	private String hibernate_show_sql;

	@Value("${hibernate.format_sql}")
	private String hibernate_format_sql;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(db_driver);
		dataSource.setUrl(db_url);
		dataSource.setUsername(db_username);
		dataSource.setPassword(db_password);
		return dataSource;
	}
	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory em) {
		return new JpaTransactionManager(em);
	}
	@Bean(name="entityManagerFactory")
	public EntityManagerFactory geEntityManagerFactoryBean() {

		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("com.kwt.app.entity");
		emf.setPersistenceUnitName("spring-jpa-unit");
		emf.setJpaVendorAdapter(getHibernateAdapter());
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", hibernate_dialect);
		jpaProperties.put("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
		jpaProperties.put("hibernate.show_sql", hibernate_show_sql);
		jpaProperties.put("hibernate.format_sql", hibernate_format_sql);
		emf.setJpaProperties(jpaProperties);
		emf.afterPropertiesSet();
		return emf.getObject();
	}

	@Bean
	public JpaVendorAdapter getHibernateAdapter() {
		return new HibernateJpaVendorAdapter();
	}

}
