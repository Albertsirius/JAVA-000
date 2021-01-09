package huangzihao.homework.multidatasource.test;

import huangzihao.homework.multidatasource.entity.Goods;
import huangzihao.homework.multidatasource.mapper.GoodsMapper;
import huangzihao.homework.multidatasource.service.GoodsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * 单元测试验证
 * @author AlbertSirius
 * @since 2021/1/8
 */
@SpringBootTest
public class MultiDataSourceTest {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void serviceTestforQuery(){
        Goods goods = goodsService.getGoods(Long.valueOf(1));
        Assertions.assertThat(goods.getName()).isEqualTo("huangzihao");
    }

    @Test
    public void insertNewGoods() {
        Goods goods = new Goods().withName("aaa")
                .withStatus("0")
                .withCcy("CNY")
                .withPrice(new BigDecimal(200))
                .withInventory(10)
                .withCreateTime(System.currentTimeMillis());
        goods.setUpdateTime(goods.getCreateTime());
        goodsService.addGoods(goods);
        //goodsMapper.insert(goods);
    }
}
