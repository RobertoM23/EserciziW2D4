package Esercizi;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // 1. Dati di esempio
        List<Product> products = List.of(
                new Product(1L, "Book A", "Books", 120.0),
                new Product(2L, "Toy Car", "Boys", 30.0),
                new Product(3L, "Milk Bottle", "Baby", 15.0),
                new Product(4L, "Book B", "Books", 80.0),
                new Product(5L, "Shirt", "Boys", 25.0)
        );

        Customer customer1 = new Customer(1L, "Mario", 2);
        Customer customer2 = new Customer(2L, "Luca", 1);

        List<Order> orders = List.of(
                new Order(101L, "Shipped", LocalDate.of(2021, 2, 15), LocalDate.of(2021, 2, 20), List.of(products.get(2)), customer1),
                new Order(102L, "Delivered", LocalDate.of(2021, 3, 10), LocalDate.of(2021, 3, 15), List.of(products.get(0), products.get(4)), customer2)
        );

        // 2. Esercizio 1
        Map<Customer, List<Order>> ordiniPerCliente = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));
        System.out.println("Esercizio 1:");
        ordiniPerCliente.forEach((cliente, ordini) ->
                System.out.println("Cliente: " + cliente.getTier() + " - Ordini: " + ordini.size())
        );

        // 3. Esercizio 2
        Map<Customer, Double> totalePerCliente = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomer,
                        Collectors.summingDouble(o -> o.getProducts().stream()
                                .mapToDouble(Product::getPrice)
                                .sum())
                ));
        System.out.println("\nEsercizio 2:");
        totalePerCliente.forEach((cliente, totale) ->
                System.out.println("Cliente: " + cliente.getTier() + " - Totale: " + totale)
        );

        // 4. Esercizio 3
        Optional<Product> prodottoPiuCostoso = products.stream()
                .max(Comparator.comparing(Product::getPrice));
        System.out.println("\nEsercizio 3:");
        prodottoPiuCostoso.ifPresent(System.out::println);

        // 5. Esercizio 4
        double mediaImportoOrdini = orders.stream()
                .mapToDouble(o -> o.getProducts().stream()
                        .mapToDouble(Product::getPrice)
                        .sum())
                .average()
                .orElse(0.0);
        System.out.println("\nEsercizio 4:");
        System.out.println("Media importo: " + mediaImportoOrdini);

        // 6. Esercizio 5
        Map<String, Double> sommaPerCategoria = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.summingDouble(Product::getPrice)
                ));
        System.out.println("\nEsercizio 5:");
        sommaPerCategoria.forEach((categoria, totale) ->
                System.out.println("Categoria: " + categoria + " - Totale: " + totale)
        );
    }
}
