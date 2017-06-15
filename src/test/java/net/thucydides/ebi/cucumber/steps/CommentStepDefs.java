package net.thucydides.ebi.cucumber.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.ebi.cucumber.framework.hooks.ScenarioHook;
import net.thucydides.ebi.cucumber.pages.CommentPage;
import net.thucydides.ebi.cucumber.pages.LoginPage;
import org.junit.Assert;

/**
 * Created by rakeshnambiar on 15/06/2017.
 */
public class CommentStepDefs extends ScenarioSteps {
    private static String userName;
    private static String password;
    CommentPage commentPage;
    LoginPage loginPage;

    @Before("@CommentArticle")
    public void navigate() throws Exception {
        try{
            commentPage.openPage();
        }catch (Exception e){
            throw new Exception("ERROR: While Opening the page");
        }
    }

    /*TC_01*/
    @Given("^I have a valid \"([^\"]*)\", \"([^\"]*)\" in BBC Website$")
    public void i_have_a_valid_in_BBC_Website(String userId, String pwd) throws Throwable {
        ScenarioHook.getScenario().write("Opened the BBC news article to comment");
        userName = userId;
        password = pwd;
    }

    @Given("^logged in to the BBC website successfully$")
    public void logged_in_to_the_BBC_website_successfully() throws Throwable {
        loginPage.loginToBBC(userName,password);
        Assert.assertTrue("ERROR: User NOT logged in Successfully",loginPage.isLoggedInSuccessfully());
    }

    @Given("^I am on the news article page to comment$")
    public void i_am_on_the_news_article_page_to_comment() throws Throwable {
        Assert.assertTrue("ERROR: Link Add Your Comments NOT available", commentPage.verifyAddCommentLnkAvailable());
    }

    @When("^I post a \"([^\"]*)\"$")
    public void i_post_a(String commentText) throws Throwable {
        commentPage.postTextComment(commentText);
        ScenarioHook.takeScreenshot();
    }

    @Then("^comment should successfully posted on the website$")
    public void comment_should_successfully_posted_on_the_website() throws Throwable {
        Assert.assertTrue("ERROR: Comment NOT successfully posted", commentPage.verifyCommentPosted());
        ScenarioHook.takeScreenshot();
    }

    /*TC_02*/
    @Given("^I haven't logged in yet to the BBC Website$")
    public void i_haven_t_logged_in_yet_to_the_BBC_Website() throws Throwable {
        Assert.assertTrue("ERROR: Unexpected Sign-in status", commentPage.verifyNotSignedIn());
    }

    @When("^I try to post a comment$")
    public void i_try_to_post_a_comment() throws Throwable {
        commentPage.clickOnAddCommentLink();
    }

    @Then("^application should not display the comment input field since the user is logged out$")
    public void application_should_not_display_the_comment_input_field_since_the_user_is_logged_out() throws Throwable {
        Assert.assertFalse("ERROR: Comment Provision available without Login",commentPage.verifyCommentInputNotAvailable());
    }

}
