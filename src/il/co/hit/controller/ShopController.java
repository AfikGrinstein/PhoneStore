package il.co.hit.controller;

import il.co.hit.model.ShopService;
import il.co.hit.model.objects.Brand;
import il.co.hit.model.objects.OS;
import il.co.hit.model.objects.Phone;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// Singleton
public class ShopController {

    private static ShopController INSTANCE;
    private static final Object lockObject = new Object();

    private final ShopService shopService;

    private ShopController() {
        this.shopService = new ShopService();
    }

    public static ShopController getInstance() {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new ShopController();
                }
            }
        }

        return INSTANCE;
    }

    public boolean addNewPhone(String name, String brand, LocalDate releaseDate, double ram, String os) {
        // validations
        Phone newPhone = Phone.builder()
                .name(name)
                .brand(Brand.valueOf(brand.toUpperCase()))
                .releaseDate(releaseDate)
                .ramMemory(ram)
                .os(OS.valueOf(os.toUpperCase()))
                .build();

        return this.shopService.addNewPhoneToStore(newPhone);
    }

    public List<Phone> getAllPhones() {
        Set<Phone> phones = this.shopService.getAllPhonesInStore();
        return new ArrayList<>(phones);
    }

    public boolean deletePhone(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        return this.shopService.delete(id);
    }
}
