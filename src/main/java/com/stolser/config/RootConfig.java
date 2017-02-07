package com.stolser.config;

import com.stolser.controller.VideoImporter;
import com.stolser.controller.VideoImporterImpl;
import com.stolser.search.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan(basePackages = {"com.stolser"})
@EnableJpaRepositories(basePackages = {"com.stolser.repository"})
@PropertySources({
        @PropertySource("classpath:config/dbConfig.properties"),
        @PropertySource("classpath:config/videoImportConfig.properties")
})
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class RootConfig {
    private static final int SEARCH_THREADS_NUMBER = 10;

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSource.setUrl(env.getRequiredProperty("db.url"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaVendorAdapter vendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty("entitymanager.packages.to.scan"));
//        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
//        entityManagerFactoryBean.setJpaProperties(hibProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
//        adapter.setDatabasePlatform();
        return adapter;
    }

    @Bean
    public VideoImporter videoImporter() {
        return new VideoImporterImpl();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IdSearcher searchEngine() {
        return new ConcurrentIdSearcher();
    }

    @Bean
    public RawResultsSearcher rawResultsSearcher() {
        return new RawResultsSearcherImpl();
    }

    @Bean
    public VideoSearcher videoSearchEngine() {
        return new ConcurrentVideoSearcher();
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(SEARCH_THREADS_NUMBER);
    }

//    @Bean
//    public HibernateTransactionManager transactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory(dataSource()).getObject());
//        return transactionManager;
//    }
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
//        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setPackagesToScan(env.getRequiredProperty("entitymanager.packages.to.scan"));
//        sessionFactoryBean.setHibernateProperties(hibProperties());
//        return sessionFactoryBean;
//    }
//
//    private Properties hibProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
//        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
//        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
//        properties.put("hibernate.connection.autocommit", env.getRequiredProperty("hibernate.connection.autocommit"));
//        return properties;
//    }
}
