package jokiOrderApp.service;

import jokiOrderApp.entity.Order;

import java.util.List;

public interface OrderServiceInterface {
    void createOrder(Order order);
    List<Order> getOrderHistory();
    void cancelAllOrders();
    Order getLatestOrder();
    void updateOrder(Order order);
    void editReview(Order latestOrder);
    void cancelOrder(Order order);
}
