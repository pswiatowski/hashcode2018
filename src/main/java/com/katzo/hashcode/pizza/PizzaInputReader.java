package com.katzo.hashcode.pizza;

import com.katzo.hashcode.pizza.model.Ingredient;
import com.katzo.hashcode.pizza.model.Pizza;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class PizzaInputReader {

    public Pizza readFile(String fileName) throws Exception {
        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI());

        List<String> data = Files.readAllLines(path);
        String[] initParams = data.get(0).split(" ");

        int rows = Integer.parseInt(initParams[0]);
        int columns = Integer.parseInt(initParams[1]);

        Ingredient[][] ingredients = new Ingredient[rows][columns];
        int minimumNumberOfIngredient = Integer.parseInt(initParams[2]);
        int maximumNumberOfCellsPerSlice = Integer.parseInt(initParams[3]);

        data.remove(0);
        for (int i = 0; i < rows; i++) {
            String row = data.get(i);

            for (int j = 0; j < row.length(); j++) {
                String ingredientValue = String.valueOf(row.charAt(j));
                ingredients[i][j] = Ingredient.valueOf(ingredientValue);
            }
        }

        return new Pizza(ingredients, minimumNumberOfIngredient, maximumNumberOfCellsPerSlice);
    }

}
