package il.co.hit.controller;

import il.co.hit.model.events.EmailNotificationObserver;
import il.co.hit.model.events.SMSNotificationObserver;
import il.co.hit.model.objects.LabPhone;
import il.co.hit.model.objects.LabStatus;
import il.co.hit.model.service.LabService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LabController {

    private static LabController INSTANCE;
    private static final Object lockObject = new Object();

    private final LabService labService;

    private LabController() {
        this.labService = new LabService();
        this.labService.addStatusUpdateObserver(new EmailNotificationObserver());
        this.labService.addStatusUpdateObserver(new SMSNotificationObserver());
    }

    public static LabController getInstance() {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new LabController();
                }
            }
        }

        return INSTANCE;
    }

    public boolean addPhoneToLab(String phoneId, String contactName, String contactPhoneNumber, String email) {
        return this.labService.addPhone(phoneId, contactName, contactPhoneNumber, email);
    }

    public List<LabPhone> filterByStatus(String filterStatus) {
        String filter = filterStatus.trim().toUpperCase();
        if ("ALL".equals(filter)) {
            return new ArrayList<>(this.labService.getAllLabPhones());
        }

        LabStatus labStatus = LabStatus.valueOf(filter);

        Set<LabPhone> result = this.labService.getPhonesByStatus(labStatus);
        return new ArrayList<>(result);
    }

    public LabPhone getLabPhone(String labPhoneId) {
        if (labPhoneId == null) {
            return null;
        }

        try {
            return this.labService.getPhone(labPhoneId);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    public boolean updateStatus(String labId, String newStatus) {
        LabStatus status = LabStatus.valueOf(newStatus.trim().toUpperCase());
        if (status == LabStatus.NEW) {
            return false;
        }

        return this.labService.updateStatus(labId, status);
    }
}
