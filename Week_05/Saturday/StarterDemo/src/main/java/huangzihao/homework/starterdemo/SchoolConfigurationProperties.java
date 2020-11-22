package huangzihao.homework.starterdemo;

import huangzihao.homework.starterdemo.beans.Klass;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * <p>
 * 配置类
 * @author huangzihao
 * @since 2020/11/22
 */
@ConfigurationProperties(prefix = "school")
@Data
public class SchoolConfigurationProperties {

    private String name;
    private List<Klass> klasses;

}
