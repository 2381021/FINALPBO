import repository.OrderRepository;
import service.OrderService;
import service.VoucherService;
import view.MainMenu;

public class Main {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository);
        VoucherService voucherService = new VoucherService();

        MainMenu mainMenu = new MainMenu(orderService, voucherService);
        mainMenu.showMenu();
    }
}
