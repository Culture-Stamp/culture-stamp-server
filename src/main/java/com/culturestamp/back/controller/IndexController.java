package com.culturestamp.back.controller;


import com.culturestamp.back.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @RequestMapping("/")
    public String toIndex(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("isUser", "true");
            model.addAttribute("nickname", user.getNickname());
        } else {
            model.addAttribute("isUser", "false");
        }
        return "index";
    }

}
