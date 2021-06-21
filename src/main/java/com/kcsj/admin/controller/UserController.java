package com.kcsj.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcsj.admin.bean.Goods;
import com.kcsj.admin.bean.User;
import com.kcsj.admin.service.AdminService;
import com.kcsj.admin.service.GoodsService;
import com.kcsj.admin.service.UserService;
import com.kcsj.admin.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @author lcz
 * @create 2021-06-09-15:13
 */
@Controller
public class UserController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;

    //用户主页
    @GetMapping({"/user","/userPage"})
    public String userPage(@RequestParam(value = "pn",defaultValue = "1") Integer pn , Model model , HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        Integer donationId = loginUser.getId();

        //用户捐赠的物资
        Page<Goods> donationPage = new Page<>(pn,4);
        Page<Goods> donation = goodsService.page(donationPage, null);
        String sql = "donation_id = " + donationId;
        donation = goodsService.getGoodsListBySql(donation,sql);
        model.addAttribute("donation",donation);

        //用户领取的物资
        Page<Goods> receivePage = new Page<>(pn,4);
        Page<Goods> receive = goodsService.page(receivePage, null);
        String receiveSQL = "(state = 2 or state = 3) and receive_id = " + donationId;
        receive = goodsService.getGoodsListBySql(receive,receiveSQL);
        model.addAttribute("receive",receive);


        //登录提示
        if (session.getAttribute("loginUser") == null){
            model.addAttribute("loginUser",null);
        }else {
            model.addAttribute("loginUser",session.getAttribute("loginUser"));
        }

        return "userPage";
    }

    //跳转捐赠
    @GetMapping("/userAddPage")
    public String addPage(HttpSession session){

        return "addGoods";
    }

    //捐赠物品页
    @PostMapping("/userAddGoods")
    public String userAddGoods(Goods goods,
                           @RequestPart("goodsImg") MultipartFile goodsImg,
                           HttpSession session) throws IOException {

        User loginUser = (User) session.getAttribute("loginUser");
        Integer donationId = loginUser.getId();
        goods.setDonationId(donationId);
        if (!goodsImg.isEmpty()){
            String originalFilename = goodsImg.getOriginalFilename();
            String photoPath =UUIDUtils.getUUID() + originalFilename ;
            goods.setPhoto(photoPath);
            goodsImg.transferTo(new File("C:\\Users\\lcz\\Desktop\\boot05-web-admin\\src\\main" +
                    "\\resources\\static\\images\\goods\\" + photoPath));
        }
        int isSuccess = goodsService.addGoods(goods);
        return "redirect:userPage";
    }


    @GetMapping("/goodsDelete")
    public String goodsDelete(@RequestParam("goodsId")int goodsId){

        goodsService.deleteById(goodsId);

        return "redirect:user";
    }

    @GetMapping("/goodsRemove")
    public String goodsRemove(@RequestParam("goodsId") Integer goodsId){

        Goods goods = new Goods(goodsId,null,7,null,1,null,null,null);
        goodsService.updateState(goods);
        return "redirect:userPage";
    }

}
