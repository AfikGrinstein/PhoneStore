package il.co.hit.model.repository;

import il.co.hit.model.objects.LabPhone;
import il.co.hit.model.objects.LabStatus;

import java.util.Set;

public interface LabRepository {

    LabPhone save(LabPhone labPhone) throws Exception;

    Set<LabPhone> findAll();

    LabPhone find(String id) throws NoSuchFieldException;

    boolean delete(String id);

    Set<LabPhone> filterByStatus(LabStatus status);
}
