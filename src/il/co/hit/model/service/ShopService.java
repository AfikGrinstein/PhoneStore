package il.co.hit.model.service;

import il.co.hit.model.objects.Phone;
import il.co.hit.model.repository.PhoneRepository;
import il.co.hit.model.repository.PhoneRepositoryImpl;

import java.util.Set;

public class ShopService {

    private final PhoneRepository phoneRepository;

    public ShopService() {
        this.phoneRepository = new PhoneRepositoryImpl();
    }

    public boolean addNewPhoneToStore(Phone phone) {
        try {
            this.phoneRepository.save(phone);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Set<Phone> getAllPhonesInStore() {
        return this.phoneRepository.findAll();
    }

    public boolean delete(String id) {
        return this.phoneRepository.delete(id);
    }
}
