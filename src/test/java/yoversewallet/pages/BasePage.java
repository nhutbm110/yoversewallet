package yoversewallet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class BasePage {

//    public static ChromeDriver driver;

    public static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    static LocalStorage local;
    static Robot robot;

    public static WebDriverWait wait;

    public void initDriver(String browser)
    {
        switch (browser) {
            case "chrome":
                System.setProperty( "webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe" );
                drivers.set(new ChromeDriver());
                break;
            case "firefox":
                System.setProperty( "webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe" );
                drivers.set(new FirefoxDriver());
                break;
            case "edge":
                System.setProperty( "webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe" );
                drivers.set(new EdgeDriver());
                break;

            default:
                throw new IllegalArgumentException( "Nhap lai tml! Co ten cung go sai."+" " + browser+ " khong support" );
        }
    }

    public void startBrowser(String browser){
//
        initDriver(browser);
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

//        Tu quet QRcode
        getDriver().get("https://wallet.dev.rndengineer.org");
        LocalStorage local = ((WebStorage) getDriver()).getLocalStorage();
        local.setItem("token", "\"8c443452-2377-3f68-b805-e14df348d21a\"");
        local.setItem("walletconnect","{\"connected\":true,\"accounts\":[\"0x76ff173412a941b4cfa3d88110da790baf32376f\"],\"chainId\":41234,\"bridge\":\"https://bridge.verichains.xyz\",\"key\":\"3083ed1077e91095bebeaa6ee3259e75f60f41afd3a3344f820cd218ca94089f\",\"clientId\":\"e96eb402-cf5f-4ec3-b3ca-24d16e96f425\",\"clientMeta\":{\"description\":\"\",\"url\":\"https://wallet.dev.rndengineer.org\",\"icons\":[],\"name\":\"\"},\"peerId\":\"eb81e80f-12da-4958-9c92-adcc240c776a\",\"peerMeta\":{\"description\":\"YoVerse.dev\",\"url\":\"https://wallet.dev.rndengineer.org/\",\"icons\":[\"https://walletconnect.org/walletconnect-logo.png\"],\"name\":\"KiWallet\"},\"handshakeId\":1660533130377314,\"handshakeTopic\":\"f2170776-7172-41a1-9264-808ddd9cb392\"}");
        getDriver().get("https://wallet.dev.rndengineer.org/wallet");

    }
    public static WebDriver getDriver()
    {
        return drivers.get();
    }

    public void closeBrowser()
    {
        getDriver().quit();
    }

    public String getURL()
    {
        return getDriver().getCurrentUrl();
    }

    public String getTitle() throws InterruptedException {
        Thread.sleep( 2000 );
        return getDriver().getTitle();
    }

    public WalletPage goToWallet(){

        getDriver().get("https://wallet.dev.rndengineer.org/wallet");
        return new WalletPage();
    }

    public boolean isAttributePresent(By ele, String attr) {
        boolean result = false;
        try {
            wait.until( ExpectedConditions.attributeToBeNotEmpty( getDriver().findElement( ele ),attr ));
            String value =   getDriver().findElement(ele).getAttribute(attr);
            if (value != null){
                result = true;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isAttributeNotPresent(By ele, String attr) {
        boolean result = false;
        try {
//            wait.until( ExpectedConditions.visibilityOfElementLocated(sendtoNextButton));
            String value =   getDriver().findElement(ele).getAttribute(attr);
            if (value == null){
                result = true;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    public void waitForElementPresent(By ele)
    {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        wait.until( ExpectedConditions.visibilityOfElementLocated(ele));
    }

    public void waitForElementClickable(By ele)
    {
        wait.until( ExpectedConditions.elementToBeClickable(ele));
    }

   public void robotEnter() throws AWTException {
       Robot robot = new Robot();
       robot.keyPress( KeyEvent.VK_ENTER); // Press Enter key
       robot.keyRelease(KeyEvent.VK_ENTER); // Release Enter key
   }

}
