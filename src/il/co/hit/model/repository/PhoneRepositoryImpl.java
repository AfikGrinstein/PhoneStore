package il.co.hit.model.repository;

import il.co.hit.model.objects.Phone;

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
    public boolean exists(String id) throws Exception {
        if (id == null) {
            return false;
        }

        boolean success = this.phoneSet.contains(new Phone(id));
        this.fileManager.write(this.phoneSet);
        return success;
    }

    @Override
    public Set<Phone> findAll() {
        return this.phoneSet;
    }

    @Override
    public boolean delete(String id) {
        return this.phoneSet.remove(new Phone(id));
    }
}
