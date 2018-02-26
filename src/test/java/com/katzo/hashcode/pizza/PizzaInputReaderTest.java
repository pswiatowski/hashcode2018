package com.katzo.hashcode.pizza;

import com.katzo.hashcode.pizza.model.Ingredient;
import com.katzo.hashcode.pizza.model.Pizza;
import org.junit.Assert;
import org.junit.Test;

public class PizzaInputReaderTest {

    @Test
    public void readFile() throws Exception {
        Pizza pizza = new PizzaInputReader().readFile("pizza");

        // then
        Ingredient[][] expectedIngredients = new Ingredient[][]{
                {Ingredient.T, Ingredient.T, Ingredient.T, Ingredient.T, Ingredient.T},
                {Ingredient.T, Ingredient.M, Ingredient.M, Ingredient.M, Ingredient.T},
                {Ingredient.T, Ingredient.T, Ingredient.T, Ingredient.T, Ingredient.T}
        };

        Pizza expectedPizza = new Pizza(expectedIngredients, 1, 6);

        Assert.assertTrue(expectedPizza.equals(pizza));
    }
}