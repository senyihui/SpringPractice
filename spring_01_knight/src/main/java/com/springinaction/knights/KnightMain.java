package com.springinaction.knights;

import com.springinaction.knights.config.KnightConfig;
import com.springinaction.knights.interfaces.Knight;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KnightMain {
    public static void main(String[] args) {
        // METHOD 1. Xml
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "Minstrel.xml");

        // METHOD 2. Java Configuration
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
//                KnightConfig.class);

        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }
}
