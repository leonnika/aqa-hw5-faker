package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.RegistrationData;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryTest {

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }

    @Test
    void shouldReplanMeetingDataValid() {
        $("[placeholder='Город']").setValue(RegistrationData.registrationUserValidData("ru").getCity());
        $("[type='tel'][placeholder='Дата встречи']").click();
        $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
        $("[name='name']").setValue(RegistrationData.registrationUserValidData("ru").getName());
        $("[name='phone']").setValue(RegistrationData.registrationUserValidData("ru").getPhone());
        $("[role='presentation']").click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        $("[type='tel'][placeholder='Дата встречи']").click();
        $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
        $$("button").find(exactText("Запланировать")).click();
        $$("button").find(exactText("Перепланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }

    @Test //ussue
    void shouldNotReplanMeetingDatesEqual() {
        String datePlan = RegistrationData.registrationDate("ru").getDate();
        $("[placeholder='Город']").setValue(RegistrationData.registrationUserValidData("ru").getCity());
        $("[type='tel'][placeholder='Дата встречи']").click();
        $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[type='tel'][placeholder='Дата встречи']").setValue(datePlan);
        $("[name='name']").setValue(RegistrationData.registrationUserValidData("ru").getName());
        $("[name='phone']").setValue(RegistrationData.registrationUserValidData("ru").getPhone());
        $("[role='presentation']").click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Встреча на эту дату уже запланирована!")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotReplanMeetingDateValidCheckIsNot() {
        $("[placeholder='Город']").setValue(RegistrationData.registrationUserValidData("ru").getCity());
        $("[type='tel'][placeholder='Дата встречи']").click();
        $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
        $("[name='name']").setValue(RegistrationData.registrationUserValidData("ru").getName());
        $("[name='phone']").setValue(RegistrationData.registrationUserValidData("ru").getPhone());
        $$("button").find(exactText("Запланировать")).click();
        assertEquals("rgba(255, 92, 92, 1)", $("[data-test-id=agreement]").getCssValue("color"));
    }

    @Nested
    class EmptyFieldsOtions {
        String expected = "Поле обязательно для заполнения";

        @Test
        void shouldNotReplanMeetingCityEmpty() {
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='name']").setValue(RegistrationData.registrationUserValidData("ru").getName());
            $("[name='phone']").setValue(RegistrationData.registrationUserValidData("ru").getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            $("[class='input__sub']").shouldHave(exactText(expected));
        }

        @Test
        void shouldNotReplanMeetingNameEmpty() {
            $("[placeholder='Город']").setValue(RegistrationData.registrationUserValidData("ru").getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='phone']").setValue(RegistrationData.registrationUserValidData("ru").getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement name = $("[data-test-id='name']");
            name.$("[class='input__sub']").shouldHave(exactText(expected));
        }

        @Test
        void shouldNotReplanMeetingPhoneEmpty() {
            $("[placeholder='Город']").setValue(RegistrationData.registrationUserValidData("ru").getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='name']").setValue(RegistrationData.registrationUserValidData("ru").getName());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement phone = $("[data-test-id='phone']");
            phone.$("[class='input__sub']").shouldHave(exactText(expected));
        }

        @Test
        void shouldNotReplanMeetingDateEmpty() {
            $("[placeholder='Город']").setValue(RegistrationData.registrationUserValidData("ru").getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[name='name']").setValue(RegistrationData.registrationUserValidData("ru").getName());
            $("[name='phone']").setValue(RegistrationData.registrationUserValidData("ru").getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement date = $("[data-test-id='date']");
            date.$("[class='input__sub']").shouldHave(exactText("Неверно введена дата"));
        }
    }

    @Nested
    class SpecialSymbolsInFieldsOtions {

        @Test
        void shouldNotReplanMeetingCitySpecialSymbols() {
            $("[placeholder='Город']").setValue(RegistrationData.registrationUserDataSpecialSymbols("ru").getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='name']").setValue(RegistrationData.registrationUserValidData("ru").getName());
            $("[name='phone']").setValue(RegistrationData.registrationUserValidData("ru").getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement city = $("[data-test-id='city']");
            city.$("[class='input__sub']").shouldHave(exactText("Доставка в выбранный город недоступна"));
        }

        @Test
        void shouldNotReplanMeetingNameSpecialSymbols() {
            $("[placeholder='Город']").setValue(RegistrationData.registrationUserValidData("ru").getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='name']").setValue(RegistrationData.registrationUserDataSpecialSymbols("ru").getName());
            $("[name='phone']").setValue(RegistrationData.registrationUserValidData("ru").getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement name = $("[data-test-id='name']");
            name.$("[class='input__sub']").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

        @Test
            //ussue
        void shouldNotReplanMeetingPhoneSpecialSymbols() {
            $("[placeholder='Город']").setValue(RegistrationData.registrationUserValidData("ru").getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='name']").setValue(RegistrationData.registrationUserValidData("ru").getName());
            $("[name='phone']").setValue(RegistrationData.registrationUserDataSpecialSymbols("ru").getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement phone = $("[data-test-id='phone']");
            phone.$("[class='input__sub']").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }

        @Test
        void shouldNotReplanMeetingDateSpesialSymbol() {
            $("[placeholder='Город']").setValue(RegistrationData.registrationUserValidData("ru").getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationUserDataSpecialSymbols("ru").getPhone());
            $("[name='name']").setValue(RegistrationData.registrationUserValidData("ru").getName());
            $("[name='phone']").setValue(RegistrationData.registrationUserValidData("ru").getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement date = $("[data-test-id='date']");
            date.$("[class='input__sub']").shouldHave(exactText("Неверно введена дата"));
        }

        @Nested
        class NotValidDateInFieldsOtions {

            @Test
            void shouldNotReplanMeetingCityNotValid() {
                $("[placeholder='Город']").setValue(RegistrationData.registrationUserNotValidData("ru").getCity());
                $("[type='tel'][placeholder='Дата встречи']").click();
                $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
                $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
                $("[name='name']").setValue(RegistrationData.registrationUserValidData("ru").getName());
                $("[name='phone']").setValue(RegistrationData.registrationUserValidData("ru").getPhone());
                $("[role='presentation']").click();
                $$("button").find(exactText("Запланировать")).click();
                SelenideElement city = $("[data-test-id='city']");
                city.$("[class='input__sub']").shouldHave(exactText("Доставка в выбранный город недоступна"));
            }

            @Test
                //ussue
            void shouldNotReplanMeetingNameNotValid() {
                $("[placeholder='Город']").setValue(RegistrationData.registrationUserValidData("ru").getCity());
                $("[type='tel'][placeholder='Дата встречи']").click();
                $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
                $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
                $("[name='name']").setValue(RegistrationData.registrationUserNotValidData("ru").getName());
                $("[name='phone']").setValue(RegistrationData.registrationUserValidData("ru").getPhone());
                $("[role='presentation']").click();
                $$("button").find(exactText("Запланировать")).click();
                SelenideElement name = $("[data-test-id='name']");
                name.$("[class='input__sub']").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
            }

            @Test
//ussue
            void shouldNotReplanMeetingPhoneNotValid() {
                $("[placeholder='Город']").setValue(RegistrationData.registrationUserValidData("ru").getCity());
                $("[type='tel'][placeholder='Дата встречи']").click();
                $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
                $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
                $("[name='name']").setValue(RegistrationData.registrationUserValidData("ru").getName());
                $("[name='phone']").setValue(RegistrationData.registrationUserNotValidData("ru").getPhone());
                $("[role='presentation']").click();
                $$("button").find(exactText("Запланировать")).click();
                SelenideElement phone = $("[data-test-id='phone']");
                phone.$("[class='input__sub']").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
            }
        }
      // Добавить тесты где имя с ё, имя разных регистров, имя на англ.
    }
}