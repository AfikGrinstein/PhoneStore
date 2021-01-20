package il.co.hit.controller;

import il.co.hit.model.events.EmailNotificationObserver;
import il.co.hit.model.events.SMSNotificationObserver;
import il.co.hit.model.exception.InvalidSessionException;
import il.co.hit.model.objects.LabPhone;
import il.co.hit.model.objects.LabStatus;
import il.co.hit.model.service.LabService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class LabController {

    private static LabController INSTANCE;
    private static final Object lockObject = new Object();

    private final LabService labService;
    private Set<String> activeSessions;

    private LabController() {
        this.activeSessions = new HashSet<>();
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

    public boolean addPhoneToLab(String session, String phoneId, String contactName, String contactPhoneNumber, String email) throws InvalidSessionException {
        this.validateSession(session);


        return this.labService.addPhone(phoneId, contactName, contactPhoneNumber, email);
    }

    public List<LabPhone> filterByStatus(String session, String filterStatus) throws InvalidSessionException {
        this.validateSession(session);
        String filter = filterStatus.trim().toUpperCase();
        if ("ALL".equals(filter)) {
            return new ArrayList<>(this.labService.getAllLabPhones());
        }

        LabStatus labStatus = LabStatus.valueOf(filter);

        Set<LabPhone> result = this.labService.getPhonesByStatus(labStatus);
        return new ArrayList<>(result);
    }

    public LabPhone getLabPhone(String session, String labPhoneId) throws InvalidSessionException {
        this.validateSession(session);
        if (labPhoneId == null) {
            return null;
        }

        try {
            return this.labService.getPhone(labPhoneId);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    public boolean updateStatus(String session, String labId, String newStatus) throws InvalidSessionException {
        this.validateSession(session);
        LabStatus status = LabStatus.valueOf(newStatus.trim().toUpperCase());
        if (status == LabStatus.NEW) {
            return false;
        }

        return this.labService.updateStatus(labId, status);
    }

    public String createSession(String password) {
        if ("1234".equals(password)) {
            String session = UUID.randomUUID().toString();
            this.activeSessions.add(session);
            return session;
        }

        return null;
    }

    public void logout(String session) {
        this.activeSessions.remove(session);
    }

    private void validateSession(String session) throws InvalidSessionException {
        if (!this.activeSessions.contains(session)) {
            throw new InvalidSessionException();
        }
    }
}
