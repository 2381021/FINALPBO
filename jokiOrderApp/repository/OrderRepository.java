package jokiOrderApp.repository;


import jokiOrderApp.config.Database;
import jokiOrderApp.entity.Order;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Component
public class OrderRepository implements OrderRepoInterface{

    Database database;

    public OrderRepository(Database database) {
        this.database = database;
    }

    private final List<Order> orders = new ArrayList<>();

    // Method to save an order
    @Override
    public void save(Order order) {
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO tbljoki(customer_name, user_id, account, phone, game, target_rank_increase, request, days_to_complete, payment_method, total_price, voucher_applied, rating, review) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1,order.getCustomerName());
            preparedStatement.setString(2,order.getUserId());
            preparedStatement.setString(3,order.getAccount());
            preparedStatement.setString(4,order.getPhone());
            preparedStatement.setString(5,order.getGame());
            preparedStatement.setInt(6,order.getTargetRankIncrease());
            preparedStatement.setString(7,order.getRequest());
            preparedStatement.setInt(8,order.getDaysToComplete());
            preparedStatement.setString(9,order.getPaymentMethod());
            preparedStatement.setDouble(10,order.getTotalPrice());
            preparedStatement.setBoolean(11,order.isVoucherApplied());
            preparedStatement.setInt(12,order.getRating());
            preparedStatement.setString(13,order.getReview());

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Insert successful !");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        };
    }

    // Method to retrieve all orders
    @Override
    public List<Order> findAll() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM tbljoki";
        ArrayList<Order> orderList = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order orderRow = new Order();

                orderRow.setCustomerName(resultSet.getString(2));
                orderRow.setUserId(resultSet.getString(3));
                orderRow.setAccount(resultSet.getString(4));
                orderRow.setPhone(resultSet.getString(5));
                orderRow.setGame(resultSet.getString(6));
                orderRow.setTargetRankIncrease(resultSet.getInt(7));
                orderRow.setRequest(resultSet.getString(8));
                orderRow.setDaysToComplete(resultSet.getInt(9));
                orderRow.setPaymentMethod(resultSet.getString(10));
                orderRow.setTotalPrice(resultSet.getDouble(11));
                orderRow.setVoucherApplied(resultSet.getBoolean(12));
                orderRow.setRating(resultSet.getInt(13));
                orderRow.setReview(resultSet.getString(14));




                orderList.add(orderRow);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return orderList;
    }

    // Method to delete all orders
    @Override
    public void deleteAll() {
        String sqlStatement = "DELETE FROM tbljoki";
        Connection connection = database.getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.executeUpdate();


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // Method to check if an order exists
    @Override
    public boolean exists(Order order) {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM tbljoki";
        ArrayList<Order> orderList = new ArrayList<>();
        boolean check = false;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String nama = resultSet.getString(2);

                if(nama != null)check = true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return check;
    }

    @Override
    public void addReview(Order order) {
        String sqlStatement = "UPDATE tbljoki set rating = ?, review = ? WHERE account = ?";
        Connection conn = database.getConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);

            preparedStatement.setInt(1,order.getRating());
            preparedStatement.setString(2, order.getReview());
            preparedStatement.setString(3,order.getAccount());



            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Update successful !");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Order order) {
        Connection connection = database.getConnection();
        String sqlStatement = "UPDATE tbljoki SET customer_name = ?, user_id = ?, account = ?, phone = ?, game = ?, target_rank_increase = ?, request = ?, days_to_complete = ?, payment_method = ?, total_price = ? WHERE account = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, order.getCustomerName());
            preparedStatement.setString(2, order.getUserId());
            preparedStatement.setString(3, order.getAccount());
            preparedStatement.setString(4, order.getPhone());
            preparedStatement.setString(5, order.getGame());
            preparedStatement.setInt(6, order.getTargetRankIncrease());
            preparedStatement.setString(7, order.getRequest());
            preparedStatement.setInt(8, order.getDaysToComplete());
            preparedStatement.setString(9, order.getPaymentMethod());
            preparedStatement.setDouble(10, order.getTotalPrice());
            preparedStatement.setString(11, order.getAccount());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update pesanan berhasil!");
            }
        } catch (Exception e) {
            System.out.println("Gagal mengupdate pesanan: " + e.getMessage());
        }
    }

    // Method to delete a specific order
    @Override
    public void delete(Order order) {
        String sqlStatement = "DELETE FROM tbljoki WHERE account = ?";
        Connection connection = database.getConnection();




        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1,order.getAccount());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Delete Successful !");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

