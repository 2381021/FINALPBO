module jokiOrderApp {
    requires spring.context;
    requires spring.beans;
    requires java.sql;
    requires org.slf4j;
    opens jokiOrderApp;
    opens jokiOrderApp.config;
    opens jokiOrderApp.entity;
    opens jokiOrderApp.repository;
    opens jokiOrderApp.service;
    opens jokiOrderApp.view;
}