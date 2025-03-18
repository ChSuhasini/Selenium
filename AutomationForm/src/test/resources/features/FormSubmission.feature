Feature: Form Submission

  Scenario: Successful form submission
    Given User navigates to the form page
    When User enters first name "<firstname>"
    And User enters last name "<lastname>"
    And User enters email "<email>"
    And User enters phone number "<phone number>"
    And User selects gender "<gender>"
    And User enters subject "<subject>"
    And User selects date of birth "<day>""<month>""<year>"
    And User selects hobby "<hobby>"
    And User uploads image "<filepath>"
    And User submits the form
    Then User closes the browser
Examples:
      | firstname | lastname | email               | phone number     | gender | subject | day | month  | year | hobby   | filepath       |
      | John      | Doe      | john.doe@example.com | 1234567890 | Male   | Maths   | 15  | August | 1990 | Reading | /src/test/resources/screenshot.png |
      | Jane      | Smith    | jane.smith@example.com | 0987654321 | Female | Maths | 10 |  July  |  1985 | Music   | /src/test/resources/screenshot.png |
      | Tom       | Hanks    | tom.hanks@example.com | 1122334455 | Male   | Maths | 5 |  June  |  1975 | Sports  |  /src/test/resources/screenshot.png |
      | Emma      | Watson   | emma.watson@example.com | 9988776655 | Female | Maths | 22  | March  | 1995 | Dancing |  /src/test/resources/screenshot.png |
    