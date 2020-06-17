package il.co.hit.view;

import il.co.hit.controller.LabController;
import il.co.hit.controller.ShopController;
import il.co.hit.model.exception.InvalidSessionException;
import il.co.hit.model.objects.Phone;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StoreView {

    private final ShopView shopView;
    private final LabView labView;
    private final ShopController shopController;
    private final LabController labController;

    private String session;

    public StoreView() {
        this.shopView = new ShopView();
        this.labView = new LabView();
        this.shopController = ShopController.getInstance();
        this.labController = LabController.getInstance();
    }

    public void start() throws IOException {
        System.out.println("Welcome to Phone Store :-)");
        boolean stayInStore = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (stayInStore) {
                System.out.println("What would you like to do?");
                System.out.println("1. Shop");
                System.out.println("2. Lab (password required)");
                System.out.println("Q. Exit");

                String userSelection = scanner.nextLine();
                switch (userSelection) {
                    case "1":
                        this.shop(scanner);
                        break;
                    case "2":
                        this.lab(scanner);
                        break;
                    case "q":
                    case "Q":
                        stayInStore = false;
                        break;
                    default:
                        System.out.println("Selection is not valid, Please select a option from the available options");
                }
            }
        }
    }

    public void shop(Scanner scanner) throws IOException {
        boolean stayInShop = true;

        while (stayInShop) {
            System.out.println("What would you like to do?");
            System.out.println("1. Add new phone to store");
            System.out.println("2. Get all store phones");
            System.out.println("3. Delete phone from store");
            System.out.println("9. Go back to main menu");

            String userSelection = scanner.nextLine();
            switch (userSelection) {
                case "1":
                    this.shopView.newPhone(scanner);
                    break;
                case "2":
                    List<Phone> phones = this.shopController.getAllPhones();
                    System.out.println("Available phones in store:");
                    for (Phone phone : phones) {
                        System.out.println(phone);
                    }
                    System.out.println();
                    break;
                case "3":
                    this.shopView.delete(scanner);
                    break;
                case "9":
                    stayInShop = false;
                    break;
                default:
                    System.out.println("Selection is not valid, Please select a option from the available options");
            }
        }
    }

    public void lab(Scanner scanner) {
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        this.session = this.labController.createSession(password);
        if (this.session == null) {
            System.out.println("Password is invalid, returning to main menu");
            System.out.println();
            return;
        }

        boolean stayInLab = true;
        while (stayInLab) {
            System.out.println("What would you like to do?");
            System.out.println("1. Add new phone to lab");
            System.out.println("2. Check phone status");
            System.out.println("3. List lab phones by status");
            System.out.println("4. Update phone status");
            System.out.println("9. Go back to main menu");

            String userSelection = scanner.nextLine();
            try {
                switch (userSelection) {
                    case "1":
                        this.labView.addPhoneToLab(scanner, session);
                        break;
                    case "2":
                        this.labView.getLabPhoneStatus(scanner, session);
                        break;
                    case "3":
                        this.labView.filterLabPhone(scanner, session);
                        break;
                    case "4":
                        this.labView.updateStatus(scanner, session);
                        break;
                    case "9":
                        stayInLab = false;
                        break;
                    default:
                        System.out.println("Selection is not valid, Please select a option from the available options");
                }
            } catch (InvalidSessionException e) {
                System.out.println("Session " + session + " invalid, returning to main menu");
                stayInLab = false;
            }
        }

        this.labController.logout(session);
    }
}
