package com.kcsj.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcsj.admin.bean.Goods;
import com.kcsj.admin.mapper.GoodsMapper;
import com.kcsj.admin.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lcz
 * @create 2021-06-07-19:34
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Page<Goods> getGoodsListBySql(Page page, String sql) {
        return goodsMapper.getGoodsListBySql(page,sql);
    }

    @Override
    public Goods queryByGoodsId(Integer goodsId) {
        Goods goods = goodsMapper.queryByGoodsId(goodsId);
        return goods;
    }

    @Override
    public int deleteById(int id) {
        int isDelete = goodsMapper.deleteById(id);
        return isDelete;
    }

    @Override
    public int updateState(Goods goods) {
        int isUpdate = goodsMapper.updateStateById(goods);
        return isUpdate;
    }

    @Override
    public int addGoods(Goods goods) {
        int isAdd = goodsMapper.addGoods(goods);
        return isAdd;
    }
}
