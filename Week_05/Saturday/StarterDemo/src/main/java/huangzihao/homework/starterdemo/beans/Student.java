package huangzihao.homework.starterdemo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 *
 * @author huangzihao
 * @since 2020/11/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable {
    private int id;
    private String name;

    public void init() { System.out.println("Hello......");}

    public Student create() {
        return new Student(101, "KK101");
    }
}
