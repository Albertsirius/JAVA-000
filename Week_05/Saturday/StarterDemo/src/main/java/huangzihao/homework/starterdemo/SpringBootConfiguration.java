package huangzihao.homework.starterdemo;

import huangzihao.homework.starterdemo.beans.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * @author huangzihao
 * @since 2020/11/22
 */
@Configuration
@EnableConfigurationProperties(SchoolConfigurationProperties.class)
@ConditionalOnProperty(prefix = "school", value = "enable")
public class SpringBootConfiguration {

    @Autowired
    private SchoolConfigurationProperties configurationProperties;

    @Bean
    public School school() {
        return new School(configurationProperties.getName(), configurationProperties.getKlasses());
    }
}
