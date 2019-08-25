package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

    public static String empName() {
        String generated_string = RandomStringUtils.randomAlphabetic(1);
        return("Megastar"+generated_string);
    }

    public static String empSal() {
        String generated_sal = RandomStringUtils.randomNumeric(5);
        return(generated_sal);
    }

    public static String empAge() {
        String generated_age = RandomStringUtils.randomNumeric(2);
        return(generated_age);
    }

}
