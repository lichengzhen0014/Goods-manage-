package com.kcsj.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcsj.admin.bean.Admin;
import com.kcsj.admin.bean.Goods;
import com.kcsj.admin.bean.User;
import com.kcsj.admin.mapper.AdminMapper;
import com.kcsj.admin.mapper.GoodsMapper;
import com.kcsj.admin.mapper.UserMapper;
import com.kcsj.admin.service.AdminService;
import com.kcsj.admin.service.GoodsService;
import com.kcsj.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class Boot05WebAdminApplicationTests {


    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    AdminService adminService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    Goods goods;

    @Test
    void userTestList() {

        List<User> list = userService.list();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    void userTestSave() {

        int count = userService.count();
        userService.save(new User(++count,"1","1","1"));
    }

    @Test
    void userTestLogin() {

//        User admin = new User(1, "admin", "123456", "");
//        Boolean aBoolean = userService.loginVerification(admin);
//        System.out.println(aBoolean);
        User user = userService.queryUser(1);
        System.out.println(user);

    }



    @Test
    void goodsTest() {

        List<Goods> list = goodsService.list();
        for (Goods goods : list) {
            if (goods.getReceiveId() != null){
                list.remove(goods);
            }
        }
        list.forEach(System.out::println);
    }

    @Test
    void goodsTestPage() {

        Page<Goods> goodsPage = new Page<>(1,4);
        //分页查询结果
        Page<Goods> page = goodsService.page(goodsPage, null);
        List<Goods> records = page.getRecords();
        records.forEach(System.out::println);
    }

    @Test
    void isAdmin() {
        Admin admin = new Admin();
        admin.setAccount("1416311778");
        admin.setPassword("256334");
        Boolean aBoolean = adminService.loginVerification(admin);
        System.out.println(aBoolean);

    }

    @Test
    void userPageTest(){
        Page<Goods> donationPage = new Page<>(1,4);
        //分页查询结果
        Page<Goods> donation = goodsService.page(donationPage, null);
        //条件筛选
        String sql = "(state = 0 or state = 1) and donation_id = 6";
        donation = goodsService.getGoodsListBySql(donation,sql);
        //page对象包含了分页的所有信息，当前页、总页数、是否有上一页、共多少条数据...
        List<Goods> records = donation.getRecords();
        records.forEach(System.out::println);


    }

    @Test
    void userPageTest02(){
        Page<Goods> receivePage = new Page<>(1,4);
        //分页查询结果
        Page<Goods> receive = goodsService.page(receivePage, null);
        //条件筛选
        String receiveSQL = "state = 3 and receive_id = " + 1;
        receive = goodsService.getGoodsListBySql(receive,receiveSQL);
        List<Goods> records = receive.getRecords();
        records.forEach(System.out::println);
    }
    @Test
    void userPageTest03(){
        Page<Goods> goodsPage = new Page<>(1,4);
        //分页查询结果
        Page<Goods> page = goodsService.page(goodsPage, null);
        //条件筛选
        String sql = "category like '%" + "被" +"%'";
        page = goodsService.getGoodsListBySql(page,sql);
        List<Goods> records = page.getRecords();
        records.forEach(System.out::println);
    }

    @Test
    void goodsDelete(){
        int i = goodsMapper.deleteById(12);
        System.out.println(i);
    }

    @Test
    void goodsUpdate(){
        Goods goods = new Goods();
        goods.setGoodsId(12);
        goods.setState(1);
        int i = goodsMapper.updateStateById(goods);
        System.out.println(i);
    }
}
