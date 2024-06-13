package de.ait_tr.DiaHelper.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "shopping_list")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
      private String title;
    @Column(name = "date")
    private LocalDate date;

    public ShoppingList() {
    }

    public ShoppingList(Long id, String title, LocalDate date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date);
    }

    @Override
    public String toString() {
        return String.format("Shopping list: ID - %d, title - %s, date - %s",//?????date
                id, title,date);
    }
}
