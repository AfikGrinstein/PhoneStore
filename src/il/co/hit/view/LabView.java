package il.co.hit.view;

import il.co.hit.controller.LabController;
import il.co.hit.model.objects.LabPhone;
import il.co.hit.model.objects.Phone;

import java.util.List;
import java.util.Scanner;

public class LabView {

    private final LabController labController;

    public LabView() {
        this.labController = LabController.getInstance();
    }

    public void addPhoneToLab(Scanner scanner) {
        System.out.print("Phone id: ");
        String phoneId = scanner.nextLine();
        System.out.print("Contact name: ");
        String contactName = scanner.nextLine();
        System.out.print("Contact phone number: ");
        String contactPhoneNumber = scanner.nextLine();
        System.out.print("Email (Optional): ");
        String email = scanner.nextLine();

        boolean success = this.labController.addPhoneToLab(phoneId, contactName, contactPhoneNumber, email);
        if (success) {
            System.out.println("Phone " + phoneId + " added successfully to lab");
        } else {
            System.out.println("Failed to add " + phoneId);
        }
        System.out.println();
    }

    public void filterLabPhone(Scanner scanner) {
        System.out.print("Status (New | In_Progress | Finished || All): ");
        String filterStatus = scanner.nextLine();

        List<LabPhone> labPhones = this.labController.filterByStatus(filterStatus);
        System.out.println("Available phones in lab filter by status (" + filterStatus + "):");
        for (LabPhone labPhone : labPhones) {
            System.out.println(labPhone);
        }

        System.out.println();
    }

    public void getLabPhone(Scanner scanner) {
        System.out.print("Lab id: ");
        String labPhoneId = scanner.nextLine();

        LabPhone labPhone = this.labController.getLabPhone(labPhoneId);
        System.out.println(labPhone);
        System.out.println();
    }
}
