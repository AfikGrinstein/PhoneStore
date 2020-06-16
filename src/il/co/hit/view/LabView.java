package il.co.hit.view;

import il.co.hit.controller.LabController;
import il.co.hit.model.exception.InvalidSessionException;
import il.co.hit.model.objects.LabPhone;

import java.util.List;
import java.util.Scanner;

public class LabView {

    private final LabController labController;

    public LabView() {
        this.labController = LabController.getInstance();
    }

    public void addPhoneToLab(Scanner scanner, String session) throws InvalidSessionException {
        System.out.print("Phone id: ");
        String phoneId = scanner.nextLine();
        System.out.print("Contact name: ");
        String contactName = scanner.nextLine();
        System.out.print("Contact phone number (optional): ");
        String contactPhoneNumber = scanner.nextLine();
        System.out.print("Email (optional): ");
        String email = scanner.nextLine();

        boolean success = this.labController.addPhoneToLab(session, phoneId, contactName, contactPhoneNumber, email);
        if (success) {
            System.out.println("Phone " + phoneId + " added successfully to lab");
        } else {
            System.out.println("Failed to add " + phoneId);
        }
        System.out.println();
    }

    public void filterLabPhone(Scanner scanner, String session) throws InvalidSessionException {
        System.out.print("Status (New | In_Progress | Finished || All): ");
        String filterStatus = scanner.nextLine();

        List<LabPhone> labPhones = this.labController.filterByStatus(session, filterStatus);
        System.out.println("Available phones in lab filter by status (" + filterStatus + "):");
        for (LabPhone labPhone : labPhones) {
            System.out.println(labPhone);
        }

        System.out.println();
    }

    public void getLabPhoneStatus(Scanner scanner, String session) throws InvalidSessionException {
        System.out.print("Lab id: ");
        String labPhoneId = scanner.nextLine();

        LabPhone labPhone = this.labController.getLabPhone(session, labPhoneId);
        System.out.println("Status = " + labPhone.getStatus().toString());
        System.out.println();
    }

    public void updateStatus(Scanner scanner, String session) throws InvalidSessionException {
        System.out.print("Enter lab phone id: ");
        String labId = scanner.nextLine();
        System.out.print("New status (In_Progress | Finished): ");
        String newStatus = scanner.nextLine();

        boolean success = this.labController.updateStatus(session, labId, newStatus);
        if (success) {
            System.out.println("Status updated to " + newStatus);
        } else {
            System.out.println("Failed to update status to phone " + labId);
        }
        System.out.println();
    }
}
