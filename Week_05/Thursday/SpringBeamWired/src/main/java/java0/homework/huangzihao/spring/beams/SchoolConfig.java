package java0.homework.huangzihao.spring.beams;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 类配置装配
 *
 * @author huangzihao
 * @since 2020/11/18
 */
@Configuration
public class SchoolConfig {

    @Bean
    public School school(){
        return new School("No.1 Middle School");
    }
}
