package de.netgain.EmployeeManagementApplication;

import de.netgain.employeemanagementapplication.Utils;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

public class UtilsTest {

    private static final List<String> VALID_NAMES = new ArrayList<>();
    private static final List<String> INVALID_NAMES = new ArrayList<>();

    @BeforeAll
    public static void setUpClass() {
        for (int i = 0; i < 100; i++) {
            VALID_NAMES.add(RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(4, 19).toLowerCase());
            INVALID_NAMES.add(RandomStringUtils.randomAlphanumeric(5, 20));
        }
    }

    @Test
    public void testValidateName() {
        VALID_NAMES.forEach(n -> assertTrue(Utils.validateName(n) == true));
        INVALID_NAMES.forEach(n -> assertTrue(Utils.validateName(n) == false));
    }

}
