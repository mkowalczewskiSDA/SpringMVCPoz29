package com.example.dao;

import com.example.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderDao {

    @Autowired
    UserDao userDao;

    Map<Integer, Order> orders = new HashMap<>();

    @PostConstruct
    public void init() {
        orders.put(1, new Order(1, 10, "test1", userDao.getUserById(4)));
        orders.put(2, new Order(2, 50, "test2", userDao.getUserById(3)));
        orders.put(3, new Order(3, 34, "test3", userDao.getUserById(1)));
        orders.put(4, new Order(4, 4, "test4", userDao.getUserById(2)));
    }
}
