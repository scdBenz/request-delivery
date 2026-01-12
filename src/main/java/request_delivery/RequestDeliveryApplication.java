package request_delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RequestDeliveryApplication {

    //TODO КОММИТЫ
    //TODO dockerfile
    //TODO Использовать значения констант из докера
    //TODO сделать скрипт для тест даты
    //TODO Продумать тесты или написать автотесты
    //TODO Дописать методы для остальных сущностей
    //TODO  Добавить фронт страничку любую(health check)
//    -----
    //TODO Подключить liquibase
    //TODO Подключить ломбок местами

	public static void main(String[] args) {
		SpringApplication.run(RequestDeliveryApplication.class, args);
	}

}
