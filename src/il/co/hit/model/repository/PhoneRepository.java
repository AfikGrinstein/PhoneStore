package il.co.hit.model.repository;

import il.co.hit.model.objects.Phone;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PhoneRepository {

    private FileManager<Phone> fileManager;
    private Set<Phone> phoneSet;

    public PhoneRepository() {
        try {
            this.fileManager = new FileManager<>("phone.json");
            this.phoneSet = fileManager.read();
        } catch (Exception e) {
            this.phoneSet = new HashSet<>();
        }
    }

    public Phone save(Phone phone) throws Exception {
        if (phone == null) {
            throw new IllegalArgumentException("phone must not be null");
        }

        phone.setId(UUID.randomUUID().toString());
        this.phoneSet.add(phone);
        fileManager.write(this.phoneSet);
        return phone;
    }

    public Set<Phone> findAll() {
        return this.phoneSet;
    }

    public boolean delete(String id) {
        return this.phoneSet.remove(Phone.builder().id(id).build());
    }
}
