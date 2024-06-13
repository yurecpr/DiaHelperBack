package de.ait_tr.DiaHelper.repository;

import de.ait_tr.DiaHelper.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByTitle(String title);
}
