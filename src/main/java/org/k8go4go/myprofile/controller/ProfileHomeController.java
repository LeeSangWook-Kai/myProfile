package org.k8go4go.myprofile.controller;

import lombok.RequiredArgsConstructor;
import org.k8go4go.myprofile.dto.ProfileDTO;
import org.k8go4go.myprofile.service.GuestService;
import org.k8go4go.myprofile.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ProfileHomeController {
    private final GuestService guestService;

    @GetMapping("home")
    public String home(Model model, @SessionAttribute(name = "userId", required = false) String id) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "");

        ProfileDTO user = guestService.findUserById(id);

        if(user != null) {
            model.addAttribute("loginuser", user.getId());
        }
        return "index";
    }
}
