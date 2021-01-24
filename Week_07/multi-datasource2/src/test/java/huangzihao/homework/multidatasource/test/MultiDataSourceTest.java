package huangzihao.homework.multidatasource.test;

import huangzihao.homework.multidatasource.entity.Goods;
import huangzihao.homework.multidatasource.service.GoodsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * @author huangzihao
 * @since 2021/1/24
 */
@SpringBootTest
public class MultiDataSourceTest {
    @Autowired
    private GoodsService goodsService;

    @Test
    public void serviceTestForQuery(){
        Goods goods = goodsService.getGoods(Long.valueOf(1));
        Assertions.assertThat(goods.getName()).isEqualTo("huangzihao");
    }

    @Test
    public void insertNewGoods() {
        Goods goods = new Goods().withName("ccc")
                .withStatus("0")
                .withCcy("CNY")
                .withPrice(new BigDecimal(300))
                .withInventory(1000)
                .withCreateTime(System.currentTimeMillis());
        goods.setUpdateTime(goods.getCreateTime());
        goodsService.addGoods(goods);
        //Goods newGoods = goodsService.getGoods(Long.valueOf(4));
        //Assertions.assertThat(newGoods.getName()).isEqualTo("ccc");
    }
}
