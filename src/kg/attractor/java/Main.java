package kg.attractor.java;

 import kg.attractor.java.homework.RestaurantOrders;
 import kg.attractor.java.homework.domain.Item;
 import kg.attractor.java.homework.domain.Order;

 import static java.util.stream.Collectors.*;
 import static java.util.Comparator.*;




public class Main {

    public static void main(String[] args) {

        var orders = RestaurantOrders.read("orders_100.json").getOrders();

    }



}
