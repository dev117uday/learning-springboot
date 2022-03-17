package app.udayyadav.springsecurityclient.event.listener;

import app.udayyadav.springsecurityclient.entity.User;
import app.udayyadav.springsecurityclient.event.RegistrationCompleteEvent;
import app.udayyadav.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;


    @Autowired
    public RegistrationCompleteEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // create verification token fr the user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        userService.saveVerificationTokenForUser(token, user);
        /// send the mail to user

        String url = event.getApplicationUrl() + "/verificationToken?token=" + token;
        log.info("XXX Click Here : " + url);

    }
}
