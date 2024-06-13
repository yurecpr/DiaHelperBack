package de.ait_tr.DiaHelper.service.interfaces;

import de.ait_tr.DiaHelper.domain.entity.User;

public interface ConfirmationService {

    String generateConfirmationCode(User user);
}