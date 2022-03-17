package app.udayyadav.springsecurityclient.controller;

import app.udayyadav.springsecurityclient.entity.User;
import app.udayyadav.springsecurityclient.entity.VerificationToken;
import app.udayyadav.springsecurityclient.event.RegistrationCompleteEvent;
import app.udayyadav.springsecurityclient.model.PasswordModel;
import app.udayyadav.springsecurityclient.model.UserModel;
import app.udayyadav.springsecurityclient.service.AuthService;
import app.udayyadav.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final AuthService authService;

    @Autowired
    public RegistrationController(UserService userService, ApplicationEventPublisher applicationEventPublisher, AuthService authService) {
        this.userService = userService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.authService = authService;
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return "User verified successfully";
        }
        return "Bad User Verification";
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request) {
        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        authService.resendVerificationTokenMail(user, authService.applicationUrl(request), verificationToken);
        return "Verification Link Sent";
    }


    @PostMapping("/register")
    public User registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        User user = userService.registerUser(userModel);
        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user, authService.applicationUrl(request)));
        user.setPassword("");
        return user;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token, @RequestBody PasswordModel passwordModel) {
        String result = userService.validatePasswordResetToken(token);
        if (!result.equalsIgnoreCase("valid")) {
            return "Invalid token";
        }

        Optional<User> user = userService.getUserByPasswordResetToken(token);
        if (user.isEmpty()) {
            return "invalid token";
        }

        userService.changePassword(user.get(), passwordModel.getNewPassword());
        return "Password Reset successful";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {
        User user = userService.findUserByEmail(passwordModel.getEmail());
        String url = "";

        if (user != null) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            authService.passwordResetTokenMail(user, authService.applicationUrl(request), token);
        }
        return "reset token sent";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel) {
        User user = userService.findUserByEmail(passwordModel.getEmail());
        if(!userService.checkIfValidOldPassword(user,passwordModel.getOldPassword())) {
            return "invalid old password";
        }

        // save new passsword method
        userService.changePassword(user, passwordModel.getNewPassword());
        return "password changed successfully";

    }


}
