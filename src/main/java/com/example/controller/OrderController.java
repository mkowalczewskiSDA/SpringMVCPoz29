package com.example.controller;

import com.example.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

@Controller
@RequestMapping("/order")
@SessionScope
public class OrderController {

    @Autowired
    OrderDao orderDao;

    @RequestMapping("/list")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderDao.getOrders());
        return "order_list";
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam int id, Model model) {
        orderDao.deleteOrder(id);
        model.addAttribute("orders", orderDao.getOrders());
        return "order_list";
    }


}
