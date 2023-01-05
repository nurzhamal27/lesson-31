package kg.attractor.java;

 import kg.attractor.java.homework.RestaurantOrders;
 import kg.attractor.java.homework.domain.Item;

 import static java.util.stream.Collectors.*;
 import static java.util.Comparator.*;




public class Main {

    public static void main(String[] args) {

        var orders = RestaurantOrders.read("orders_100.json").getOrders();
//        var orders = RestaurantOrders.read("orders_1000.json").getOrders();
        //var orders = RestaurantOrders.read("orders_10_000.json").getOrders();

    }
}
