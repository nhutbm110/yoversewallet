package yoversewallet.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WalletTest extends BaseTest {

    static String actualName;
    static  String expectedName;
    static Float totalToken;
    static  Float detailToken;

//    WalletPage walletPage;

    @Test
    public void tc1_VerifyLoginSuccessful()
    {
        assertEquals( basePage.getURL(),"https://wallet.dev.rndengineer.org/wallet" );
        System.out.println(basePage.getURL());
    }

    @Test
    public void tc2_GetTitle() throws InterruptedException {
        assertEquals( basePage.getTitle(),"YoVerse Wallet" );
        System.out.println(basePage.getTitle());
    }

    @Test(description = "test thử coi có ăng không")
    public void tc3_VerifyAddressInHomepage() {
//        walletPage=basePage.goToWallet();
        System.out.println("Address Wallet o homepage: " +walletPage.getAddressMasterAccount());
        String actualAddress = walletPage.getAddressMasterAccount();
    }
//    @Test
    public void tc31_test() throws InterruptedException {
//        System.out.println("Amoun total: "+walletPage.getAmountTotal());
        totalToken = walletPage.getAmountTotal();
        System.out.println("Total ne: " +totalToken);
//        System.out.println("STARVERSE: "+walletPage.getValueFirstToken());
//        System.out.println("YoVerse: "+walletPage.getValueSecondToken());
        detailToken= walletPage.getValueFirstToken()+walletPage.getValueSecondToken();
        System.out.println("Detail ne: " +detailToken);
        assertEquals( totalToken,detailToken );
    }

//    @Test
    public void tc_4GetAccountInfoInHomepage()
    {
        System.out.println("Name o homepage: "+walletPage.getAccountName());
        String actualName = walletPage.getAccountName();
        System.out.println("Email o homepage: "+walletPage.getAccountEmail());
    }

//    @Test
    public void tc_5GetReceiveInfo() throws InterruptedException {
        walletPage.clickReceiveButton();
        System.out.println("Name la: "+walletPage.getReceiveName());
        String expectedName = walletPage.getReceiveName();
        System.out.println("Address la: "+walletPage.getReceiveAddress());
        String expectedAddress = walletPage.getReceiveAddress();
        assertEquals(expectedAddress,expectedName);
    }
//    @Test(dependsOnMethods = {"TC4getAccountInfo","TC5getReceiveInfo"})
    public void TC6_verifyInfo() throws InterruptedException {
        actualName = walletPage.getAccountName();
        expectedName = walletPage.getReceiveName();
        Assert.assertEquals(actualName,expectedName);

    }

//    @Test
    public void tc_70SendToTokenInputInvalidName() throws AWTException {
        walletPage.clickSendButton();
//        System.out.println( "Att truoc khi nhap: " + walletPage.getAttributeNextButton() );
        assertTrue( walletPage.checkNextButtonDisable() );
        //input invalid username
        walletPage.inputUsernameAddress( "tvxq24122003" );
        walletPage.robotEnter();
        walletPage.sendtoCheckMessageDisplay();
        Assert.assertEquals( walletPage.sendtoErrorMessageAddress(), "Invalid username or address" );
    }
//    @Test
    public void tc_71SendToTokenInputInvalidAddress() throws AWTException {
        walletPage.clickSendButton();
        assertTrue( walletPage.checkNextButtonDisable() );
        walletPage.inputUsernameAddress( "0x44a47c20c915c4aa6dcbf18456748c1230f5tvxq" );
        walletPage.robotEnter();
        walletPage.sendtoCheckMessageDisplay();
        Assert.assertEquals( walletPage.sendtoErrorMessageAddress(), "Invalid username or address" );
    }
//    @Test
    public void tc_72SendToTokenInputYourUsername() throws InterruptedException, AWTException {
//        walletPage.deleteUsernameAddress();
        walletPage.clickSendButton();
        assertTrue( walletPage.checkNextButtonDisable() );
        walletPage.inputUsernameAddress( "nhutnebasic1110" );
        Thread.sleep( 500 );
        walletPage.robotEnter();
        walletPage.sendtoCheckMessageDisplay();
        Assert.assertEquals( walletPage.sendtoErrorMessageAddress(), "Receiver's address cannot be the same as your address" );
    }
//    @Test
    public void tc_73SendToTokenInputYourAddress() throws InterruptedException, AWTException {

//        walletPage.deleteUsernameAddress();
        walletPage.clickSendButton();
        assertTrue( walletPage.checkNextButtonDisable() );
        walletPage.inputUsernameAddress( "0x76ff173412a941b4cfa3d88110da790baf32376f" );
        Thread.sleep( 500 );
        walletPage.robotEnter();
        walletPage.sendtoCheckMessageDisplay();
        Assert.assertEquals( walletPage.sendtoErrorMessageAddress(), "Receiver's address cannot be the same as your address" );
    }
//    @Test
    public void tc_74SendToTokenInputValidUsername() throws AWTException {
        walletPage.clickSendButton();
        assertTrue( walletPage.checkNextButtonDisable() );
        walletPage.inputUsernameAddress( "giin1176217basic" );
//        Thread.sleep( 1000 );
        walletPage.robotEnter();
        walletPage.clickSendToNextButton();
        walletPage.clickDropdownToken();
        walletPage.clickValueDropdownToken("STAR");
        walletPage.inputSentToAmount("1.99");
        walletPage.clickSendToNextButton();
        /*Click to open popup confirm*/
        walletPage.clickSendToNextButton();
        //
        walletPage.clickCancelPopupConfirm();
        Assert.assertEquals(walletPage.getContent1PopupConfirm(),"Please use your Mobile Wallet to confirm this transaction" );
    }
//    @Test
    public void tc75_SendToTokenInputValidAddress() throws InterruptedException, AWTException {
        walletPage.clickSendButton();
        assertTrue( walletPage.checkNextButtonDisable() );
        walletPage.inputUsernameAddress( "0x2612714fe7a1e6d194df5d26e8e822eadefcb3bc" );
        walletPage.robotEnter();
        walletPage.clickSendToNextButton();
        walletPage.clickDropdownToken();
        walletPage.clickValueDropdownToken("YOVE");
        walletPage.inputSentToAmount("1.01");
        walletPage.clickSendToNextButton();
        /*Click to open popup confirm*/
        walletPage.clickSendToNextButton();
        //
        walletPage.clickCancelPopupConfirm();
        Assert.assertEquals(walletPage.getContent1PopupConfirm(),"Please use your Mobile Wallet to confirm this transaction" );
    }

//    @Test
    public void tc76_UsemaxAmountToken() throws AWTException {
        walletPage.clickSendButton();
        assertTrue( walletPage.checkNextButtonDisable() );
        walletPage.inputUsernameAddress( "jaejoong2601basic" );
        walletPage.robotEnter();
        walletPage.clickSendToNextButton();
        walletPage.clickDropdownToken();
        walletPage.clickValueDropdownToken("STAR");
        walletPage.clickUseMaxAmount();
        walletPage.clickSendToNextButton();
        /*Click to open popup confirm*/
        walletPage.clickSendToNextButton();
        //
        walletPage.clickCancelPopupConfirm();
        Assert.assertEquals(walletPage.getContent1PopupConfirm(),"Please use your Mobile Wallet to confirm this transaction" );
    }


//    @Test
    public void tc80_Transfer_NFT_Success() throws AWTException {
        walletPage.clickTabNFT();
        walletPage.selectCollection( "Character" );
        walletPage.selectNFT( "#354" );
        walletPage.clickTranferNFT();
        walletPage.inputUsernameAddress("thailandadvance0607");
        walletPage.robotEnter();
        walletPage.clickSendToNextButton();
        walletPage.clickSendToNextButton();
        Assert.assertEquals(walletPage.getContent1PopupConfirm(),"Please use your Mobile Wallet to confirm this transaction" );
        Assert.assertEquals(walletPage.getContent2PopupConfirm(),"Compare this icon on Mobile Wallet to make sure this transaction is secured" );
    }

}
