package java0.homework.huangzihao.spring.beams;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 *
 * @author huangzihao
 * @since 2020/11/18
 */

public class SpringBeamsDemo {

    public static void main(String[] args) {
        //通过xml方式装载bean
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) applicationContext.getBean("student01");
        System.out.println(student.getName());


        //自动扫描装载Bean
        Teacher teacher = (Teacher) applicationContext.getBean(Teacher.class);
        System.out.println(teacher.printSubject());

        //通过类注解装配
        ApplicationContext annotationApplicationContext = new AnnotationConfigApplicationContext(SchoolConfig.class);
        School school = annotationApplicationContext.getBean(School.class);
        System.out.println(school.getSchoolName());


    }


}
