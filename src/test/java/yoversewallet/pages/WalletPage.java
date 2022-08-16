package yoversewallet.pages;

import org.openqa.selenium.By;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;


public class WalletPage extends BasePage {

//    By masterAccountCopyButton = By.cssSelector("div.css-4g6ai3 svg");
    By masterAccountCopyButton = By.xpath("//div[@id='accountcopy']");
    By copiedMessage = By.xpath("//*[contains(text(),'Copied')]");
    By receiveButton = By.xpath("//button[text()='Receive']");
    By sendButton = By.xpath("//button[text()='Send']");
    By textboxUsernameAddress = By.xpath("//input[@placeholder='Type username or address']");
    By accountName = By.cssSelector("div.css-1jq1leu");
    By accountEmail = By.cssSelector("div.css-zaljb5");
    By receiverNameCopyButton = By.cssSelector("div.css-1ryw2n0 svg");
    By receiverAddressCopyButton = By.cssSelector("div.css-1wakvh svg");
    By sendtoNextButton = By.xpath("//button[@id='next']");
    By sendtoTokenDropdown = By.xpath("//*[text()='Please select token']//parent::button");
    String sendtoTokenDropdownList = "//div[contains(text(),'%s')]//parent::button";
    By sendtoAmountTextbox = By.xpath("//input[@type='text']");
    By sendtoPopupConfirmCancelButton = By.xpath("//button[@aria-label='Close']");
    By sendtoPopupConfirmText1 = By.cssSelector("div.css-1i44buz");
    By sendtoPopupConfirmText2 = By.cssSelector("div.css-5vvevk");
    By sendtoErrorMessage = By.xpath("//p[@id='username_errormessage']");
    By sendtoDeleteUserName = By.xpath("//*[@class='chakra-button css-1g7qt7v']");
    By tabNFTs = By.xpath( "//div[@id='tabnfts']" );
    String listCollection = "//*[contains(text(),'%s')]";
    String listNFT = "//*[contains(text(),'%s')]";
    String detailNFT ="//div[contains(text(),'%s')]";
    By buttonTranferNFT = By.xpath("//button[@id='transfer']");
    By amountTotal = By.xpath("//div[@id='total_balance']");
    By usemaxAmount = By.xpath("//div[@id='usemax']");
    By token1 = By.xpath("//*[@id='home_token_0_balance']");
    By token2 = By.xpath("//*[@id='home_token_1_balance']");



    public String getAddressMasterAccount() {
        waitForElementPresent(masterAccountCopyButton);
        getDriver().findElement(masterAccountCopyButton).click();
        getDriver().findElement(copiedMessage).isDisplayed();
        String data =null;
        try {
            data = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData( DataFlavor.stringFlavor);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }

    public float getAmountTotal() throws InterruptedException {
        float amount =0;
        waitForElementPresent(amountTotal);
        Thread.sleep( 3000 );
        String textBefore = getDriver().findElement(amountTotal).getText();
        String textAfter = textBefore.replaceAll( ",", "");
//        String textAfter = textBefore.replace( "\"", "");

//        String amount = text.replaceAll( "\\s\\w+",text );
        try {
            amount = Float.parseFloat( textAfter );
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return amount;
    }


    public String getAccountName()
    {
        waitForElementPresent(accountName);
        String name = getDriver().findElement(accountName).getText();
        return name;
    }

    public String getAccountEmail()
    {
        waitForElementPresent(accountEmail);
        String email = getDriver().findElement(accountEmail).getText();
        return email;
    }

    public void clickReceiveButton()
    {
        waitForElementPresent(receiveButton);
        getDriver().findElement(receiveButton).click();
    }

    public void clickSendButton(){
        waitForElementPresent(sendButton);
        getDriver().findElement(sendButton).click();
    }
    public String getReceiveName()  {
        waitForElementPresent(receiverNameCopyButton);
        getDriver().findElement(receiverNameCopyButton).click();
        getDriver().findElement(copiedMessage).click();
        getDriver().findElement(copiedMessage).isDisplayed();
        String name =null;
        try {
            name = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData( DataFlavor.stringFlavor);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return name;
    }

    public String getReceiveAddress() throws InterruptedException {
        waitForElementPresent(receiverAddressCopyButton);
        getDriver().findElement(receiverAddressCopyButton).click();
        waitForElementPresent(copiedMessage);
        getDriver().findElement(copiedMessage).isDisplayed();
        String address =null;
        try {
            address = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData( DataFlavor.stringFlavor);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return address;
    }

    public boolean checkNextButtonDisable()
    {
        return getDriver().findElement( sendtoNextButton ).getAttribute( "disabled" ).equals( "true" );
    }

    public boolean checkNextButtonEnable()
    {
        return getDriver().findElement( sendtoNextButton ).getAttribute( "disabled" ) == null;
    }

    public void inputUsernameAddress(String value)
    {

        waitForElementPresent(textboxUsernameAddress);
        getDriver().findElement(textboxUsernameAddress).clear();
        getDriver().findElement(textboxUsernameAddress).sendKeys(value);
    }

    public void deleteUsernameAddress()
    {
        waitForElementPresent(sendtoDeleteUserName);
        getDriver().findElement(sendtoDeleteUserName).click();
    }

    public boolean sendtoCheckMessageDisplay()
    {
        return getDriver().findElement(sendtoErrorMessage).isDisplayed();
    }

    public String sendtoErrorMessageAddress()
    {
//        wait.until( ExpectedConditions.visibilityOfElementLocated(sendtoErrorMessage));
        waitForElementPresent(sendtoErrorMessage);
        String mess = getDriver().findElement(sendtoErrorMessage).getText();
        return mess;

    }

    public void clickSendToNextButton(){
        waitForElementClickable(sendtoNextButton);
        getDriver().findElement(sendtoNextButton).click();
    }

    public String getAttributeNextButton()
    {
        waitForElementPresent(sendtoNextButton);
        String att = getDriver().findElement(sendtoNextButton).getAttribute("disabled");
        return  att;
    }

    public void clickDropdownToken(){
        waitForElementPresent( sendtoTokenDropdown );
        getDriver().findElement( sendtoTokenDropdown ).click();
    }

    public void clickValueDropdownToken(String value){
        sendtoTokenDropdownList = String.format( sendtoTokenDropdownList,value );
        getDriver().findElement(By.xpath( sendtoTokenDropdownList ) ).click();
    }

    public void inputSentToAmount(String value){
        waitForElementPresent(sendtoAmountTextbox);
        getDriver().findElement(sendtoAmountTextbox).clear();
        getDriver().findElement(sendtoAmountTextbox).sendKeys(value);
    }

    public void clickCancelPopupConfirm(){
        waitForElementPresent(sendtoPopupConfirmCancelButton);
        getDriver().findElement(sendtoPopupConfirmCancelButton).click();
    }

    public String getContent1PopupConfirm()
    {
        waitForElementPresent(sendtoPopupConfirmText1);
        String text = getDriver().findElement(sendtoPopupConfirmText1).getText();
        return text;
    }

    public String getContent2PopupConfirm()
    {
        waitForElementPresent(sendtoPopupConfirmText2);
        String text = getDriver().findElement(sendtoPopupConfirmText2).getText();
        return text;
    }

    public void clickTabNFT(){
        waitForElementPresent(tabNFTs);
        getDriver().findElement(tabNFTs).click();
    }
    public void selectCollection(String value)
    {
        listCollection = String.format(listCollection,value);
        getDriver().findElement( By.xpath( listCollection ) ).click();
    }

    public void selectNFT(String value)
    {
        listNFT = String.format(listNFT,value);
        getDriver().findElement( By.xpath(listNFT ) ).click();
    }

    public void clickDetailNFT(String value)
    {
        detailNFT = String.format(detailNFT,value);
        getDriver().findElement( By.xpath(detailNFT ) ).click();
    }

    public void clickTranferNFT()
    {
        waitForElementPresent(buttonTranferNFT);
        getDriver().findElement(buttonTranferNFT).click();
    }

    public void clickUseMaxAmount()
    {
        waitForElementPresent(usemaxAmount);
        getDriver().findElement(usemaxAmount).click();
    }
    public float getValueFirstToken() throws InterruptedException {
        float amount =0;
        waitForElementPresent(token1);
        Thread.sleep( 3000 );
        String textBefore = getDriver().findElement(token1).getText();
//        System.out.println("Before: " +textBefore);
//        String textAfter = textBefore.replaceAll( "\\d+((.|,)\\d+)?", "");
//        String textAfter = textBefore.replaceAll( ",", "");
        String textAfter = textBefore.replaceAll( "[\\s,a-zA-Z]", "");
        try {
            amount = Float.parseFloat( textAfter );
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return amount;
    }

    public float getValueSecondToken() throws InterruptedException {
        float amount =0;
        waitForElementPresent(token2);
        Thread.sleep( 3000 );
        String textBefore = getDriver().findElement(token2).getText();
//        System.out.println("Before: " +textBefore);
//        String textAfter = textBefore.replaceAll( "\\d+((.|,)\\d+)?", "");
//        String textAfter = textBefore.replaceAll( ",", "");
        String textAfter = textBefore.replaceAll( "[\\s,a-zA-Z]", "");
        try {
            amount = Float.parseFloat( textAfter );
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return amount;
    }
}
