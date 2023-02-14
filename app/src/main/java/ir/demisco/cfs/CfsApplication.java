package ir.demisco.cfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Rasool Aghajani - 4/27/2021
 */

@SpringBootApplication(scanBasePackages = {"ir.demisco.cloud","ir.demisco.cfs.*"})
@EntityScan(basePackages = {"ir.demisco.cfs.model","ir.demisco.cloud.basic.model"})
@EnableJpaRepositories(basePackages = {"ir.demisco.cloud.basic.*","ir.demisco.cfs.service.repository"})
@PropertySource("classpath:swagger.properties")
public class CfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CfsApplication.class);
    }

}
