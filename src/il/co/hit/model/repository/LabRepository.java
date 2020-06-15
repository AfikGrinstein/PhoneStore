package il.co.hit.model.repository;

import il.co.hit.model.objects.LabPhone;
import il.co.hit.model.objects.LabStatus;

import java.io.IOException;
import java.util.Set;

public interface LabRepository {

    LabPhone save(LabPhone labPhone) throws Exception;

    Set<LabPhone> findAll();

    LabPhone find(String id) throws NoSuchFieldException;

    Set<LabPhone> filterByStatus(LabStatus status);

    void update(LabPhone phone) throws IOException;
}
