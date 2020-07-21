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
        $("[data-test-id='city'] .input__control").setValue(city);
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(name);
        $("[data-test-id='phone'] .input__control").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно!")).isDisplayed();

        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] .input__control").setValue(newDate);
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
        $("[data-test-id='city'] .input__control").setValue("Париж");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(name);
        $("[data-test-id='phone'] .input__control").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Доставка в выбранный город недоступна")).isDisplayed();
    }

    @Test
    void shouldNoDate() {
        $("[data-test-id='city'] .input__control").setValue(city);
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='name'] .input__control").setValue(name);
        $("[data-test-id='phone'] .input__control").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Неверно введена дата")).isDisplayed();
    }

    @Test
    void shouldWrongName() {
        $("[data-test-id='city'] .input__control").setValue(city);
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue("456211");
        $("[data-test-id='phone'] .input__control").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).isDisplayed();
    }

    @Test
    void shouldwrongPhone() {
        $("[data-test-id='city'] .input__control").setValue(city);
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(name);
        $("[data-test-id='phone'] .input__control").setValue("89998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).isDisplayed();
    }

    @Test
    void shouldNotmarkCheckbox() {
        $("[data-test-id='city'] .input__control").setValue(city);
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(name);
        $("[data-test-id='phone'] .input__control").setValue(phone);
        $(byText("Запланировать")).click();
        $(".input_invalid").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldClosePopup() {
        $("[data-test-id='city'] .input__control").setValue(city);
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(name);
        $("[data-test-id='phone'] .input__control").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(".icon").click();
    }

    @Test
    void shouldClosePopupNewDate() {
        $("[data-test-id='city'] .input__control").setValue(city);
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(name);
        $("[data-test-id='phone'] .input__control").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно!")).isDisplayed();

        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] .input__control").setValue(newDate);
        $(byText("Запланировать")).click();

        $(byText("Перепланировать")).click();
        $(".icon").click();
    }
}
