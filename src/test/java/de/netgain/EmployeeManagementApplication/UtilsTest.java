package de.netgain.EmployeeManagementApplication;

import de.netgain.employeemanagementapplication.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

public class UtilsTest {

    private static final List<String> VALID_NAMES = new ArrayList<>();
    private static final List<String> INVALID_NAMES = new ArrayList<>();

    @BeforeAll
    public static void setUpClass() {
        VALID_NAMES.addAll(Arrays.asList("Hans", "Peter", "Anne", "Liese"));
        INVALID_NAMES.addAll(Arrays.asList("JoChen", "ongbak", "L33t", "Peter die Wurst"));
    }

    @Test
    public void testValidateName() {
        VALID_NAMES.forEach(n -> assertTrue(Utils.validateName(n) == true));
    }

    @Test
    public void testNegativeValidateName() {
        INVALID_NAMES.forEach(n -> assertTrue(Utils.validateName(n) == false));
    }

}
