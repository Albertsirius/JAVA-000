package huangzihao.homework.multidatasource.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 数据源上下文，切换数据源逻辑在这里实现
 *
 * @author AlbertSirius
 * @since 2021/1/6
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> DATASOURCE_CONTEXT = ThreadLocal.withInitial(() -> DataSourceKey.MASTER);

    private static List<String> slaveDataSourceKeys;

    private static int roundRobinCount = 0;

    private static Lock lock = new ReentrantLock();

    static {
        slaveDataSourceKeys = new ArrayList<>();
        slaveDataSourceKeys.add(DataSourceKey.SLAVE1);
        slaveDataSourceKeys.add(DataSourceKey.SLAVE2);
    }

    public static void setDataSourceKey(String key) {
        DATASOURCE_CONTEXT.set(key);
    }

    public static String getDataSourceKey() {
        String key = DATASOURCE_CONTEXT.get();
        return key != null ? key : DataSourceKey.MASTER;
    }

    public static void clearDataSourceKey() {
        DATASOURCE_CONTEXT.remove();
    }

    //使用主库
    public static void useMasterDataSource() {
        DATASOURCE_CONTEXT.set(DataSourceKey.MASTER);
    }

    //使用从库
    public static void  useSlaveDataSource() {
        lock.lock();
        try {
            DATASOURCE_CONTEXT.set(slaveDataSourceKeys.get(roundRobinCount % slaveDataSourceKeys.size()));
            roundRobinCount++;
        }catch (Exception e) {
            useMasterDataSource();
        }finally {
            lock.unlock();
        }
    }

}
