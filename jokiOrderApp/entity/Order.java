package jokiOrderApp.entity;

public class Order {
    private String customerName;
    private String userId;
    private String account;
    private String phone;
    private String game;
    private int targetRankIncrease;
    private String request;
    private int daysToComplete;
    private String paymentMethod;
    private double totalPrice;
    private boolean voucherApplied;
    private int rating;  // Field for rating
    private String review;  // Field for review

    // Getters and setters for all fields

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserId() { // Corrected method name
        return userId;
    }

    public void setUserId(String userId) { // Corrected method name
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getTargetRankIncrease() {
        return targetRankIncrease;
    }

    public void setTargetRankIncrease(int targetRankIncrease) {
        this.targetRankIncrease = targetRankIncrease;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getDaysToComplete() {
        return daysToComplete;
    }

    public void setDaysToComplete(int daysToComplete) {
        this.daysToComplete = daysToComplete;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isVoucherApplied() {
        return voucherApplied;
    }

    public void setVoucherApplied(boolean voucherApplied) {
        this.voucherApplied = voucherApplied;
    }

    // Getter and setter for rating
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating >= 1 && rating <= 5) {  // Ensure rating is between 1 and 5
            this.rating = rating;
        } else {
            System.out.println("Rating harus antara 1 dan 5."); // Message in Indonesian
        }
    }

    // Getter and setter for review
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}