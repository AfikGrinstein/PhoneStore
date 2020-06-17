package il.co.hit.model.service;

import il.co.hit.model.exception.NotFoundException;
import il.co.hit.model.objects.Phone;
import il.co.hit.model.repository.PhoneRepository;
import il.co.hit.model.repository.PhoneRepositoryImpl;

import java.io.IOException;
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

    public boolean delete(String id) throws IOException {
        try {
            this.phoneRepository.delete(id);
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }
}
