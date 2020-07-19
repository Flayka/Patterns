package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.DataGenerator.*;

public class OrderFormTest {

    public String city = getCity();
    public String name = getName();
    public String date = getDate();
    public String newDate = getNewDate();
    public String phone = getPhone();

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }

    @Test
    void shouldFillCorrectRegisterNewDate() {
        $("[placeholder='Город']").setValue(city);
        $(".menu-item_type_block").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно!")).isDisplayed();

        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(newDate);
        $(byText("Запланировать")).click();

        $(byText("Перепланировать")).click();
        $(".notification_visible").shouldHave(text("Успешно!"));
    }

    @Test
    void shouldSendEmptyForm() {
        $(byText("Запланировать")).click();
        $(byText("Поле обязательно для заполнения")).isDisplayed();
    }

    @Test
    void shouldWrongCity() {
        $("[placeholder='Город']").setValue("Париж");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Доставка в выбранный город недоступна")).isDisplayed();
    }

    @Test
    void shouldNoDate() {
        $("[placeholder='Город']").setValue(city);
        $(".menu-item_type_block").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Неверно введена дата")).isDisplayed();
    }

    @Test
    void shouldWrongName() {
        $("[placeholder='Город']").setValue(city);
        $(".menu-item_type_block").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue("456211");
        $("[name='phone']").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).isDisplayed();
    }

    @Test
    void shouldwrongPhone() {
        $("[placeholder='Город']").setValue(city);
        $(".menu-item_type_block").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue("89998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).isDisplayed();
    }

    @Test
    void shouldNotmarkCheckbox() {
        $("[placeholder='Город']").setValue(city);
        $(".menu-item_type_block").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $(byText("Запланировать")).click();
        $(".input_invalid").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldClosePopup() {
        $("[placeholder='Город']").setValue(city);
        $(".menu-item_type_block").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(".icon").click();
    }

    @Test
    void shouldClosePopupNewDate() {
        $("[placeholder='Город']").setValue(city);
        $(".menu-item_type_block").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно!")).isDisplayed();

        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(newDate);
        $(byText("Запланировать")).click();

        $(byText("Перепланировать")).click();
        $(".icon").click();
    }
}
