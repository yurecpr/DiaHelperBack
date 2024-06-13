package de.ait_tr.DiaHelper.service;

import de.ait_tr.DiaHelper.domain.entity.ConfirmationCode;
import de.ait_tr.DiaHelper.domain.entity.User;
import de.ait_tr.DiaHelper.repository.ConfirmationCodeRepository;
import de.ait_tr.DiaHelper.service.interfaces.ConfirmationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationServiceImpl implements ConfirmationService {
    private ConfirmationCodeRepository repository;

    public ConfirmationServiceImpl(ConfirmationCodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String generateConfirmationCode(User user) {
        LocalDateTime expired = LocalDateTime.now().plusMinutes(1);
        String code = UUID.randomUUID().toString();

        ConfirmationCode entity = new ConfirmationCode(code, expired, user);
        repository.save(entity);
        return code;
    }
}
