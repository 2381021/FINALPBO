package jokiOrderApp;

import jokiOrderApp.config.Database;
import jokiOrderApp.repository.OrderRepository;
import jokiOrderApp.service.OrderService;
import jokiOrderApp.service.VoucherService;
import jokiOrderApp.view.MainMenu;
import jokiOrderApp.view.MainMenuInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "jokiOrderApp")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        MainMenuInterface mainMenu = context.getBean(MainMenu.class);
        mainMenu.showMenu();
    }

    @Bean
    Database database (){
        Database database = new Database("dbjoki", "root","", "localhost","3306");
        database.setup();
        return database;
    }
}