package huangzihao.homework.multidatasource.data;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 *
 * 定义切面
 * @author AlbertSirius
 * @since 2021/1/6
 */

@Aspect
@Slf4j
@Component
public class DynamicDataSourceAspect {
    @Pointcut("@annotation(huangzihao.homework.multidatasource.data.annotation.ReadOnly)")
    public void readOnlyPointCut(){
    }

    //ReadOnly注解的方法使用从库
    @Before("readOnlyPointCut()")
    public void readOnlyBefore() throws Throwable{
        DynamicDataSourceContextHolder.useSlaveDataSource();
        log.info("Use Slave Source : " + DynamicDataSourceContextHolder.getDataSourceKey());
    }

    @After("readOnlyPointCut()")
    public void readOnlyAfter() throws Throwable{
        DynamicDataSourceContextHolder.clearDataSourceKey();
        //log.info("Restore Master Source : " + DynamicDataSourceContextHolder.getDataSourceKey());
    }

}
