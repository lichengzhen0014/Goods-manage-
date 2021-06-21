package com.kcsj.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcsj.admin.bean.Goods;
import com.kcsj.admin.bean.User;
import com.kcsj.admin.service.GoodsService;
import com.kcsj.admin.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author lcz
 * @create 2021-06-07-19:45
 */
@Controller
public class IndexController {

    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;

    @GetMapping(value = {"/login"})
    public String loginPage() {

        return "login";
    }

    @GetMapping(value = {"/logout"})
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "login";
    }


    @PostMapping(value = "/login")
    public String main(User user, HttpSession session, Model model) {

        Boolean isLogin = userService.loginVerification(user);
        if (isLogin) {
            User loginUser = userService.queryUserByUserNameAndPassword(user);
            session.setAttribute("loginUser",loginUser);

            //重定向防止表单重复提交
            return "redirect:index.html";
        }else {
            model.addAttribute("msg","账号或密码错误!");
            return "login";
        }

    }

    @GetMapping(value = {"index.html","/"})
    public String mainPage(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model ,HttpSession session) {

        Page<Goods> goodsPage = new Page<>(pn,4);
        //分页查询结果
        Page<Goods> page = goodsService.page(goodsPage, null);
        //条件筛选
        String sql = "state = 1 or state = 2";
        page = goodsService.getGoodsListBySql(page,sql);

        model.addAttribute("page",page);
        //登录提示
        if (session.getAttribute("loginUser") == null){
            model.addAttribute("loginUser",null);
        }else {
            model.addAttribute("loginUser",session.getAttribute("loginUser"));
        }

        return "index";
    }

    @GetMapping("/indexSearch")
    public String searchGoods(@RequestParam(value = "pn",defaultValue = "1") Integer pn,@RequestParam("category") String category, Model model , HttpSession session) {

        Page<Goods> goodsPage = new Page<>(pn,4);
        Page<Goods> page = goodsService.page(goodsPage, null);
        String sql = "(state = 1 or state = 2) and category like '%" + category +"%'";
        page = goodsService.getGoodsListBySql(page,sql);

        model.addAttribute("page",page);
        //登录提示
        if (session.getAttribute("loginUser") == null){
            model.addAttribute("loginUser",null);
        }else {
            model.addAttribute("loginUser",session.getAttribute("loginUser"));
        }

        return "index";
    }

    //领取物品
    @GetMapping("/receive")
    public String goodsReceive(@RequestParam("goodsId") Integer goodsId,@RequestParam("pn") Integer pn, HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        Integer receiveId = loginUser.getId();
        Goods goods = new Goods(goodsId,null,receiveId,null,2,null,null,null);
        goodsService.updateState(goods);
        return "redirect:/index.html?pn=" + pn;
    }

//    @RequestMapping(value = "/verify")
//    @ResponseBody
//    public Map<String, Object> login(String token) {
//        String secret = "服务端的密钥";
//        String result = HttpUtils.get("https://recaptcha.net/recaptcha/api/siteverify?secret=" + secret + "&response=" + token);
//        Map<String, Object> map = JSONObject.parseObject(result);
//        return map;
//    }


}
