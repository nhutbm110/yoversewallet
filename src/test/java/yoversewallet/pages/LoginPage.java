package yoversewallet.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    By loginButton = By.xpath("//button[text()='Login Account']");
//    @Test
    public  void clickLoginButton()
    {
//        driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );

        getDriver().findElement(loginButton).click();
    }
}
