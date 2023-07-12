package br.com.omotor.labelcreatorproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LabelcreatorProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(LabelcreatorProjectApplication.class, args);
    }

}
