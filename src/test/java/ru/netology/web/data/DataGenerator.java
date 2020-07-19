package ru.netology.web.data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.*;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String getCity() {
        List<String> cityList = Arrays.asList("Абакан", "Барнаул", "Нижний Новгород", "Ставрополь", "Ульяновск", "Кострома");
        Random random = new Random();
        String randomElement = cityList.get(random.nextInt(cityList.size()));
        return randomElement;
    }

    public static String getDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 3);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(cal.getTime());
    }

    public static String getNewDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 5);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(cal.getTime());
    }

    public static String getName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String getPhone() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().cellPhone();
    }
}
