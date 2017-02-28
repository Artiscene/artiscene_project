//package com.artiscene.services;
//
//import com.artiscene.models.PasswordResetToken;
//import com.artiscene.models.User;
//
//import java.util.Locale;
//
//import static com.sun.org.apache.xml.internal.serializer.utils.Utils.messages;
//
///**
// * Created by vanessamnoble on 2/27/17.
// */
//
//public class ResetPasswordService {
//
//    public void createPasswordResetTokenForUser(User user, String token) {
//        PasswordResetToken myToken = new PasswordResetToken(token, user);
//        passwordTokenRepository.save(myToken);
//    }
//    private SimpleMailMessage constructResetTokenEmail(
//            String contextPath, Locale locale, String token, User user) {
//        String url = contextPath + "/user/changePassword?id=" +
//                user.getId() + "&token=" + token;
//        String message = messages.getMessage("message.resetPassword",
//                null, locale);
//        return constructEmail("Reset Password", message + " \r\n" + url, user);
//    }
//
//    private SimpleMailMessage constructEmail(String subject, String body, User user) {
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setSubject(subject);
//        email.setText(body);
//        email.setTo(user.getEmail());
//        email.setFrom(env.getProperty("support.email"));
//        return email;
//    }
//
//    public void changeUserPassword(User user, String password) {
//        user.setPassword(passwordEncoder.encode(password));
//        repository.save(user);
//    }
//}
