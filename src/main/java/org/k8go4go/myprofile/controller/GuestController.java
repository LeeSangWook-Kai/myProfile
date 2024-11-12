package org.k8go4go.myprofile.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.k8go4go.myprofile.dto.ProfileDTO;
import org.k8go4go.myprofile.dto.ProfileImageDTO;
import org.k8go4go.myprofile.service.GuestService;
import org.k8go4go.myprofile.session.SessionConst;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.FileSystemException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/guest")
@Slf4j
public class GuestController {
    private final GuestService guestService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        return "guest/login";
    }

    @PostMapping("/login")
    public String loginProc(ProfileDTO vo, HttpServletRequest request) {
        HttpSession session =  request.getSession();
        ProfileDTO user = guestService.login(vo.getId(), vo.getPasswd());

        if(user != null) {
            session.setAttribute(SessionConst.LOGIN_MEMBER, user);
            session.setMaxInactiveInterval(60 * 30);
            session.setAttribute("name", user.getName());
            session.setAttribute("userid", user.getId());
            return "redirect:/home";
        }

        return "redirect:/guest/login";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "guest/signup";
    }

    @PostMapping("/signup")
    public String signupProc(ProfileDTO dto, ProfileImageDTO file) throws FileSystemException {
        log.info("signupProc : " + dto.toString());
        log.info("signupProc : " + file.toString());
        guestService.saveProfile(dto, file);
        return "redirect:/guest/login";
    }
}
