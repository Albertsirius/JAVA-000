package huangzihao.homework.multidatasource.service;

import huangzihao.homework.multidatasource.data.annotation.ReadOnly;
import huangzihao.homework.multidatasource.entity.Goods;
import huangzihao.homework.multidatasource.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AlbertSirius
 * @since 2021/1/6
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @ReadOnly
    public Goods getGoods(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    public void addGoods(Goods goods) {
        goodsMapper.insert(goods);
    }
}
