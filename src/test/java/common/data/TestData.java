package common.data;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class TestData {
    public String[] genders = {"Male", "Female", "Other"},
            hobbies = {"Sports", "Reading", "Music"};
    public List<String> listFieldsAuthorizationRu = List.of("Вход\n" +
            "Почта\n" +
            "\n" +
            "Пароль\n" +
            "\n" +
            "Запомнить меня\n" +
            "ВОЙТИ\n" +
            "ВОЙТИ ЧЕРЕЗ SSO\n" +
            "ВОССТАНОВИТЬ ПАРОЛЬ\n" +
            "СОЗДАТЬ АККАУНТ");
    public List<String> listFieldsAuthorizationEn = List.of("Log in\n" +
            "Email\n" +
            "\n" +
            "Password\n" +
            "\n" +
            "Remember me\n" +
            "LOG IN\n" +
            "LOG IN WITH SSO\n" +
            "RECOVER PASSWORD\n" +
            "CREATE AN ACCOUNT");
    Faker faker = new Faker(new Locale("ru"));
    public final String
            name = faker.name().firstName(),
            secondName = faker.name().lastName(),
            nickname = faker.name().username(),
            phone = faker.phoneNumber().subscriberNumber(10),
            organization = faker.company().name(),
            position = faker.company().profession(),
            currentAddress = faker.address().streetAddress(),
            eventName = faker.harryPotter().spell(),
            testEmail = faker.internet().emailAddress(),
            testPassword = faker.internet().password(6, 10, true, true, true);

    public static String getRandomItemFromArray(String[] array) {
        int index = getRandomInt(0, array.length - 1);

        return array[index];
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public String gender = getRandomItemFromArray(genders),
            hobby = getRandomItemFromArray(hobbies),
            description =
                    "1. Gender - " + gender +
                            "\n2. Address - " + currentAddress +
                            "\n3. Hobbies - " + hobby;


}
