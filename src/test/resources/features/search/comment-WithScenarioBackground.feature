Feature: An audience member visits www.bbc.co.uk/news and successfully provides their comment on one of the news articles
  In order to comment to a news article on BBC Website
  As a registered BCC user
  I should have login first to the website

  Background:
    Given a registered user on BBC website with the email address "rakeshnambiar.c@gmail.com"
    And with the valid password "Password_01"
    And logged in to the BBC website successfully


  @TC01_BCC_CommentTest
  Scenario Outline: To verify comment functionality with different data combinations
    Given I am on the news article page to comment
    When I post a "<comment>"
    Then comment should successfully posted on the website

    Examples:
      | comment                                            |
      | This is a string Test comment                      |
      | This is a string comment with @Specialchar         |
      | $%#$%$^#%^%$^$%^$%                                 |
      | 3432434234324324324                                |
      | 4545$%$%#$%sdfgfgfgfg                              |
      | https://www.youtube.com/watch?v=4yT7YPxU6C8&t=603s |


  @TC02_BCC_CommentTest
    Scenario: To verify the comments can't be posted without sign-in to the website
    Given I am on the news article page to comment
    And I have logged out from the BBC website
    When I try to post a comment
    Then application should not display the comment input field since the user is logged out

  @TC04_BCC_CommentTest
  Scenario: To verify comment posting with an image with various format


  @TC05_BCC_CommentTest
  Scenario: Upload a video footage as comment, with various format

  #Twitter or facebook comments

  #post duplicate comments and there should be validation - It is failing

  #Post comments with some smileys


