package de.ait_tr.DiaHelper.domain.dto;

import de.ait_tr.DiaHelper.domain.entity.User;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class ProductDto {
    private Long id;
    private String productTitle;
    private int calories;
    private Set<User> forFavorites;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }


    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Set<User> getForFavorites() {
        return forFavorites;
    }

    public void setForFavorites(Set<User> forFavorites) {
        this.forFavorites = forFavorites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return calories == that.calories && Objects.equals(id, that.id) && Objects.equals(productTitle, that.productTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productTitle, calories);
    }

    @Override
    public String toString() {
        return String.format("Product: ID - %d, productTitle - %s, calories - %d ",
                id, productTitle, calories);
    }
}