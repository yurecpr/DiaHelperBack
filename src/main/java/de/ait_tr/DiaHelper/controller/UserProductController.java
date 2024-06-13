//package de.ait_tr.DiaHelper.controller;
//
//import de.ait_tr.DiaHelper.domain.entity.Product;
//import de.ait_tr.DiaHelper.exception_handling.Response;
//import de.ait_tr.DiaHelper.service.interfaces.UserProductService;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Set;
//
//@RestController
//@RequestMapping("/favorites")
//public class UserProductController {
//    private UserProductService userProductService;
//
//    public UserProductController(UserProductService userProductService) {
//        this.userProductService = userProductService;
//    }
//
//    @PostMapping("/add")
//    public Response addFavoriteProductToUser(@AuthenticationPrincipal String email, @RequestBody String productTitle) {
//        userProductService.addFavoriteProductToUser(email, productTitle);
//        return new Response("Product added.");
//    }
//
//    @DeleteMapping("/remove")
//    public Response removeFavoriteProductFromUser(@AuthenticationPrincipal String email, @RequestBody String productTitle) {
//        userProductService.removeFavoriteProductFromUser(email, productTitle);
//        return new Response("Product removed.");
//    }
//
//    @GetMapping("/list")
//    Set<Product> getFavoriteUserProduct(String email) {
//        return userProductService.getFavoriteUserProduct(email);
//    }
//}
