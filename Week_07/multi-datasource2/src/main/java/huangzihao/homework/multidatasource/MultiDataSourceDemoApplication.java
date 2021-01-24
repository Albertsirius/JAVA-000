package huangzihao.homework.multidatasource;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * 采用shardingsphere-jdbc来实现主从
 *
 * @author huangzihao
 * @since 2021/1/24
 */
@Slf4j
@MapperScan("huangzihao.homework.multidatasource.mapper")
@SpringBootApplication
public class MultiDataSourceDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiDataSourceDemoApplication.class, args);
    }
}
