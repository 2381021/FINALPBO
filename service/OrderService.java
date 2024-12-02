package service;

import entity.Order;
import repository.OrderRepository;

import java.util.List;

public class OrderService {
    private final OrderRepository repository;

    // Constructor
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    // Method to create a new order
    public void createOrder(Order order) {
        repository.save(order);
    }

    // Method to retrieve all orders from the repository (order history)
    public List<Order> getOrderHistory() {
        return repository.findAll();
    }

    // Method to cancel all orders (if needed, but use with caution)
    public void cancelAllOrders() {
        repository.deleteAll();
    }

    // Method to retrieve the latest order (most recent order placed)
    public Order getLatestOrder() {
        List<Order> orders = repository.findAll();
        return orders.isEmpty() ? null : orders.get(orders.size() - 1);
    }

    // New method to cancel a specific order
    public void cancelOrder(Order order) {
        // Check if the order exists in the repository
        if (repository.exists(order)) {
            repository.delete(order);  // Delete the specific order
            System.out.println("Pesanan dengan ID " + order.getUserId() + " berhasil dibatalkan.");
        } else {
            System.out.println("Pesanan tidak ditemukan.");
        }
    }
}
