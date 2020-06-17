package il.co.hit.test;

import il.co.hit.controller.LabController;
import il.co.hit.controller.ShopController;
import il.co.hit.model.repository.LabRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LabTests {

    private LabController labController;
    private ShopController shopController;
    private String session;

    @BeforeAll
    public static void beforeAll() {
        LabRepositoryImpl.FILENAME = "lab_phone_test.data";
    }

    @BeforeEach
    public void setup() {
        labController = LabController.getInstance();
        shopController = ShopController.getInstance();
        session = labController.createSession("1234");
    }

    @Test
    public void addLabPhoneWithInvalidPhoneId() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = labController.addPhoneToLab(session, "xx", "Test", "", "");
            Assertions.assertFalse(result);
        });
    }

    @Test
    public void changeStatusWithInvalidStatus() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            labController.updateStatus(session, "xx", "wrong_status");
        });
    }

    @Test
    public void failedToDeleteWithInvalidPhoneId() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = shopController.delete("xx");
            Assertions.assertFalse(result);
        });
    }
}
