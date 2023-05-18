package mobileTest;


import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyDemoApp {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:platformVersion", "9.0");
        capabilities.setCapability("appium:deviceName", "Galaxy S9 FHD GoogleAPI Emulator");
        capabilities.setCapability("appium:deviceOrientation", "portrait");
        capabilities.setCapability("appium:app", "storage:filename=mda-1.0.17-20.apk");
        capabilities.setCapability("appium:appPackage", "com.saucelabs.mydemoapp.android");
        capabilities.setCapability("appium:appActivity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity");
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("appium:ensureWebviewsHavePages", true);
        capabilities.setCapability("appium:nativeWebScreenshot", true);

        MutableCapabilities sauceOptions = new MutableCapabilities(); // Conf Saucelabs
        sauceOptions.setCapability("name", "FTS 137 MyDemoApp");
        capabilities.setCapability("sauce:options", sauceOptions);
        //capabilities.setCapability("sauce:options", {"name":"FTS-137 MyDemoApp"});
        capabilities.setCapability("appium:newCommandTimeout", 3600);
        capabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("https://oauth-cristianobonfim-20602:b319d7e4-fa0a-45ef-86e3-ee5ae426cbbd@ondemand.us-west-1.saucelabs.com:443/wd/hub");

        driver = new AndroidDriver(remoteUrl, capabilities);
    }

    @Test
    public void testeSelecionarProduto() {
        // Na Tela inicial (Home), clicar no produto mochila
        WebElement produtoSelecionado = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Sauce Lab Back Packs\"]"));
        produtoSelecionado.click();
        // Na tela de produto, verificar o nome e o preço (por enquanto so esta clicando)
        WebElement nomeProduto = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/productTV"));
        assertEquals("Sauce Lab Back Packs", nomeProduto.getText());
        WebElement precoProduto = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/priceTV"));
        assertEquals("$ 29.99", precoProduto.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
