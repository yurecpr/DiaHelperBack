package de.ait_tr.DiaHelper.controller;

import de.ait_tr.DiaHelper.domain.dto.UserDto;
import de.ait_tr.DiaHelper.domain.entity.Product;
import de.ait_tr.DiaHelper.domain.entity.User;
import de.ait_tr.DiaHelper.exception_handling.Response;
import de.ait_tr.DiaHelper.service.interfaces.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public User getById(@RequestParam Long id) {
        return service.getUserById(id);
    }

    @GetMapping("/profile")
    public UserDto getUserProfile(@AuthenticationPrincipal String email) {
        return service.getUserProfile(email);
    }

    @PutMapping("/updated-user")
    public User updateUser(@RequestBody User updatedUser, @AuthenticationPrincipal String email){
        return service.update(updatedUser, email);
    }
    @PostMapping("/favorites/add")
    public Response addFavoriteProductToUser(@AuthenticationPrincipal String email, @RequestBody Product product) {
        service.addFavoriteProductToUser(email, product);
        return new Response("Product added.");
    }

    @DeleteMapping("/favorites/remove")
    public Response removeFavoriteProductFromUser(@AuthenticationPrincipal String email, @RequestBody Product product) {
        service.removeFavoriteProductFromUser(email, product);
        return new Response("Product removed.");
    }

    @GetMapping("/favorites/list")
    Set<Product> getFavoriteUserProduct(@AuthenticationPrincipal String email) {
        return service.getFavoriteUserProduct(email);
    }
}