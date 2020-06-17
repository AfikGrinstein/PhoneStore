package il.co.hit.view;

import il.co.hit.controller.ShopController;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ShopView {

    private final ShopController shopController;

    public ShopView() {
        this.shopController = ShopController.getInstance();
    }

    public void newPhone(Scanner scanner) {
        System.out.print("Name: ");
        String phoneName = scanner.nextLine();
        System.out.print("Brand (Apple | Samsung | Xiaomi | LG): ");
        String brand = scanner.nextLine();
        System.out.print("Release Date (dd/mm/yy): ");
        String releaseDateInput = scanner.nextLine();
        LocalDate releaseDate = LocalDate.parse(releaseDateInput, DateTimeFormatter.ofPattern("dd/MM/yy"));
        System.out.print("RAM: ");
        String ram = scanner.nextLine();
        System.out.print("OS (Android | IOS): ");
        String os = scanner.nextLine();

        boolean success = this.shopController.addNewPhone(phoneName, brand, releaseDate, Double.parseDouble(ram), os);
        if (success) {
            System.out.println("Phone " + phoneName + " added successfully");
        } else {
            System.out.println("Failed to add " + phoneName);
        }
        System.out.println();
    }

    public void delete(Scanner scanner) throws IOException {
        System.out.print("Phone id: ");
        String id = scanner.nextLine();

        boolean success = this.shopController.delete(id);
        if (success) {
            System.out.println(MessageFormat.format("Phone {0} deleted from store", id));
        } else {
            System.out.println("Failed to delete " + id);
        }
        System.out.println();
    }
}
