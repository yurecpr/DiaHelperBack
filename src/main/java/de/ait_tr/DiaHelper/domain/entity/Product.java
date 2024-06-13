package de.ait_tr.DiaHelper.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "product_name")
    @NotNull(message = "Product title can not be null")
    @NotBlank(message = "Product title can not be empty")
    @Pattern(
            regexp = "[A-Z][a-z]{2,}",
            message = "Product title should be at least 3 character length, " +
                    " start with capital letter and may contain only latin characters"
    )
    private String productTitle;


    @Column(name = "calories")
    @NotNull(message = "Can not be null")
    @NotBlank(message = "Can not be blank")
    private int calories;
//    @ManyToMany
//    @JoinTable(
//            name = "user_product",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private Set<User> forFavorites;

    public Product() {
    }

    public Product(Long id, String productTitle, int calories) {
        this.id = id;
        this.productTitle = productTitle;
        this.calories = calories;
    }


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
        Product product = (Product) o;
        return calories == product.calories && Objects.equals(productTitle, product.productTitle);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productTitle, calories);

    }

    @Override
    public String toString() {
        return String.format("Product: ID - %d, product_title - %s, calories - %d",
                id, productTitle, calories);
    }
}