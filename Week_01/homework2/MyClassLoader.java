import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author huangzihao
 * @since 2020/10/21
 */
public class MyClassLoader extends ClassLoader {

    public MyClassLoader() {
        super();
    }

    public static void main(String[] args) {
        try {
            Class cl = new MyClassLoader().findClass("Hello");
            cl.getDeclaredMethod("hello").invoke(cl.newInstance());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classByteArray = Files.readAllBytes(Paths.get(name + ".xlass"));
            for (int i = 0; i < classByteArray.length; i++) {
                classByteArray[i] = (byte) (255 - classByteArray[i]);
            }
            return defineClass(name, classByteArray, 0, classByteArray.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Can't not find " + name, e);
        }
    }

}
