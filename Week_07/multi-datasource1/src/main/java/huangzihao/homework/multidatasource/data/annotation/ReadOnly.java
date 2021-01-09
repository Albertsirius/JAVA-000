package huangzihao.homework.multidatasource.data.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 定义ReadOnly注解，表示使用从库，其他默认使用主库
 * @author AlbertSirius
 * @since 2021/1/6
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadOnly {
}
