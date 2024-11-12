package org.k8go4go.myprofile.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.k8go4go.myprofile.dto.PageDTO;
import org.k8go4go.myprofile.dto.Pager;
import org.k8go4go.myprofile.dto.ProfileDTO;
import org.k8go4go.myprofile.dto.ProfileImageDTO;
import org.k8go4go.myprofile.service.ProfileService;
import org.k8go4go.myprofile.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.FileSystemException;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
    private final ProfileService profileService;
    @GetMapping("/all")
    public String allMembers(Pager pager, Model model) {
        model.addAttribute("users", profileService.findAllUser(pager));
        model.addAttribute("pageInfo", new PageDTO(pager, profileService.totalCount(pager)));
        return "profile/members";
    }

    @GetMapping("/{id}")
    public String profile(@PathVariable(name="id") String id, HttpServletRequest request, Model model) {
        HttpSession session =  request.getSession();
        ProfileDTO user = (ProfileDTO)session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("user", user);
        return "profile/userinfo";
    }

    @PostMapping("/{id}")
    public String updateProfile(@PathVariable(name="id") String id, Model model, ProfileDTO modifyUser, ProfileImageDTO modifyFile) throws FileSystemException {
        ProfileDTO selectedUser = profileService.findUserById(id);
        if(selectedUser == null || !modifyUser.getId().equals(selectedUser.getId())) {
            throw new IllegalArgumentException("Not Fount");
        }
        modifyUser.setPid(selectedUser.getPid());
        profileService.modifyProfile(modifyUser, modifyFile);
        model.addAttribute("user", modifyUser);
        return "profile/userinfo";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute(SessionConst.LOGIN_MEMBER);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/guest/login";
    }
}
