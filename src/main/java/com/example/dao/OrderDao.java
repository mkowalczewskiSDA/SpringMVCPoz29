package com.example.dao;

import com.example.model.Order;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Map<Integer, Order> getOrders() {
        return orders;
    }

    public void deleteOrder(int id) {
        orders.remove(id);
    }

    public Order getOrderById(int id) {
        return orders.get(id);
    }

    public void editOrder(Order order) {
        if (order.getId() == null) {
            int id = orders.keySet().stream().min(((o1, o2) -> o2-o1)).orElse(0)+1;
            order.setId(id);
        }
        orders.put(order.getId(), order);
    }
}
