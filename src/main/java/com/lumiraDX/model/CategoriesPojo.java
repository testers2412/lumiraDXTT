package com.lumiraDX.model;

public class CategoriesPojo {

   private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CategoriesPojo getCategoryPojo(String name){
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        return categoriesPojo;
    }

}
