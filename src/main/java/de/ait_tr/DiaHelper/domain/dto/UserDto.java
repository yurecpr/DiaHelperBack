package de.ait_tr.DiaHelper.domain.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Objects;


public class UserDto {
    private Long id;
    private String username;
    private String email;
    private BigDecimal glucoseLevel;
    private double weight;
    private double height;
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getGlucoseLevel() {
        return glucoseLevel;
    }

    public void setGlucoseLevel(BigDecimal glucoseLevel) {
        this.glucoseLevel = glucoseLevel;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Double.compare(userDto.weight, weight) == 0 && Double.compare(userDto.height, height) == 0 && age == userDto.age && Objects.equals(id, userDto.id) && Objects.equals(username, userDto.username) && Objects.equals(email, userDto.email) && Objects.equals(glucoseLevel, userDto.glucoseLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, glucoseLevel, weight, height, age);
    }

    @Override
    public String toString() {
        return String.format("User: ID - %d, name - %s, email - %s, glucoseLevel - %.2f, weight - %.2f, height - %.2f, age - %d",
                id, username, email, glucoseLevel, weight, height, age);
    }
}
