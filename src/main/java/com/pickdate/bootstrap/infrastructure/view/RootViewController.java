package com.pickdate.bootstrap.infrastructure.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class RootViewController {

    @GetMapping("/")
    String index() {
        return "index";
    }
}
