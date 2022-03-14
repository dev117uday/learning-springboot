package com.backend.springtok;

import com.backend.springtok.entity.Doctor;
import com.backend.springtok.entity.NewDoctor;
import com.backend.springtok.entity.Nurse;
import com.backend.springtok.entity.Staff;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringTokApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(SpringTokApplication.class, args);
        // Arrays.stream(app.getBeanDefinitionNames()).forEach(System.out::println);

        // from XML

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        var doctor = context.getBean(Doctor.class);
        doctor.assist();
        System.out.println(doctor.getQualification());
        System.out.println(doctor.getNurse().toString());
        System.out.println(doctor);

        var doctor2 = (Nurse) context.getBean("nurse");
        doctor2.assist();

        Staff doctor3 = context.getBean(Doctor.class);
        doctor3.assist();

        var doctor4 = context.getBean(NewDoctor.class);
        doctor4.assist();

        // from class      

        ApplicationContext anotherContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        var newDoctor = anotherContext.getBean(NewDoctor.class);
        newDoctor.assist();
        newDoctor.setQualification("MBBS++");
        System.out.println(newDoctor);

        // singleton demo
        var anotherNewDoctor = anotherContext.getBean(NewDoctor.class);
        System.out.println(anotherNewDoctor);

    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("hello world");
        };
    }

}
