package com.kcsj.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcsj.admin.bean.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lcz
 * @create 2021-06-07-19:32
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    Page<Goods> getGoodsListBySql(Page page, String sql);
    Goods queryByGoodsId(@Param("goodsId") Integer goodsId);
    int deleteById(@Param("goodsId") Integer goodsId);
    int updateStateById(Goods goods);
    int addGoods(Goods goods);

}
