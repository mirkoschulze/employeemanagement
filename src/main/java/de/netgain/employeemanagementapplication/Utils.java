package de.netgain.employeemanagementapplication;

import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
    
    private static final Logger L = LoggerFactory.getLogger(Utils.class);

    public static boolean validateName(String name) {
        L.info("[Utils] : validateName(String name) called with param = " + name);
        return Pattern.matches("[A-Z]{1}[a-z]+|\\w{0}", name);
    }

}
