package com.pangpang.jei.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @description: 源码获取bean
 * @author: leewake
 * @create: 2018-06-06 11:48
 **/

@RestController
public class GetBeanDefinitionController {

    public static final String PACKAGEPATH = "com.pangpang.jei.controller";

    @ApiOperation("获取bean")
    @GetMapping("/beanDefinition/get")
    public void getBeanDefinition() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        Set<BeanDefinition> candidateComponents = provider.findCandidateComponents(PACKAGEPATH);
        for (BeanDefinition candidateComponent : candidateComponents) {
            System.out.println(candidateComponent.getBeanClassName()
                    + "\t" + candidateComponent.getResourceDescription()
                    + "\t" + candidateComponent.getClass());
        }
    }

    @ApiOperation("路径获取")
    @GetMapping("/path/get")
    public void getResource() {
        System.out.println(GetBeanDefinitionController.class.getResource("")); //file:/Users/lijingwei/github/jei-demo/target/classes/com/pangpang/jei/controller/
        System.out.println(GetBeanDefinitionController.class.getResource("/")); //file:/Users/lijingwei/github/jei-demo/target/classes/

        System.out.println(GetBeanDefinitionController.class.getClassLoader().getResource("")); //file:/Users/lijingwei/github/jei-demo/target/classes/
        System.out.println(GetBeanDefinitionController.class.getClassLoader().getResource("/")); //null

        System.out.println(GetBeanDefinitionController.class.getClassLoader().getResource("file/2007NoModelMultipleSheet.xlsx").getFile()); // /Users/lijingwei/github/jei-demo/target/classes/file/2007NoModelMultipleSheet.xlsx
    }
}
