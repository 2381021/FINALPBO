package jokiOrderApp.repository;

import jokiOrderApp.entity.Order;

import java.util.List;

public interface OrderRepoInterface {
    void save(Order order);
    List<Order> findAll();
    void deleteAll();
    boolean exists(Order order);
    void addReview(Order order);
    void update(Order order);
    void delete(Order order);
}
