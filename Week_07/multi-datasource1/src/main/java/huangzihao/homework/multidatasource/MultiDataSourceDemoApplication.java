package huangzihao.homework.multidatasource;

import huangzihao.homework.multidatasource.entity.Goods;
import huangzihao.homework.multidatasource.mapper.GoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.math.BigDecimal;

@Slf4j
@SpringBootApplication
@MapperScan("huangzihao.homework.multidatasource.mapper")
public class MultiDataSourceDemoApplication {

    @Autowired
    private GoodsMapper goodsMapper;

    public static void main(String[] args) {
        SpringApplication.run(MultiDataSourceDemoApplication.class, args);
    }

}
