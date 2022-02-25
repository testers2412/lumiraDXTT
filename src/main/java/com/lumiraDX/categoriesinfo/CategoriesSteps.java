package com.lumiraDX.categoriesinfo;

import com.lumiraDX.constants.EndPoints;
import com.lumiraDX.model.CategoriesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class CategoriesSteps {
    @Step("Create New Category with Name: {0}")
    public ValidatableResponse createNewCategory(String name){
        CategoriesPojo categoriesPojo = CategoriesPojo.getCategoryPojo(name);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(categoriesPojo)
                .when()
                .post(EndPoints.CATEGORIES)
                .then();
    }
  @Step ("Get All Categories")
    public ValidatableResponse getAllCategories(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.CATEGORIES)
                .then();
    }
    @Step ("Get Category By Id : {0}")
    public ValidatableResponse getCategoryById(int iD){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", iD)
                .when()
                .get(EndPoints.GETID)
                .then();
    }
    @Step ("Update Categories Records by Updated Name : {0}, ID : {1}")
    public ValidatableResponse updateCategoriesRecords(String updatedName, int iD){
        CategoriesPojo categoriesPojo = CategoriesPojo.getCategoryPojo(updatedName);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", iD)
                .body(categoriesPojo)
                .when()
                .put(EndPoints.GETID)
                .then();
    }
    @Step ("Delete Categories Records by ID : {0}")
    public ValidatableResponse deleteCategoriesRecords(int iD){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", iD)
                .when()
                .delete(EndPoints.GETID)
                .then();
    }

}
