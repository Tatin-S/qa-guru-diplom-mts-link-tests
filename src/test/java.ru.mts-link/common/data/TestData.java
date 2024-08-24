package common.data;
import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {
    Faker faker = new Faker(new Locale("ru"));
    public final String
            eventName = faker.harryPotter().spell();
}
