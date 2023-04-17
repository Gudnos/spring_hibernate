package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Ivan", "Petrov", "user1@mail.ru");
      User user2 = new User("Petr", "Ivanov", "user2@mail.ru");
      User user3 = new User("Oleg", "Sidorov", "user3@mail.ru");

      Car car1 = new Car("Lada", 2111);
      Car car2 = new Car("Lada", 2112);
      Car car3 = new Car("Lada", 2113);

      user1.setUserCar(car1);
      user2.setUserCar(car2);
      user3.setUserCar(car3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println(user.getUserCar());
         System.out.println();
      }


      Car car = new Car("Lada", 2111);
      User user = userService.getUserByCar(car);
      System.out.println("Id = "+user.getId());
      System.out.println("First Name = "+user.getFirstName());
      System.out.println("Last Name = "+user.getLastName());
      System.out.println("Email = "+user.getEmail());
      System.out.println(user.getUserCar());

      context.close();
   }
}
