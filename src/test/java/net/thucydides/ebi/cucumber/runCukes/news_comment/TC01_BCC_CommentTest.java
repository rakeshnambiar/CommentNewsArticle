package net.thucydides.ebi.cucumber.runCukes.news_comment;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.thucydides.ebi.cucumber.tags.Comments;
import net.thucydides.ebi.cucumber.tags.SanityTest;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(tags = {"@TC01_BCC_CommentTest"},
        format = {"pretty", "html:target/Destination/TC01_BCC_CommentTest",
                "json:target/cucumber-report/TC01_BCC_CommentTest.json"},
        features= {"src/test/resources/features/search/comment.feature"},
        glue = {"net.thucydides.ebi.cucumber"})

@Category({Comments.class,SanityTest.class})
public class TC01_BCC_CommentTest {
}
