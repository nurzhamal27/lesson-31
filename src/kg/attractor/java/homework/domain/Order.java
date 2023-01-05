package kg.attractor.java.homework.domain;

import kg.attractor.java.homework.util.NotImplementedException;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class Order {
    // Этот блок кода менять нельзя! НАЧАЛО!
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
    // Этот блок кода менять нельзя! КОНЕЦ!

    //----------------------------------------------------------------------
    //------   Реализация ваших методов должна быть ниже этой линии   ------
    //----------------------------------------------------------------------

    public void calculateTotal() {
//        try {
//            var total = items.stream()
//                    .mapToDouble(item -> item.getPrice() * item.getAmount())
//                    .sum();
//            System.out.println(total);
//        }catch (NotImplementedException e){
//            e.printStackTrace();
//        }
//        System.out.println(total);

    }

    public void printOrders() {
        var printOrders = items.stream()
                .map(Item :: getAmount)
                .collect(toList());
        printOrders.forEach(System.out::println);
    }

    public List<Order> getHomeDeliveryOrders(List<Order> orders) {
        return orders.stream()
                .filter(o -> o.isHomeDelivery())
                .collect(toList());

    }


}
