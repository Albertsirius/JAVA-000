package huangzihao.homework.starteddemo;

import huangzihao.homework.starterdemo.SpringBootConfiguration;
import huangzihao.homework.starterdemo.beans.School;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 *
 * @author huangzihao
 * @since 2020/11/22
 */
@SpringBootTest(classes = SpringBootConfiguration.class)
public class SchoolTest {

    @Autowired
    School school;

    @Test
    public void test(){
        school.ding();
    }
}
