package huangzihao.homework.starterdemo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *
 * @author huangzihao
 * @since 2020/11/22
 */
@Data
@AllArgsConstructor
public class Klass {

    private String name;
    private List<Student> students;

    public Klass() {
        name = "";
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void dong() {
        System.out.println(this.getStudents());
    }
}
