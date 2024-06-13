package de.ait_tr.DiaHelper.repository;

import de.ait_tr.DiaHelper.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
