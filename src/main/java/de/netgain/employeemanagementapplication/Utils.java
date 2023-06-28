package de.netgain.employeemanagementapplication;

import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for the entire application.
 *
 * @author MirkoSchulze
 */
public class Utils {

    private static final Logger L = LoggerFactory.getLogger(Utils.class);

    /**
     * Checks if the submitted name is in a valid state via regex pattern:
     * <ul>
     * <li>starts with an uppercase character</li>
     * <li>contains no more uppercase characters</li>
     * <li>contains only alphabetical characters</li>
     * <li>is of length = 0 (for JSF usage)</li>
     * </ul>
     *
     * @param name the name to be validated
     * @return true if the submitted name is in a valid state
     */
    public static boolean validateName(String name) {
        L.info("[Utils] : validateName(String name) called with param = " + name);
        return Pattern.matches("[A-Z]{1}[a-z]+|\\w{0}", name);
    }

}
