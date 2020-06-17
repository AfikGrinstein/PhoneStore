package il.co.hit.model.repository;

import il.co.hit.model.exception.NotFoundException;
import il.co.hit.model.objects.Phone;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PhoneRepositoryImpl implements PhoneRepository {

    private FileManager<Phone> fileManager;
    private Set<Phone> phoneSet;

    public PhoneRepositoryImpl() {
        try {
            this.fileManager = new FileManager<>("phone.data");
            this.phoneSet = fileManager.read();
        } catch (Exception e) {
            e.printStackTrace();
            this.phoneSet = new HashSet<>();
        }
    }

    @Override
    public Phone save(Phone phone) throws Exception {
        if (phone == null) {
            throw new IllegalArgumentException("phone must not be null");
        }

        phone.setId(UUID.randomUUID().toString());
        this.phoneSet.add(phone);
        fileManager.write(this.phoneSet);
        return phone;
    }

    @Override
    public boolean exists(String id) {
        if (id == null) {
            return false;
        }

        return this.phoneSet.contains(new Phone(id));
    }

    @Override
    public Set<Phone> findAll() {
        return this.phoneSet;
    }

    @Override
    public Phone find(String id) throws NoSuchFieldException {
        for (Phone phone: this.phoneSet) {
            if (phone.getId().equals(id)) {
                return phone;
            }
        }

        throw new java.lang.NoSuchFieldException("Cannot find phone with id " + id);
    }

    @Override
    public void delete(String id) throws NotFoundException, IOException {
        boolean removed = this.phoneSet.remove(new Phone(id));
        if (removed) {
            this.fileManager.write(this.phoneSet);
        } else {
            throw new NotFoundException();
        }
    }
}
