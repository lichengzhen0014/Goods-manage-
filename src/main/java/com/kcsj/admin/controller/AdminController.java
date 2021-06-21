package com.kcsj.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcsj.admin.bean.Admin;
import com.kcsj.admin.bean.Goods;
import com.kcsj.admin.bean.User;
import com.kcsj.admin.service.AdminService;
import com.kcsj.admin.service.GoodsService;
import com.kcsj.admin.service.UserService;
import com.kcsj.admin.service.impl.MailService;
import com.kcsj.admin.utils.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpSession;

/**
 * @author lcz
 * @create 2021-06-09-15:13
 */
@Controller
@EnableAsync
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    SendMail sendMail;

    @GetMapping("/adminLogin")
    public String adminLogin(HttpSession session){
        Object adminInfo = session.getAttribute("AdminInfo");
        if (adminInfo != null){
            return "redirect:admin.html";
        }
        return "adminLogin";
    }

    @PostMapping("/admin")
    public String admin(Admin admin,HttpSession session){
        Boolean aBoolean = adminService.loginVerification(admin);
        if (aBoolean){
            Admin adminInfo = adminService.queryAdmin(admin);
            session.setAttribute("AdminInfo",adminInfo);
            return "redirect:admin.html";
        }
        return "adminLogin";
    }

    @GetMapping("/adminOut")
    public String adminOut(HttpSession session){
        session.removeAttribute("AdminInfo");
        return "redirect:index.html";
    }

    @GetMapping("/admin.html")
    public String adminPage(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model , HttpSession session){
        Page<Goods> goodsPage = new Page<>(pn,4);
        //分页查询结果
        Page<Goods> page = goodsService.page(goodsPage, null);
        //条件筛选
        String sql = "state = 0 or state = 2";
        page = goodsService.getGoodsListBySql(page,sql);
        model.addAttribute("page",page);
        //管理员信息
        if (session.getAttribute("AdminInfo") == null){
            model.addAttribute("AdminInfo",null);
        }else {
            model.addAttribute("AdminInfo",session.getAttribute("AdminInfo"));
        }

        return "admin";
    }

    //通过捐赠
    @GetMapping("/goodsAdopt")
    public String donationExamineAdopt(@RequestParam("goodsId")Integer goodsId,@RequestParam("pn")Integer pn, HttpSession session){

        Admin adminInfo = (Admin) session.getAttribute("AdminInfo");
        Integer adminId = adminInfo.getAdminId();
        Goods goods = new Goods(goodsId,null,null,adminId,1,null,null,null);
        int isSuccess = goodsService.updateState(goods);
        return "redirect:admin.html?pn=" + pn;

    }

    //撤回捐赠
    @GetMapping("/goodsRevoke")
    public String donationExamineRevoke(@RequestParam("goodsId")Integer goodsId,@RequestParam("pn") Integer pn){

        int i = goodsService.deleteById(goodsId);

        return "redirect:admin.html?pn=" + pn;
    }

    //通过领取
    @GetMapping("/receiveAdopt")
    public String receiveAdopt(@RequestParam("goodsId")Integer goodsId,@RequestParam("pn")Integer pn,HttpSession session){
        Admin adminInfo = (Admin) session.getAttribute("AdminInfo");
        Integer adminId = adminInfo.getAdminId();
        Goods goods = new Goods(goodsId,null,null,adminId,3,null,null,null);
        goodsService.updateState(goods);

        //邮件通知
        System.out.println(Thread.currentThread().getName());
        Goods receiveGoods = goodsService.queryByGoodsId(goodsId);
        Integer receiveId = receiveGoods.getReceiveId();
        User user = userService.queryUser(receiveId);
        sendMail.sendMail(user.getEmail(),"审核通知",user.getUserName() + "您在Freecycle领取的物品审核通过，快去看看吧！");

        return "redirect:admin.html?pn=" + pn;
    }
    //撤回领取
    @GetMapping("/receiveRevoke")
    public String receiveRevoke(@RequestParam("goodsId")Integer goodsId,@RequestParam("pn")Integer pn){

        Goods goods = new Goods(goodsId,null,7,null,1,null,null,null);
        int i = goodsService.updateState(goods);

        return "redirect:admin.html?pn=" + pn;
    }

    //查验用户
    @GetMapping("/auditUser")
    public String auditUser(@RequestParam("goodsId") Integer goodsId,HttpSession session){
        Goods goods = goodsService.queryByGoodsId(goodsId);
        Integer auditUserId = goods.getReceiveId();
        User user = userService.queryUser(auditUserId);
        session.setAttribute("checkUser",user);
        return "redirect:/auditUser.html";
    }

    @GetMapping("/auditUser.html")
    public String auditUserPage(@RequestParam(value = "pn",defaultValue = "1") Integer pn , Model model , HttpSession session){
        //用户捐赠的物资
        User checkUser = (User) session.getAttribute("checkUser");
        Integer donationId = checkUser.getId();
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
        return "auditUser";
    }

}
