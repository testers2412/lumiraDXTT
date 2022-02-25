package com.lumiraDX.cucumber.steps;


import com.lumiraDX.categoriesinfo.CategoriesSteps;
import com.lumiraDX.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;

public class CategoriesStepDefs extends TestUtils {

    static ValidatableResponse response;
    static String catName;
    static List<Integer> newIdList;
    static List<Integer> oldIdList;
    static int id;
    static String updatedName;


    @Steps
    CategoriesSteps categoriesSteps;


    @When("^I create a new category \"([^\"]*)\"$")
    public void iCreateANewCategory(String catName) {
        this.catName = catName;
        // To get old ID list
        response = categoriesSteps.getAllCategories();
        oldIdList = response.extract().path("id");
        System.out.println("Old id list is : " + oldIdList);
        response = categoriesSteps.createNewCategory(catName);
        response.statusCode(201).log().ifValidationFails();
    }

    @And("^I extract the ID of the newly created category$")
    public void iExtractTheIDOfTheNewlyCreatedCategory() {

        response = categoriesSteps.getAllCategories();
        response.log().ifValidationFails();
        newIdList = response.extract().path("id");
        System.out.println("The id list is : " + newIdList);
        newIdList.removeAll(oldIdList);
        System.out.println(newIdList);
        id = newIdList.get(0);
        System.out.println("The id is : " + id);

    }

    @Then("^I verifies the new category has been created successfully by ID$")
    public void iVerifiesTheNewCategoryHasBeenCreatedSuccessfullyByID() {
        response = categoriesSteps.getCategoryById(id);
        response.statusCode(200).log().ifValidationFails();
        System.out.println("The response is: " + response);
    }

    @When("^I read the newly created category by ID$")
    public void iReadTheNewlyCreatedCategoryByID() {
       response = categoriesSteps.getCategoryById(id);
       response.statusCode(200).log().ifValidationFails();
    }

    @Then("^I verify the new category has been created successfully with correct name$")
    public void iVerifyTheNewCategoryHasBeenCreatedSuccessfullyWithCorrectName() {
        //Bug!!
        System.out.println("Expected name is : " + catName);
        System.out.println("Actual name is : " + response.extract().path("name"));
        Assert.assertThat(response.extract().path("name"), equalTo(catName));
    }
    @When("^I update the newly created category \"([^\"]*)\"$")
    public void iUpdateTheNewlyCreatedCategory(String updatedName) {
        this.updatedName = updatedName;
        response= categoriesSteps.updateCategoriesRecords(updatedName,id);
        response.statusCode(204).log().ifValidationFails();

    }

    @Then("^I should verify that the update has been performed successfully$")
    public void iShouldVerifyThatTheUpdateHasBeenPerformedSuccessfully() {
        response = categoriesSteps.getCategoryById(id);
        System.out.println("Expected name is : " + updatedName);
        System.out.println("Actual name is : " + response.extract().path("name"));
        Assert.assertThat(response.extract().path("name"), equalTo(updatedName));
    }


    @When("^I delete the newly created category by ID$")
    public void iDeleteTheNewlyCreatedCategoryByID() {
        response = categoriesSteps.deleteCategoriesRecords(id);
        response.statusCode(204).log().ifValidationFails();
    }

    @Then("^I should verify that the deletion has been performed successfully$")
    public void iShouldVerifyThatTheDeletionHasBeenPerformedSuccessfully() {
        response = categoriesSteps.getCategoryById(id);
        response.statusCode(404).log().ifValidationFails();
    }

}
