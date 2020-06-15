package il.co.hit.model.repository;

import il.co.hit.model.objects.LabPhone;
import il.co.hit.model.objects.LabStatus;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LabRepositoryImpl implements LabRepository {

    private FileManager<LabPhone> fileManager;
    private Set<LabPhone> labPhoneSet;

    public LabRepositoryImpl() {
        try {
            this.fileManager = new FileManager<>("lab_phone.data");
            this.labPhoneSet = this.fileManager.read();
        } catch (Exception e) {
            this.labPhoneSet = new HashSet<>();
        }
    }

    @Override
    public LabPhone save(LabPhone labPhone) throws Exception {
        if (labPhone == null) {
            throw new IllegalArgumentException("phone must not be null");
        }

        labPhone.setId(UUID.randomUUID().toString());
        this.labPhoneSet.add(labPhone);
        this.fileManager.write(this.labPhoneSet);
        return labPhone;
    }

    @Override
    public Set<LabPhone> findAll() {
        return this.labPhoneSet;
    }

    @Override
    public LabPhone find(String id) throws NoSuchFieldException {
        for (LabPhone labPhone: this.labPhoneSet) {
            if (labPhone.getId().equals(id)) {
                return labPhone;
            }
        }

        throw new java.lang.NoSuchFieldException("Cannot find lab phone with id " + id);
    }

    @Override
    public Set<LabPhone> filterByStatus(LabStatus status) {
        HashSet<LabPhone> response = new HashSet<>();
        for (LabPhone labPhone: this.labPhoneSet) {
            if (labPhone.getStatus() == status) {
                response.add(labPhone);
            }
        }

        return response;
    }

    @Override
    public void update(LabPhone phone) throws IOException {
        this.labPhoneSet.remove(new LabPhone(phone.getId()));
        this.labPhoneSet.add(phone);
        this.fileManager.write(this.labPhoneSet);
    }
}
