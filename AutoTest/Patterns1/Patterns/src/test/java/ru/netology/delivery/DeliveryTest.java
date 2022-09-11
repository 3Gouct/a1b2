package ru.netology.delivery;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    private final DataGenerator.UserInfo User = DataGenerator.Registration.generateUser ("ru");
    private final int daysForFirstMeeting = 4;
    private final String firstMeetingDate = DataGenerator.generateDate (daysForFirstMeeting);
    private final int daysForSecondMeeting = 7;
    private final String secondMeeting = DataGenerator.generateDate (daysForSecondMeeting);

    @BeforeEach
    void setup() {
        open ("http://localhost:9999");
    }

    @Test
    public void successfulMeeting() {
        Configuration.holdBrowserOpen = true;
        $ ("[data-test-id='city'] input").setValue (User.getCity ());
        $ ("[data-test-id='date'] input").sendKeys (Keys.chord (Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $ ("[data-test-id='date'] input").setValue (firstMeetingDate);
        $ ("[data-test-id='name'] input").setValue (User.getName ());
        $ ("[data-test-id='phone'] input").setValue (User.getPhone ());
        $ ("[data-test-id='agreement']").click ();
        $ (byText ("Запланировать")).click ();
        $ ("[data-test-id='success-notification']").shouldBe (visible, Duration.ofSeconds (15));
        $ ("[data-test-id='success-notification'] [class='notification__content']").shouldHave (exactText ("Встреча успешно запланирована на " + firstMeetingDate));
        $ ("[data-test-id='date'] input").doubleClick ();
        $ ("[data-test-id='date'] input").sendKeys (Keys.DELETE);
        $ ("[data-test-id='date'] input").setValue (secondMeeting);
        $ (byText ("Запланировать")).click ();
        $ ("[data-test-id='replan-notification']").shouldBe (visible, Duration.ofSeconds (15));
        $ (byText ("Перепланировать")).click ();
        $ ("[data-test-id='success-notification'] [class='notification__content']").shouldHave (exactText ("Встреча успешно запланирована на " + secondMeeting));
    }
}