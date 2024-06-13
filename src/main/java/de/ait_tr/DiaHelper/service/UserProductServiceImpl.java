//package de.ait_tr.DiaHelper.service;
//
//import de.ait_tr.DiaHelper.domain.entity.Product;
//import de.ait_tr.DiaHelper.domain.entity.User;
//import de.ait_tr.DiaHelper.exception_handling.exceptions.ProductNotFoundException;
//import de.ait_tr.DiaHelper.exception_handling.exceptions.UserWithThisEmailNotFoundException;
//import de.ait_tr.DiaHelper.repository.ProductRepository;
//import de.ait_tr.DiaHelper.repository.UserRepository;
//import de.ait_tr.DiaHelper.service.interfaces.ProductService;
//import de.ait_tr.DiaHelper.service.interfaces.UserProductService;
//import de.ait_tr.DiaHelper.service.interfaces.UserService;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//@Service
//public class UserProductServiceImpl implements UserProductService {
//    private UserRepository userRepository;
//    private ProductRepository productRepository;
//    private UserService userService;
//    private ProductService productService;
//
////    public UserProductServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
////        this.userRepository = userRepository;
////        this.productRepository = productRepository;
////    }
//
//    public UserProductServiceImpl(UserRepository userRepository, ProductRepository productRepository, UserService userService, ProductService productService) {
//        this.userRepository = userRepository;
//        this.productRepository = productRepository;
//        this.userService = userService;
//        this.productService = productService;
//    }
//
//    @Override
//    public void addFavoriteProductToUser(String email, String productTitle) {
//        //User user = userRepository.findByEmail(email);
//        User user=userService.getUserByEmail(email);
//        if (user == null) {
//            throw new UserWithThisEmailNotFoundException(email);
//        }
//        Product product = productRepository.findByProductTitle(productTitle);
//        if (product == null) {
//            productRepository.save(product);
//        }
//        user.getProducts().add(product);
//        userRepository.save(user);
//    }
//
//    @Override
//    public void removeFavoriteProductFromUser(String email, String productTitle) {
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UserWithThisEmailNotFoundException(email);
//        }
//        Product product = productRepository.findByProductTitle(productTitle);
//        if (product == null) {
//            productRepository.save(product);
//        }
//        user.getProducts().remove(product);
//        userRepository.save(user);
//    }
//
//    @Override
//    public Set<Product> getFavoriteUserProduct(String email) {
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UserWithThisEmailNotFoundException(email);
//        }
//        return user.getProducts();
//    }
//}
