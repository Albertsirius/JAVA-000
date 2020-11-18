package java0.homework.huangzihao.spring.beams;

import org.springframework.stereotype.Component;

/**
 * <p>
 * 中文老师的bean
 * @author huangzihao
 * @since 2020/11/18
 */
@Component
public class ChineseTeacher implements Teacher{
    @Override
    public String printSubject() {
        return "Chinese";
    }
}
