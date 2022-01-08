package demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

//        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

//        Doctor doctor = context.getBean(Doctor.class);
//        doctor.assist();
//        System.out.println(doctor.getQualification());

//        Staff nurse = (Nurse) context.getBean("nurse");
//        nurse.assist();

        ApplicationContext context =
                new AnnotationConfigApplicationContext(BeanConfig.class);

        Doctor newDoctor = context.getBean(Doctor.class);
        newDoctor.assist();
        newDoctor.setQualification("MBBS");
        System.out.println(newDoctor);

        Doctor anotherDoctor = context.getBean(Doctor.class);
        System.out.println(anotherDoctor);

    }
}
