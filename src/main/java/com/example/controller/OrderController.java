package com.example.controller;

import com.example.dao.OrderDao;
import com.example.dao.UserDao;
import com.example.model.Order;
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

    @Autowired
    UserDao userDao;

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

    @PostMapping("/edit")
    public String editOrder(@RequestParam int id, Model model) {
        model.addAttribute("order", orderDao.getOrderById(id));
        model.addAttribute("users", userDao.getUsers());
        return "post_order";
    }

    @PostMapping("/save")
    public String saveOrder(Order order, @RequestParam int id) {
        var ord = order;
        order.setUser(userDao.getUserById(id));
        orderDao.editOrder(order);
        return "redirect:list";
    }


}
