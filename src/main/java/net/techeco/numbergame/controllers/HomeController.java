package net.techeco.numbergame.controllers;

import net.techeco.numbergame.core.NumberGame;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Value("${net.techeco.game.number-array-length}")
    private int numberArrayLength;

    @GetMapping("/")
    public String index(
            HttpSession session
    ) {
        Object gameObject = session.getAttribute("GameObject");
        if (gameObject == null) {
            gameObject = new NumberGame(this.numberArrayLength)
                    .generateNewArray();
            session.setAttribute("GameObject", gameObject);
        }
        return "index";
    }
}
