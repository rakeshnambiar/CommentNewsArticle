package net.thucydides.ebi.cucumber.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

/**
 * Created by rakeshnambiar on 15/06/2017.
 */
public class LoginPage extends PageObject {
    CommentPage commentPage;

    @FindBy(id="username-input")
    WebElementFacade userNameInp;

    @FindBy(id="password-input")
    WebElementFacade passwordInp;

    @FindBy(id="submit-button")
    WebElementFacade singInBtn;

    @FindBy(id="mybbc-wrapper")
    WebElementFacade myBBCPanel;

    public void loginToBBC(String username, String pwd) throws Exception {
        try{
            clickOn(commentPage.singIn);
            waitForWithRefresh();
            typeInto(userNameInp, username);
            typeInto(passwordInp, pwd);
            clickOn(singInBtn);
            waitForWithRefresh();
        }catch (Exception e){
            throw new Exception("ERROR: While Login to BBC Website");
        }
    }

    public boolean isLoggedInSuccessfully() throws Exception {
        try{
            return myBBCPanel.isCurrentlyVisible();
        }catch (Exception e){
            throw new Exception("ERROR: While verifying the Login status");
        }
    }



}
