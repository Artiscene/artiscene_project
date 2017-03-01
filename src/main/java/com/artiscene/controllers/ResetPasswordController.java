//package com.artiscene.controllers;
//
//import com.artiscene.models.User;
//import com.artiscene.repositories.UserRepository;
//import com.artiscene.services.GenericResponse;
//import com.artiscene.services.ResetPasswordService;
//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.validation.Valid;
//import java.util.Locale;
//import java.util.UUID;
//
//import static com.sun.org.apache.xml.internal.serializer.utils.Utils.messages;
//
//@Controller
//class ResetPasswordController {
//    @Autowired
//    UserRepository repository;
//
//    ResetPasswordService userService;
//
//    /**
//     * Created by vanessamnoble on 2/27/17.
//     */
//    @RequestMapping(value = "/user/resetPassword",
//            method = RequestMethod.POST)
//    @ResponseBody
//    public GenericResponse resetPassword(HttpServletRequest request,
//
//  @RequestParam("email") String userEmail) {
//    User user = repository.findByEmail(userEmail);
//    if (user == null) {
//        throw new UsernameNotFoundException();
//    }
//    String token = UUID.randomUUID().toString();
//    userService.createPasswordResetTokenForUser(user, token);
//    userService.send(constructResetTokenEmail(getAppUrl(request),
//                      request.getLocale(), token, user));
//    return new GenericResponse(
//                      messages.getMessage("message.resetPasswordEmail", null,
//                      request.getLocale()));
//    }
//    @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
//    @ResponseBody
//    public GenericResponse savePassword(Locale locale,
//                                        @Valid PasswordDto passwordDto) {
//        User user =
//                (User) SecurityContextHolder.getContext()
//                        .getAuthentication().getPrincipal();
//
//        userService.changeUserPassword(user, passwordDto.getNewPassword());
//        return new GenericResponse(
//                messages.getMessage("message.resetPasswordSuc", null, locale));
//    }
//
//
//}

