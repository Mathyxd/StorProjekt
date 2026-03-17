package Service;
import java.util.ArrayList; // Vi bruger ArrayList til at gemme ordrer
import java.util.Comparator; // Bruges til at sortere ordrer
import java.util.List;// Interface for lister
public class OrderManager {


    public class OrderManager { // Klasse der styrer alle ordrer

        private List<Order> activeOrders; // Liste over aktive (ikke færdige) ordrer
        private List<Order> completedOrders; // Liste over færdige ordrer (arkiv)

        public OrderManager() { // Constructor - kører når objektet oprettes
            activeOrders = new ArrayList<>(); // Opretter tom liste til aktive ordrer
            completedOrders = new ArrayList<>(); // Opretter tom liste til arkiv
        }

        // 🔹 Tilføj en ny ordre
        public void addOrder(Order order) { // Metode der modtager en ordre
            if (order == null) { // Tjekker om ordren ikke findes
                System.out.println("Ordre er null!"); // Fejlbesked
                return; // Stopper metoden
            }

            activeOrders.add(order); // Tilføjer ordren til listen over aktive ordrer
        }

        // 🔹 Vis aktive ordrer sorteret efter afhentningstid
        public void showActiveOrders() { // Metode til at vise ordrer
            activeOrders.stream() // Laver listen om til en stream (så vi kan arbejde med den)
                    .sorted(Comparator.comparing(Order::getPickupTime))
                    // Sorterer ordrer efter afhentningstid (tidligst først)
                    .forEach(System.out::println);
            // Printer hver ordre (kalder toString automatisk)
        }

        //  Gør en ordre færdig
        public void completeOrder(int orderNumber) { // Finder ordre via ID
            Order found = null; // Variabel til at gemme den ordre vi leder efter

            for (Order o : activeOrders) { // Gennemgår alle aktive ordrer
                if (o.getOrderNumber() == orderNumber) { // Tjekker om ID matcher
                    found = o; // Gemmer den fundne ordre
                    break; // Stopper loopet (vi har fundet den)
                }
            }

            if (found != null) { // Hvis vi fandt en ordre
                found.setStatus(OrderStatus.READY); // Sætter status til "klar"

                activeOrders.remove(found); // Fjerner den fra aktive ordrer
                completedOrders.add(found); // Tilføjer den til arkivet
            } else { // Hvis vi IKKE fandt en ordre
                System.out.println("Ordre ikke fundet!"); // Fejlbesked
            }
        }

        // 🔹 Vis alle færdige ordrer
        public void showCompletedOrders() { // Metode til at vise arkiv
            for (Order o : completedOrders) { // Gennemgår alle færdige ordrer
                System.out.println(o); // Printer hver ordre
            }
        }
    }
}