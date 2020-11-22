package huangzihao.homework.starterdemo.beans;

import huangzihao.homework.starterdemo.aop.ISchool;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author huangzihao
 * @since 2020/11/22
 */
@Data
@AllArgsConstructor
public class School implements ISchool {

    private String name;
    private List<Klass> Klasses;

    public School() {
        name = "";
        Klasses = new ArrayList<>();
    }

    public void addKlass(Klass klass) {
        Klasses.add(klass);
    }

    @Override
    public void ding() {

        System.out.println("School " + this.name + " has " + Klasses.size() + " Classes.");

    }
}
