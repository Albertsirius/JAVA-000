package huangzihao.homework.multidatasource.controller;

import huangzihao.homework.multidatasource.entity.Goods;
import huangzihao.homework.multidatasource.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getGoods(@PathVariable("id") String id) {
        return goodsService.getGoods(Long.valueOf(id));
    }

    @PostMapping("/goods")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addGoods(@RequestBody Goods goods){
        goodsService.addGoods(goods);
        return goods;
    }
}
