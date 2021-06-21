package com.kcsj.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcsj.admin.bean.Goods;

/**
 * @author lcz
 * @create 2021-06-07-19:34
 */
public interface GoodsService extends IService<Goods> {

    Page<Goods> getGoodsListBySql(Page page, String sql);
    Goods queryByGoodsId(Integer goodsId);
    int deleteById(int id);
    int updateState(Goods goods);
    int addGoods(Goods goods);

}
