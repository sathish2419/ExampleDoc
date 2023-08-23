package com.multipledb.springboot.DBConfiguration;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "SecondaryEntityManagerFactory",
        transactionManagerRef = "SecondaryTransactionManager",
        basePackages = { "package com.multipledb.springboot.Repository;" }
)
public class SecondarydbConfiguration {
    @Bean(name="SecondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.Secondary")
    public DataSource SecondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "SecondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean SecondaryEntityManagerFactory
            (EntityManagerFactoryBuilder builder,
             @Qualifier("SecondaryDataSource") DataSource dataSource) {

        HashMap<String,Object> properties=new HashMap<>();
        properties.put("hibernate.ddl-auto","update");

        return builder.dataSource(dataSource)
                .properties(properties)
                .packages("package com.multipledb.springboot.Secondary")
                .build();
    }

    @Bean(name = "SecondaryTransactionManager")
    public PlatformTransactionManager SecondaryTransactionManager(
            @Qualifier("SecondaryEntityManagerFactory") EntityManagerFactory SecondaryEntityManagerFactory) {
        return new JpaTransactionManager(SecondaryEntityManagerFactory);
    }
}

