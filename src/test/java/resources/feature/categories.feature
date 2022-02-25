@Regression
Feature: Categories Functionality
  Testing the Categories functionality by performing end to end CRUD operation
@Smoke
  Scenario: I should be able to create a new category successfully
    When I create a new category "Health"
    And I extract the ID of the newly created category
    Then I verifies the new category has been created successfully by ID

  Scenario: I should be able to read the newly created category successfully
    When I read the newly created category by ID
    Then I verify the new category has been created successfully with correct name

  Scenario: I should be able to update the newly created category
   When I update the newly created category "Updated_Health"
   Then I should verify that the update has been performed successfully

  Scenario: I should be able to delete the newly created ID successfully
   When I delete the newly created category by ID
   Then I should verify that the deletion has been performed successfully

