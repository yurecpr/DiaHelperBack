package de.ait_tr.DiaHelper.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
public class User implements UserDetails {

//    public static void main(String[] args) {
//        new BCryptPasswordEncoder().encode("111");
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NotBlank(message = "User name can not be empty")
    @Pattern(
            regexp = "[A-Z][a-z]{2,}",
            message = "User name should be at least 3 character length, " +
                    " start with capital letter and may contain only latin characters"
    )
    private String username;

    @Column(name = "password")
    @NotNull(message = "Password can not be null")
    @NotEmpty(message = "Password can not be empty")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long, contain at least one uppercase letter, one digit, and one special character (@$!%*?&)"
    )
    private String password;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    //@JoinColumn(name = "role_id")
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
   // @ManyToMany(mappedBy = "forFavorites")

   @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
   @JoinTable(
           name = "user_product",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "product_id")
   )
    private Set<Product> products = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getTitle()))
                .collect(Collectors.toList());
    }


    @Column(name = "email")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email format: email should contain only latin letters, digits, '@', '-', '_', and have a valid domain"
    )
    @NotNull(message = "Email can not be null")
    @NotEmpty(message = "Email can not be empty")
    private String email;

    @Column(name = "glucose_level")
//    @NotNull(message = "Glucose level can not be null")
//    @DecimalMin(value = "1", inclusive = false, message = "Glucose level must be greater than 0")
    private BigDecimal glucoseLevel;

    @Column(name = "weight")
    @NotNull(message = "Weight can not be null")
    private double weight;

    @Column(name = "height")
    @NotNull(message = "Height can not be null")
    private double height;

    @Column(name = "age")
    @NotNull(message = "Age can not be null")
    private int age;


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public void setUsername(String username) {
        this.username = username;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isActive == user.isActive && Double.compare(user.weight, weight) == 0 && Double.compare(user.height, height) == 0 && age == user.age && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && Objects.equals(products, user.products) && Objects.equals(email, user.email) && Objects.equals(glucoseLevel, user.glucoseLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, isActive, roles, products, email, glucoseLevel, weight, height, age);
    }

    @Override
    public String toString() {
        return String.format("User: ID - %d, name - %s, password - %s, active - %s, role - %s, email - %s, glucoseLevel - %.2f, weight - %.2f, height - %.2f, age - %d",
                id, username, password, isActive ? "yes" : "no", roles, email, glucoseLevel, weight, height, age);
    }


}
