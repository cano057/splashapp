package com.Connection;

import com.ecommerce.model.*;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientClass {

    private static String token;
    private static JwtUser user;
    private static ArrayList<Category> categories;
    private static ArrayList<Product> products;
    private static ArrayList<Products> categoriesProducts;

    public ClientClass(JwtUser user) {
        this.user = user;
        this.token = null;
        categories = new ArrayList<>();
        products = new ArrayList<>();
        categoriesProducts = new ArrayList<>();
    }

    public static void setToken(String _token) {
        token = _token;
    }

    public static ArrayList<Category> getCategories(){
        return categories;
    }

    public static ArrayList<Product> getProducts(){
        return products;
    }

    public static void setCategories(ArrayList<Category> _categories){
        categories = _categories;
    }

    public static void setProducts(ArrayList<Product> _products) {
        products = _products;
    }

    public static void addCategoryProducts(Products products){
        categoriesProducts.add(products);
    }

    public static void resetCategoriesProducts() {
        categoriesProducts.clear();
    }

    public class Products {
        private String nameCategory;
        private ArrayList<Product> products;

        public Products (ArrayList<Product> _products, String _nameCategory) {
            this.nameCategory = _nameCategory;
            this.products = _products;
        }

        public void setNameCategory(String newNameCategory) {
            this.nameCategory = newNameCategory;
        }

        public void setProducts(ArrayList<Product> newProducts){
            this.products = newProducts;
        }

        public String getNameCategory(){
            return this.nameCategory;
        }

        public ArrayList<Product> getProducts() {
            return products;
        }
    }
}

