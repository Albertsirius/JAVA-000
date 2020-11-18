package java0.homework.huangzihao.spring.beams;

/**
 * <p>
 * 学生类定义
 * @author huangzihao
 * @since 2020/11/18
 */
public class Student {
    private String name;

    public void setName(String name) {this.name = name;}

    public String getName() {return  name;}

    public Student(String name) {
        this.name = name;
    }

    public Student() {
        this.name = "default";
    }
}
