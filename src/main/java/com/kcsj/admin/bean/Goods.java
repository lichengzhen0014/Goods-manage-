package com.kcsj.admin.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author lcz
 * @create 2021-06-07-16:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("goods")
@Component
public class Goods {

    //商品  id
    private Integer goodsId;
    //捐赠者id
    private Integer donationId;
    //认领人id
    private Integer receiveId;
    //管理员id
    private Integer controllerId;
    // 0:发布未审核  1:已审核未领取 2:申请领取未审核 3:领取成功
    private Integer state = 0;
    private String photo;
    private String category;
    private String description;

}
