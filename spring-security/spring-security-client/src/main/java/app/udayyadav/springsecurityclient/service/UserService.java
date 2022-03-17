package app.udayyadav.springsecurityclient.service;

import app.udayyadav.springsecurityclient.entity.User;
import app.udayyadav.springsecurityclient.entity.VerificationToken;
import app.udayyadav.springsecurityclient.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);
}
