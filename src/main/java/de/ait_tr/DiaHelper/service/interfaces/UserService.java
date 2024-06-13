package de.ait_tr.DiaHelper.service.interfaces;

import de.ait_tr.DiaHelper.domain.dto.UserDto;
import de.ait_tr.DiaHelper.domain.entity.Product;
import de.ait_tr.DiaHelper.domain.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

public interface UserService extends UserDetailsService {

    User save(User user);
    User getUserById(Long id);
    UserDto getUserProfile(String email);
//    User update(User updatedUser);
//   // void deleteById(Long id);

    UserDetails loadUserByEmail(String email);

    void register(User user);

    void updatePassword(User user);

    User update(User updatedUser, String email);
    User getUserByEmail(String email);
    void addFavoriteProductToUser(String email, Product product);
    void removeFavoriteProductFromUser(String email, Product product);
    Set<Product> getFavoriteUserProduct(String email);
}
