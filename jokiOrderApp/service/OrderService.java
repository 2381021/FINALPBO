package jokiOrderApp.service;

import jokiOrderApp.entity.Order;
import jokiOrderApp.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrderService implements OrderServiceInterface {

    private final OrderRepository repository;

    // Constructor
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    // Method to create a new order
    @Override
    public void createOrder(Order order) {
        repository.save(order);
    }

    // Method to retrieve all orders from the jokiOrderApp.repository (order history)
    @Override
    public List<Order> getOrderHistory() {
        return repository.findAll();
    }

    // Method to cancel all orders (if needed, but use with caution)
    @Override
    public void cancelAllOrders() {
        repository.deleteAll();
    }

    // Method to retrieve the latest order (most recent order placed)
    @Override
    public Order getLatestOrder() {
        List<Order> orders = repository.findAll();
        return orders.isEmpty() ? null : orders.get(orders.size() - 1);
    }

    @Override
    public void updateOrder(Order order) {
        repository.update(order);
    }

    @Override
    public void editReview(Order latestOrder){
        repository.addReview(latestOrder);
    }

    // New method to cancel a specific order
    @Override
    public void cancelOrder(Order order) {
        // Check if the order exists in the jokiOrderApp.repository
        if (repository.exists(order)) {
            repository.delete(order);  // Delete the specific order
            System.out.println("Pesanan dengan ID " + order.getUserId() + " berhasil dibatalkan.");
        } else {
            System.out.println("Pesanan tidak ditemukan.");
        }
    }
}
