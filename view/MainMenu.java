package view;

import entity.Order;
import service.OrderService;
import service.VoucherService;

import java.util.List;

public class MainMenu {
    private final OrderService orderService;
    private final VoucherService voucherService;

    public MainMenu(OrderService orderService, VoucherService voucherService) {
        this.orderService = orderService;
        this.voucherService = voucherService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("==== MENU UTAMA ====");
            System.out.println("1. Buat Pesanan Baru");
            System.out.println("2. Edit Pesanan");
            System.out.println("3. Tampilkan Riwayat Pesanan");
            System.out.println("4. Rating (Ulasan)");
            System.out.println("5. Batal Pemesanan");
            System.out.println("6. Keluar");

            String choice = InputHelper.input("Pilih menu");

            switch (choice) {
                case "1":
                    createNewOrder();
                    break;
                case "2":
                    editOrder();
                    break;
                case "3":
                    showOrderHistory();
                    break;
                case "4":
                    rateOrder();
                    break;
                case "5":
                    cancelOrder();
                    break;
                case "6":
                    System.out.println("Terima kasih! Program selesai.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
        }
    }

    private void showOrderHistory() {
        System.out.println("==== RIWAYAT PESANAN ====");
        List<Order> orderHistory = orderService.getOrderHistory();

        if (orderHistory.isEmpty()) {
            System.out.println("Belum ada riwayat pesanan.");
        } else {
            for (int i = 0; i < orderHistory.size(); i++) {
                Order order = orderHistory.get(i);
                System.out.println("Pesanan " + (i + 1) + ":");
                System.out.println("Nama Pelanggan: " + order.getCustomerName());
                System.out.println("ID User: " + order.getUserId());
                System.out.println("Akun: " + order.getAccount());
                System.out.println("No. Telp: " + order.getPhone());
                System.out.println("Game: " + order.getGame());
                System.out.println("Target Rank: +" + order.getTargetRankIncrease() + " Rank");
                System.out.println("Request: " + order.getRequest());
                System.out.println("Waktu Selesai: " + order.getDaysToComplete() + " hari");
                System.out.println("Metode Pembayaran: " + order.getPaymentMethod());
                System.out.println("Total Harga: Rp. " + order.getTotalPrice());
                System.out.println("Rating User: " + order.getRating() + " (Ulasan User: " + order.getReview() + ")");
                System.out.println("====================================");
            }
        }
    }

    private void createNewOrder() {
        System.out.println("==== BUAT PESANAN BARU ====");

        // Pendaftaran Akun
        String name = InputHelper.input("Nama Pelanggan");
        String userId = InputHelper.input("ID User");
        String account = InputHelper.input("Akun");
        String phone = InputHelper.input("No. Telp");

        // Create a new order object
        Order order = new Order();
        order.setCustomerName(name);
        order.setUserId(userId);
        order.setAccount(account);
        order.setPhone(phone);

        // Pilih Game
        System.out.println("==== PILIH GAME ====");
        System.out.println("1. PUBG");
        System.out.println("2. Mobile Legends");
        System.out.println("3. Free Fire");
        String gameChoice = InputHelper.input("Pilih game");
        switch (gameChoice) {
            case "1":
                order.setGame("PUBG");
                break;
            case "2":
                order.setGame("Mobile Legends");
                break;
            case "3":
                order.setGame("Free Fire");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                createNewOrder(); // Retry if invalid choice
                return;
        }

        // Tentukan Target Rank
        chooseTargetRank(order);

        // Request ke Joki
        String request = InputHelper.input("Masukkan request khusus untuk joki (optional)");
        order.setRequest(request);

        // Tentukan Waktu Selesai
        int daysToComplete = Integer.parseInt(InputHelper.input("Berapa hari untuk menyelesaikan pesanan?"));
        order.setDaysToComplete(daysToComplete);

        // Pilih Metode Pembayaran
        System.out.println("==== PILIH METODE PEMBAYARAN ====");
        System.out.println("1. Transfer Bank");
        System.out.println("2. E-Wallet");
        System.out.println("3. Kartu Kredit");
        String paymentChoice = InputHelper.input("Pilih metode pembayaran");
        switch (paymentChoice) {
            case "1":
                order.setPaymentMethod("Transfer Bank");
                break;
            case "2":
                order.setPaymentMethod("E-Wallet");
                break;
            case "3":
                order.setPaymentMethod("Kartu Kredit");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                createNewOrder(); // Retry if invalid choice
                return;
        }

        // Apply Voucher
        applyVoucher(order);

        // Create order in the service
        orderService.createOrder(order);

        // Show success message
        System.out.println("Pesanan berhasil dibuat!");

        // Return to main menu
        showMenu();


}

    private void editOrder() {
        Order order = orderService.getLatestOrder();
        if (order == null) {
            System.out.println("Tidak ada pesanan untuk diedit.");
            return;
        }

        System.out.println("==== EDIT PESANAN ====");
        System.out.println("1. Edit Game");
        System.out.println("2. Edit Pendaftaran Akun");
        System.out.println("3. Edit Target Rank");
        System.out.println("4. Edit Request ke Joki");
        System.out.println("5. Edit Waktu Selesai");
        System.out.println("6. Edit Metode Pembayaran");
        System.out.println("7. Batalkan Edit");
        String editChoice = InputHelper.input("Pilih yang ingin diubah");

        switch (editChoice) {
            case "1":
                System.out.println("==== PILIH GAME ====");
                System.out.println("1. PUBG");
                System.out.println("2. Mobile Legends");
                System.out.println("3. Free Fire");
                String gameChoice = InputHelper.input("Pilih game");
                switch (gameChoice) {
                    case "1":
                        order.setGame("PUBG");
                        break;
                    case "2":
                        order.setGame("Mobile Legends");
                        break;
                    case "3":
                        order.setGame("Free Fire");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
                break;
            case "2":
                String newName = InputHelper.input("Nama baru");
                String newUserId = InputHelper.input("ID User baru");
                String newAccount = InputHelper.input("Akun baru");
                String newPhone = InputHelper.input("No. Telp baru");
                order.setCustomerName(newName);
                order.setUserId(newUserId);
                order.setAccount(newAccount);
                order.setPhone(newPhone);
                break;
            case "3":
                chooseTargetRank(order);
                break;
            case "4":
                String newRequest = InputHelper.input("Request baru ke Joki");
                order.setRequest(newRequest);
                break;
            case "5":
                int newDays = Integer.parseInt(InputHelper.input("Jumlah hari baru untuk selesai"));
                order.setDaysToComplete(newDays);
                break;
            case "6":
                System.out.println("==== PILIH METODE PEMBAYARAN ====");
                System.out.println("1. Transfer Bank");
                System.out.println("2. E-Wallet");
                System.out.println("3. Kartu Kredit");
                String paymentChoice = InputHelper.input("Pilih metode pembayaran");
                switch (paymentChoice) {
                    case "1":
                        order.setPaymentMethod("Transfer Bank");
                        break;
                    case "2":
                        order.setPaymentMethod("E-Wallet");
                        break;
                    case "3":
                        order.setPaymentMethod("Kartu Kredit");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
                break;
            case "7":
                System.out.println("Batal melakukan edit.");
                return;
            default:
                System.out.println("Pilihan tidak valid, coba lagi.");
                editOrder();
        }

        System.out.println("Pesanan berhasil diedit!");
    }

    private void cancelOrder() {
        Order order = orderService.getLatestOrder();
        if (order == null) {
            System.out.println("Tidak ada pesanan untuk dibatalkan.");
            return;
        }

        System.out.println("==== BATAL PEMESANAN ====");
        System.out.println("Apakah Anda yakin ingin membatalkan pesanan ini?");
        System.out.println("1. Ya, batalkan pesanan");
        System.out.println("2. Tidak, kembali ke menu");

        String cancelChoice = InputHelper.input("Pilih opsi");

        switch (cancelChoice) {
            case "1":
                orderService.cancelOrder(order); // Assuming cancelOrder is implemented in OrderService
                System.out.println("Pesanan berhasil dibatalkan.");
                break;
            case "2":
                System.out.println("Pesanan tidak dibatalkan, kembali ke menu.");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                cancelOrder();
        }
    }

    private void chooseTargetRank(Order order) {
        System.out.println("==== TENTUKAN TARGET RANK ====");
        System.out.println("1. +1 Rank (Rp. 15.000)");
        System.out.println("2. +2 Rank (Rp. 30.000)");
        System.out.println("3. +3 Rank (Rp. 45.000)");
        String rankChoice = InputHelper.input("Pilih target rank");

        switch (rankChoice) {
            case "1":
                order.setTargetRankIncrease(1);
                order.setTotalPrice(15000);
                break;
            case "2":
                order.setTargetRankIncrease(2);
                order.setTotalPrice(30000);
                break;
            case "3":
                order.setTargetRankIncrease(3);
                order.setTotalPrice(45000);
                break;
            default:
                System.out.println("Pilihan tidak valid, coba lagi.");
                chooseTargetRank(order);
        }
    }

    private void applyVoucher(Order order) {
        System.out.println("==== MASUKKAN KODE VOUCHER ====");
        String voucherCode = InputHelper.input("Masukkan kode voucher (contoh: MTJ)");

        if (voucherService.applyVoucher(voucherCode)) {
            order.setTotalPrice(0); // Diskon 100%
            order.setVoucherApplied(true);
            System.out.println("Selamat! Anda mendapatkan diskon 100%. Total harga: Rp. " + order.getTotalPrice());
        } else {
            System.out.println("Kode voucher salah. Harga normal tetap: Rp. " + order.getTotalPrice());
        }
    }

    private void rateOrder() {
        System.out.println("==== RATING (ULASAN) ====");
        Order order = orderService.getLatestOrder();
        if (order == null) {
            System.out.println("Tidak ada pesanan untuk diberi ulasan.");
            return;
        }

        String rating = InputHelper.input("Berikan rating (1-5)");
        String review = InputHelper.input("Tulis ulasan");

        order.setRating(Integer.parseInt(rating));
        order.setReview(review);

        System.out.println("Terima kasih atas ulasan Anda!");
    }
}
