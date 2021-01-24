package huangzihao.homework.multidatasource.service;

import huangzihao.homework.multidatasource.entity.Goods;
import huangzihao.homework.multidatasource.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangzihao
 * @since 2021/1/24
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public Goods getGoods(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    public void addGoods(Goods goods){
        goodsMapper.insert(goods);
    }
}
