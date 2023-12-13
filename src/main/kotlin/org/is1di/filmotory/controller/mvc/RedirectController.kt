package org.is1di.filmotory.controller.mvc

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RedirectController {
    @GetMapping
    fun redirect(): String = "redirect:/main"
}