package com.katzo.hashcode.pizza.model;

import java.util.Arrays;
import java.util.Objects;

public class Pizza {

    private final Ingredient[][] pizza;

    private final int minimumNumberOfIngredient;
    private final int maximumNumberOfCellsPerSlice;

    public Pizza(Ingredient[][] pizza, int minimumNumberOfIngredient, int maximumNumberOfCellsPerSlice) {
        this.pizza = pizza;
        this.minimumNumberOfIngredient = minimumNumberOfIngredient;
        this.maximumNumberOfCellsPerSlice = maximumNumberOfCellsPerSlice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza)) return false;
        Pizza pizza1 = (Pizza) o;
        return minimumNumberOfIngredient == pizza1.minimumNumberOfIngredient &&
                maximumNumberOfCellsPerSlice == pizza1.maximumNumberOfCellsPerSlice &&
                Arrays.deepEquals(pizza, pizza1.pizza);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(minimumNumberOfIngredient, maximumNumberOfCellsPerSlice);
        result = 31 * result + Arrays.hashCode(pizza);
        return result;
    }
}
