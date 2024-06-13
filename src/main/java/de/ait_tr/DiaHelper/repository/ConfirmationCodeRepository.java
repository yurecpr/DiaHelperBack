package de.ait_tr.DiaHelper.repository;

import de.ait_tr.DiaHelper.domain.entity.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Long> {

    ConfirmationCode findByCode(String code);
}
