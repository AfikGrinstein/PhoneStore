package il.co.hit.view;

import il.co.hit.controller.ShopController;
import il.co.hit.model.objects.Phone;

import java.util.List;
import java.util.Scanner;

public class StoreView {

    private final ShopView shopView;
    private final LabView labView;
    private final ShopController shopController;

    public StoreView() {
        this.shopView = new ShopView();
        this.labView = new LabView();
        this.shopController = ShopController.getInstance();
    }

    public void start() {
        System.out.println("Welcome to Phone Store :-)");
        boolean stayInStore = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (stayInStore) {
                System.out.println("What would you like to do?");
                System.out.println("1. Shop");
                System.out.println("2. Lab");
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

    public void shop(Scanner scanner) {
        boolean stayInShop = true;

        while (stayInShop) {
            System.out.println("What would you like to do?");
            System.out.println("1. Add new phone to store");
            System.out.println("2. Get all store phones");
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
                case "9":
                    stayInShop = false;
                    break;
                default:
                    System.out.println("Selection is not valid, Please select a option from the available options");
            }
        }
    }

    public void lab(Scanner scanner) {
        boolean stayInLab = true;
        while (stayInLab) {
            System.out.println("What would you like to do?");
            System.out.println("1. Add new phone to lab");
            System.out.println("2. Check phone status");
            System.out.println("3. List lab phones by status");
            System.out.println("4. Update phone status");
            System.out.println("9. Go back to main menu");

            String userSelection = scanner.nextLine();
            switch (userSelection) {
                case "1":
                    this.labView.addPhoneToLab(scanner);
                    break;
                case "2":
                    this.labView.getLabPhoneStatus(scanner);
                    break;
                case "3":
                    this.labView.filterLabPhone(scanner);
                    break;
                case "4":
                    this.labView.updateStatus(scanner);
                    break;
                case "9":
                    stayInLab = false;
                    break;
                default:
                    System.out.println("Selection is not valid, Please select a option from the available options");
            }
        }
    }
}
