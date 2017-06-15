package net.thucydides.ebi.cucumber.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.ebi.cucumber.framework.hooks.ScenarioHook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.interactions.Actions;



import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;


/**
 * Created by rakeshnambiar on 15/06/2017.
 */
public class CommentPage extends PageObject {

    @FindBy(id = "idcta-username")
    WebElementFacade singIn;

    @FindBy(css="a[class='blogs-comments-link']")
    java.util.List<WebElementFacade> lnkAddComment;

    @FindBy(name="comment")
    WebElementFacade commentInput;

    @FindBy(css="#submit_new_comment > input.cmts-submit.cmts-button")
    WebElementFacade postCommentBtn;

    @FindBy(css="div[class^='cmts-message cmts-message-success']")
    WebElementFacade successMsg;

    @FindBy(id="bbc-blogs-comments-iframe")
    WebElementFacade commentFrame;


    public void openPage() throws Exception {
        try{
            this.open();
            waitForWithRefresh();
            new Actions(getDriver()).moveToElement(singIn).perform();
        }catch (Exception e){
            throw new Exception("ERROR: While Opening the BBC news article page");
        }
    }

    public boolean verifyAddCommentLnkAvailable() throws Exception {
        try{
            waitForWithRefresh();
            try {
                getDriver().switchTo().alert().dismiss();
                getDriver().switchTo().parentFrame();
            }catch (NoAlertPresentException ex){
                ScenarioHook.getScenario().write("No alert presents while login");
            }
            if(lnkAddComment.size() > 0){
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the 'Add Your Comments' link");
        }
    }

    public void postTextComment(String comment) throws Exception {
        try{
            clickOn(lnkAddComment.get(0));
            waitForWithRefresh();
            getDriver().switchTo().frame(commentFrame);
            typeInto(commentInput, comment);
            clickOn(postCommentBtn);
            getDriver().switchTo().parentFrame();
            waitForWithRefresh();
        }catch (Exception e){
            throw new Exception("ERROR: While Posting the text comment - "+comment);
        }
    }

    public boolean verifyCommentPosted() throws Exception {
        boolean posted = false;
        try{
            waitForWithRefresh();
            getDriver().switchTo().frame(commentFrame);
            new Actions(getDriver()).moveToElement(successMsg).perform();
            if(successMsg.getText().equalsIgnoreCase("Thanks, your comment has been posted.")){
                posted = true;
            }
            getDriver().switchTo().parentFrame();
        }catch (Exception e){
            throw new Exception("ERROR: While verifying the Success Message after posting comment");
        }
        return posted;
    }

    public boolean verifyNotSignedIn() throws Exception {
        try{
            if(singIn.getText().equalsIgnoreCase("sign in")){
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the Sign-in status");
        }
    }

    public boolean verifyCommentInputNotAvailable() throws Exception {
        try{
            getDriver().switchTo().frame(commentFrame);
            return commentInput.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void clickOnAddCommentLink() throws Exception {
        try{
            clickOn(lnkAddComment.get(0));
            waitForWithRefresh();
        }catch (Exception e){
            throw new Exception("ERROR: While Clicks On the Add Comments link");
        }
    }

}
