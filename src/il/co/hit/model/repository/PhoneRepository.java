package il.co.hit.model.repository;

import il.co.hit.model.exception.NotFoundException;
import il.co.hit.model.objects.Phone;

import java.io.IOException;
import java.util.Set;

public interface PhoneRepository {

    Phone save(Phone phone) throws Exception;

    boolean exists(String id) throws Exception;

    Set<Phone> findAll();

    Phone find(String id) throws NoSuchFieldException;

    void delete(String id) throws NotFoundException, IOException;
}
