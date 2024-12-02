package repository;

import entity.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    // Method to save an order
    public void save(Order order) {
        orders.add(order);
    }

    // Method to retrieve all orders
    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    // Method to delete all orders
    public void deleteAll() {
        orders.clear();
    }

    // Method to check if an order exists
    public boolean exists(Order order) {
        return orders.contains(order);
    }

    // Method to delete a specific order
    public void delete(Order order) {
        orders.remove(order);
    }
}
