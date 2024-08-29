package common.data;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class TestData {
    public String[] genders = {"Male", "Female", "Other"},
            hobbies = {"Sports", "Reading", "Music"};
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
    }    public String gender = getRandomItemFromArray(genders),
            hobby = getRandomItemFromArray(hobbies),
            pictures = "data/picture.jpg",
            description =
                    "1. Gender - " + gender +
                            " 2. Address - " + currentAddress +
                            " 3. Hobbies - " + hobby;

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }


}
