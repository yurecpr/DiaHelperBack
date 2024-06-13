package de.ait_tr.DiaHelper.service.interfaces;

import de.ait_tr.DiaHelper.domain.entity.User;

public interface EmailService {
    void sendConfirmationEmail(User user, String password);
    void sendUpdateToPassword(User user, String password);
}
