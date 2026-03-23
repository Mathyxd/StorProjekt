package UI;
import Service.OrderManager;
import Service.PaymentService;
import main.*;
import Model.*;
import util.*;
import File.*;

import java.util.Scanner;


public class PizzaUi extends BaseUi {

    private final OrderUi orderUi;
    private final CustomerUi customerUi;
    private final MenuUi menuUi;
    private boolean running;


    public PizzaUi() {
        super(new Scanner(System.in));
        OrderManager orderManager = new OrderManager();
        PaymentService paymentService = new PaymentService();
        Menu menu = new Menu();


        customerUi = new CustomerUi(scanner);
        menuUi = new MenuUi(scanner, menu);
        orderUi = new OrderUi(scanner, orderManager, paymentService, menu, customerUi, menuUi);
        running = true;
    }

    public void start() {
        while (running) {
            showMainMenu();
            int choice = readInt("Vælg et menupunkt: ");
            handleChoice(choice);
        }
    }

    private void showMainMenu() {
        System.out.println();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     🍕 Mario's Pizzabar      ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println("1. Vis menukort");
        System.out.println("2. Opret ordre");
        System.out.println("3. Vis aktive ordrer");
        System.out.println("4. Marker ordre som klar");
        System.out.println("5. Marker ordre som leveret");
        System.out.println("6. Vis ordre Historik");
        System.out.println("7. Vis kundeoversigt");
        System.out.println("8. Afslut");
    }

    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                menuUi.showMenuCard();
                break;
            case 2:
                orderUi.createOrder();
                break;
            case 3:
                orderUi.showActiveOrders();
                break;
            case 4:
                orderUi.markOrderReady();
                break;
            case 5:
                orderUi.markOrderComplete();
                break;
            case 6:
                orderUi.showOrderHistory();
                break;
            case 7:
                customerUi.showCustomerOverview();
                break;
            case 8:
                exitProgram();
                break;
            default:
                System.out.println("Ugyldigt valg. Prøv igen.");
        }
    }
    public void exitProgram() {
        running = false;
        System.out.println("Programmet lukker.");
    }

 }








