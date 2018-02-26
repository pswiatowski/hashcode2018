package com.katzo.hashcode.pizza.model;

public enum Ingredient implements Comparable<Ingredient>{
    M("MUSHROOM"), T("TOMATO");

    private final String representation;

    Ingredient(String representation) {
        this.representation = representation;
    }



}
