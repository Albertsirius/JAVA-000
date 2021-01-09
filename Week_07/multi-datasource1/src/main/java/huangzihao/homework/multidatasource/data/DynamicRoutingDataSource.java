package huangzihao.homework.multidatasource.data;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * 动态数据源，继承AbstractRoutingDataSource
 *
 * @author AlbertSirius
 * @since 2021/1/6
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
}
