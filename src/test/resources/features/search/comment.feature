@CommentArticle
Feature: An audience member visits BBC News Article and successfully provides their comment on one of the news articles
  In order to comment to a news article on BBC Website
  As a registered BCC user
  I should have login first to the website

  @TC01_BCC_CommentTest
  Scenario Outline: To verify comment functionality with different data combinations
    Given I have a valid "<UserName>", "<Password>" in BBC Website
    And logged in to the BBC website successfully
    And I am on the news article page to comment
    When I post a "<comment>"
    Then comment should successfully posted on the website

    Examples:
      | UserName                  | Password    | comment                       |
      | rakeshnambiar.c@gmail.com | Password_01 | This is a string Test comment |
#      | rakeshnambiar.c@gmail.com | Password_01 | This is a string comment with @Specialchar         |
#      | rakeshnambiar.c@gmail.com | Password_01 | $%#$%$^#%^%$^$%^$%                                 |
#      | rakeshnambiar.c@gmail.com | Password_01 | 3432434234324324324                                |
#      | rakeshnambiar.c@gmail.com | Password_01 | 4545$%$%#$%sdfgfgfgfg                              |
#      | rakeshnambiar.c@gmail.com | Password_01 | https://www.youtube.com/watch?v=4yT7YPxU6C8&t=603s |

  @TC02_BCC_CommentTest
  Scenario: To verify the comments can't be posted without sign-in to the website
    Given I am on the news article page to comment
    And I haven't logged in yet to the BBC Website
    When I try to post a comment
    Then application should not display the comment input field since the user is logged out

  @TC04_BCC_CommentTest
  Scenario: To verify comment posting with an image with various format


  @TC05_BCC_CommentTest
  Scenario: Upload a video footage as comment, with various format

  #Twitter or facebook comments

  #post duplicate comments and there should be validation - It is failing

  #Post comments with some smileys


