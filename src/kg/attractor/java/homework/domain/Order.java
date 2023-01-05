package kg.attractor.java.homework.domain;

import kg.attractor.java.homework.util.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.*;

public class Order {

    private final Customer customer;
    private final List<Item> items;
    private final boolean homeDelivery;
    private transient double total = 0.0d;

    public Order(Customer customer, List<Item> orderedItems, boolean homeDelivery) {
        this.customer = customer;
        this.items = List.copyOf(orderedItems);
        this.homeDelivery = homeDelivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return homeDelivery == order.homeDelivery &&
                Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, homeDelivery);
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isHomeDelivery() {
        return homeDelivery;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotal() {
        return total;
    }

    public void calculateTotal() {
        try {
            var total = items.stream()
                    .mapToDouble(item -> item.getPrice() * item.getAmount())
                    .sum();
            System.out.println(total);
        }catch (NotImplementedException e){
            e.printStackTrace();
        }
        System.out.println(total);

    }


    public void printOrders() {
        var printOrders = items.stream()
                .map(Item :: getAmount)
                .collect(toList());
        printOrders.forEach(System.out::println);
    }

    public void getTopNOrders(List<Order> orders) {
        List<Order> topOrders = orders.stream()
                .sorted(comparingDouble(Order::getTotal).reversed())
                .limit(200)
                .collect(toList());
    }

    public void getLowNOrders(List<Order> orders) {
        List<Order> lowOrders = orders.stream()
                .sorted(comparingDouble(Order::getTotal))
                .limit(10)
                .collect(toList());
    }

    public List<Order> getHomeDeliveryOrders(List<Order> orders) {
        return orders.stream()
                .filter(o -> o.isHomeDelivery())
                .collect(toList());

    }

    public void findMostAndLeastProfitableOrdersForHomeDelivery(List<Order> orders) {
        var mostProfitable = orders.stream()
                .filter(order -> order.isHomeDelivery())
                .max(comparingDouble(Order::getTotal)).get();
        System.out.println(mostProfitable);

        var leastProfitable = orders.stream()
                .filter(order -> order.isHomeDelivery())
                .min(comparingDouble(Order::getTotal)).get();
        System.out.println(leastProfitable);

    }


    public List<Order> findOrdersByTotalValue(List<Order> orders, double minOrderTotal, double maxOrderTotal) {
        return orders.stream()
                .filter(order -> order.getTotal() >= minOrderTotal && order.getTotal() <= maxOrderTotal)
                .collect(toList());
    }

    public double totalValueOfAllOrders(List<Order> orders) {
        return orders.stream()
                .mapToDouble(order -> order.getTotal())
                .reduce(0.0, (a, b) -> a + b);
    }

    public List<String> getSortedEmailAddresses(List<Order> orders) {
        String[] emailAddresses = orders.stream()
                .map(order -> order.getCustomer().getEmail())
                .distinct()
                .toArray(String[]::new);
        Arrays.sort(emailAddresses);
        return Arrays.asList(emailAddresses);
    }

    public Map<Customer, List<Order>> getOrdersGroupedByCustomer(List<Order> orders) {
        return orders.stream()
                .collect(groupingBy(Order::getCustomer));
    }

    public Map<Customer, Double> getTotalAmountByCustomer(List<Order> orders) {
        return orders.stream()
                .collect(groupingBy(Order::getCustomer,
                        mapping(Order::getTotal,
                                reducing(0.0, Double::sum))));
    }


    public Optional<Customer> getCustomerWithHighestTotalAmount(List<Order> orders) {
        return orders.stream()
                .collect(groupingBy(Order::getCustomer,
                        mapping(Order::getTotal,
                                reducing(0.0, Double::sum))))
                .entrySet().stream()
                .collect(maxBy(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey);
    }

    public Optional<Customer> getCustomerWithLowestTotalAmount(List<Order> orders) {
        return orders.stream()
                .collect(groupingBy(Order::getCustomer,
                        mapping(Order::getTotal,
                                reducing(0.0, Double::sum))))
                .entrySet().stream()
                .collect(minBy(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey);
    }

    public Map<String, Long> groupItemsByQuantity(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(Item::getName, Collectors.counting()));
    }

}
