package org.example.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v101.emulation.Emulation;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.decorators.Decorated;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.Optional;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CDP {

    public static void setGeolocationOverride(double latitude, double longitude) {
        DevTools devtools = getDevTools();
        devtools.createSession();
        devtools.send(Emulation.setGeolocationOverride(Optional.of(latitude), Optional.of(longitude), Optional.of(1)));
    }

    public static DevTools getDevTools() {
        return getCdpDriver().getDevTools();
    }

    @Nonnull
    @CheckReturnValue
    private static HasDevTools getCdpDriver() {
        WebDriver webDriver = getWebDriver();
        return getCdpDriver(webDriver);
    }

    @Nonnull
    @CheckReturnValue
    private static HasDevTools getCdpDriver(WebDriver webDriver) {
        if (webDriver instanceof HasDevTools) {
            return ((HasDevTools) webDriver);
        } else if (webDriver instanceof Decorated) {
            return getCdpDriver(((Decorated<WebDriver>) webDriver).getOriginal());
        } else if (webDriver instanceof WrapsDriver) {
            return getCdpDriver(((WrapsDriver) webDriver).getWrappedDriver());
        } else {
            WebDriver augmentedDriver = new Augmenter().augment(getWebDriver());
            return ((HasDevTools) augmentedDriver);
        }
    }
}
