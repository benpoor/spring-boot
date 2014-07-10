package com.benpoor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created with IntelliJ IDEA.
 * User: benpoor2008
 * Date: 14-5-12
 * Time: 下午1:36
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ComponentScan(basePackages = {"com","reactor"})
@EnableAutoConfiguration
//@ImportResource("")
public class Application {


    public static void main(String[] args){
        SpringApplication.run(Application.class,"--debug");
    }
}
