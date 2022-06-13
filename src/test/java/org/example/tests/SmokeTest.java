package org.example.tests;

import com.codeborne.selenide.WebDriverConditions;
import io.qameta.allure.Description;
import org.example.helpers.CDP;
import org.example.helpers.DriverUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.tests.Location.MOSCOW;


public class SmokeTest extends TestBase {

    public static final String EXPECTED_TITLE = "Wildberries – Интернет-магазин модной одежды и обуви";
    public static final String BASE_URL = "https://www.wildberries.ru/";
    public static final String DELIVERY_ADDRESSES_URL = "services/besplatnaya-dostavka?desktop=1#terms-delivery";
    public static final String ITEM_FOR_SEARCH = "Аэрохоккей-настольный футбол КЛАСК (KLASK)";

    @BeforeEach
    @Override
    public void beforeEach() {
        super.beforeEach();
        step("Open url '" + BASE_URL + "'", () -> open(BASE_URL));
    }

    @Test
    @Description("Smoke test")
    @DisplayName("Page title should have header text")
    void titleTest() {
        step("Page title should have text: " + EXPECTED_TITLE, () -> {
            webdriver().shouldHave(WebDriverConditions.title(EXPECTED_TITLE));
        });
    }

    @Test
    @Description("Smoke test")
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";
            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }

    @Test
    @Description("Smoke test")
    @DisplayName("An item can be found using search")
    void checkSearch() {
        step("Perform a search request", () -> {
            $("#searchInput").setValue(ITEM_FOR_SEARCH).pressEnter();
        });

        step("Check a relevant item is found", () -> {
            $(".product-card__brand-name").shouldHave(text(ITEM_FOR_SEARCH));
        });
    }

    @Test
    @Description("Smoke test")
    @DisplayName("Catalog: Open 1-st level category")
    void check1stLevelCategorySelection() {
        step("Select 1-st level category via burger-menu", () -> {
            $("button[data-wba-header-name='Catalog']").click();
            $(".menu-burger__main-list").$(By.linkText("Женщинам")).click();
        });

        step("Check the opened category corresponds to the selected category", () -> {
            $(".catalog-title").shouldHave(text("Женщинам"));
        });
    }

    @Test
    @Description("Smoke test")
    @DisplayName("Catalog: Open 2-nd level category")
    void check2ndLevelCategorySelection() {
        step("Select some Category->Subcategory via burger-menu", () -> {
            $("button[data-wba-header-name='Catalog']").click();
            $(".menu-burger__main-list").$(By.linkText("Игрушки")).hover();
            $(".menu-burger__first").$(By.linkText("Антистресс")).click();
        });

        step("Check the opened category corresponds to the selected subcategory", () -> {
            $(".catalog-title").shouldHave(text("Игрушки антистресс"));
        });
    }

    @Test
    @Description("Smoke test")
    @DisplayName("Catalog: Open 3-nd level category")
    void check3rdLevelCategorySelection() {
        step("Select some Category->Subcategory", () -> {
            $("button[data-wba-header-name='Catalog']").click();
            $(".menu-burger__main-list").$(By.linkText("Электроника")).hover();
            $(".menu-burger__first").$(withText("Игровые консоли и игры")).click();
            $(".menu-burger__second").$(By.linkText("Приставки 8bit и 16bit")).click();
        });

        step("Check the opened category corresponds to the selected subcategory", () -> {
            $(".catalog-title").shouldHave(text("Приставки 8bit и 16bit"));
        });
    }

    @Test
    @Description("Smoke test")
    @DisplayName("An item can be added to the basket")
    void checkAddItemToBasket() {
        step("Search for an item", () -> {
            $("#searchInput").setValue(ITEM_FOR_SEARCH).pressEnter();
        });

        step("Click 'Add to the basket' for the found item", () -> {
            $(".product-card")
                    .$(withText(ITEM_FOR_SEARCH))
                    .hover()
                    .ancestor(".product-card")
                    .$(withText("В корзину"))
                    .click();
            $(".j-item-basket .navbar-pc__notify").shouldHave(text("1"));
        });

        step("Check the item is shown in the basket", () -> {
            $(".j-item-basket").click();
            $$(".list-item__wrap").shouldBe(size(1));
            $(By.partialLinkText(ITEM_FOR_SEARCH)).shouldBe(visible);
        });
    }

    @CsvSource(value = {
            "Armenia | https://am.wildberries.ru/",
            "Israel | https://wildberries.co.il/"},
            delimiter = '|')

    @ParameterizedTest(name = "Some country can be selected: {0}")
    @Description("Smoke test")
    @DisplayName("Some country can be selected")
    void checkCountrySelection(String country, String expectedUrl) {
        step("Select " + country, () -> {
            $(".j-b-header-country").click();
            $(".tooltip-country").$(withText(country)).click();
        });

        step("Check opened url is " + expectedUrl, () -> {
            webdriver().shouldHave(url(expectedUrl));
        });
    }

    @Test
    @Description("Smoke test")
    @DisplayName("Some delivery address can be selected")
    void checkDeliveryAddressSelection() {
        String[] pickPointAddress = new String[1];
        step("Header: Click on delivery address icon", () -> {
            CDP.setGeolocationOverride(MOSCOW.latitude, MOSCOW.longitude);
            $(".j-geocity-wrap").click();
            $(".popup__header").shouldHave(text("Выберите адрес доставки"));
            pickPointAddress[0] = $("#pooList .address-item__name").text().replaceAll("\n.*", "");
            $("#pooList .address-item__name").click();
            $(".balloon-content").$(withText("Выбрать")).click();
        });

        step("Check the selected address is shown in the header", () -> {
            $("[data-wba-header-name='DLV_Adress']").shouldHave(text(pickPointAddress[0]));
        });
    }

    @Test
    @Description("Smoke test")
    @DisplayName("Pick-up points list can be shown")
    void checkShopsListIsShown() {
        step("Click on addresses navbar icon",
                () -> $("[data-wba-header-name='Pick_up_points']").click());

        step("Check page with pick-up points list is open", () -> {
            webdriver().shouldHave(
                    url(BASE_URL + DELIVERY_ADDRESSES_URL));
            $("#terms-delivery").shouldHave(text("Информация о доставке и пунктах выдачи"));
            $("#pooList .swiper-slide-active").shouldBe(visible);
        });
    }
}