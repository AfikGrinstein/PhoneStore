package il.co.hit.model.service;

import il.co.hit.model.objects.Contact;
import il.co.hit.model.objects.LabPhone;
import il.co.hit.model.objects.LabStatus;
import il.co.hit.model.repository.LabRepository;
import il.co.hit.model.repository.LabRepositoryImpl;
import il.co.hit.model.repository.PhoneRepository;
import il.co.hit.model.repository.PhoneRepositoryImpl;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Set;

public class LabService {

    private final LabRepository labRepository;
    private final PhoneRepository phoneRepository;

    public LabService() {
        this.labRepository = new LabRepositoryImpl();
        this.phoneRepository = new PhoneRepositoryImpl();
    }

    public boolean addPhone(String phoneId, String contactName, String contactPhoneNumber, String email) {
        try {
            // Check if phone belong to us
            if (!this.phoneRepository.exists(phoneId)) {
                throw new NoSuchElementException("Phone " + phoneId + " not exists");
            }

            LabPhone labPhone = LabPhone.builder()
                    .phoneId(phoneId)
                    .status(LabStatus.NEW)
                    .contact(new Contact(contactName, contactPhoneNumber, email))
                    .creationDate(LocalDateTime.now())
                    .build();

            this.labRepository.save(labPhone);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Set<LabPhone> getPhonesByStatus(LabStatus labStatus) {
        return this.labRepository.filterByStatus(labStatus);
    }

    public LabPhone getPhone(String labPhoneId) throws NoSuchFieldException {
        return this.labRepository.find(labPhoneId);
    }

    public Set<LabPhone> getAllLabPhones() {
        return this.labRepository.findAll();
    }
}