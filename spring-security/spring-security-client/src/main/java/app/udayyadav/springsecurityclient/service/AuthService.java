package app.udayyadav.springsecurityclient.service;

import app.udayyadav.springsecurityclient.entity.User;
import app.udayyadav.springsecurityclient.entity.VerificationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class AuthService {

    public void passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl + "/savePassword?token=" + token;
        log.info("XXX Click Here to reset your password : " + url);
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    public void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl + "/verificationToken?token=" + verificationToken.getToken();
        log.info("XXX Click Here : " + url);
    }
}
