package com.pickdate.iam.infrastructure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginViewController {

    @GetMapping("/login")
    String login() {
        return "login";
    }
}
