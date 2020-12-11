package huangzihao.homework.sqldemo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>
 * 生成1000000条记录订单文件
 * @author huangzihao
 * @since 2020/12/11
 */
public class GeneraData {

    public static void main(String[] args) {
        Path path = Paths.get("data.txt");
        BufferedWriter writer;
        try{
            if(!Files.exists(path)){
                Files.createFile(path);
            }
            writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
            //写入记录1000000条，每条为1,1,CNY
            for(int i = 0; i < 1000000; i++) {
                writer.write("1,1,CNY\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
