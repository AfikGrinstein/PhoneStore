package il.co.hit.test;

import il.co.hit.controller.LabController;
import il.co.hit.model.repository.LabRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LabTests {

    private LabController labController;

    @BeforeAll
    public static void beforeAll() {
        LabRepositoryImpl.FILENAME = "lab_phone_test.data";
    }

    @BeforeEach
    public void setup() {
        labController = LabController.getInstance();
    }


    @Test
    public void addLabPhoneWithInvalidPhoneId() {
        boolean result = labController.addPhoneToLab("xx", "Test", "", "");

        Assertions.assertFalse(result);
    }

    @Test
    public void changeStatusWithInvalidStatus() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            labController.updateStatus("xx", "wrong_status");
        });
    }
}
