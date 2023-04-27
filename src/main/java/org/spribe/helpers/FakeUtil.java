package org.spribe.helpers;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.experimental.Accessors;

public class FakeUtil {

    @Getter
    @Accessors(fluent = true)
    private static final Faker FAKER = new Faker();


    public static String generateHash() {
        return FAKER().regexify("[a-z0-9]{10}");
    }

    public static String generateScreenName() {
        return FAKER().funnyName().name().replaceAll(" ", "");
    }

    public static String generateFirstName() {
        return FAKER().name().firstName();
    }

    public static String generateGender() {
        String[] gender = {"male", "female"};
        int randomIndex = (int) (Math.random() * (gender.length - 0)) + 0;
        return gender[randomIndex];
    }

    public static String generateLastName() {
        return FAKER().name().firstName();
    }

    public static Integer generateAge() {
        return FAKER().number().numberBetween(16, 99);
    }

    public static String generateEmail() {
        return FAKER().name().username().concat("@spribe.com");
    }

    public static String generatePhoneNumber() {
        return "+38093".concat(generatePhoneNumberWithoutCode());
    }

    public static String generatePhoneNumberWithoutCode() {
        return generatePhoneNumberWithoutCode(7);
    }

    public static String generatePhoneNumberWithoutCode(int digits) {
        return FAKER().number().randomDigitNotZero() + FAKER().number().digits(digits - 1);
    }
}
