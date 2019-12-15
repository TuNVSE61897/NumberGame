package net.techeco.numbergame.controllers;

import net.techeco.numbergame.core.NumberGame;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/game")
public class GameController {
    @Value("${net.techeco.game.number-array-length}")
    private int numberArrayLength;

    @RequestMapping("/play")
    public ResponseEntity playGame(
            @RequestParam Integer numberValue
            , HttpSession session
    ) {
        Object gameObject = session.getAttribute("GameObject");
        NumberGame numberGame;
        if (gameObject == null) {
            numberGame = new NumberGame(this.numberArrayLength);
            gameObject = numberGame;
            numberGame.generateNewArray();
            session.setAttribute("GameObject", gameObject);
        } else {
            numberGame = (NumberGame) gameObject;
        }
        String rs = numberGame.checkResult(numberValue);
        return ResponseEntity.ok(rs);
    }

    @RequestMapping("/refresh")
    public ResponseEntity refresh(
            HttpSession session
    ) {
        Object gameObject = session.getAttribute("GameObject");
        NumberGame numberGame;
        if (gameObject == null) {
            numberGame = new NumberGame(this.numberArrayLength);
            gameObject = numberGame;
            session.setAttribute("GameObject", gameObject);
        } else {
            numberGame = (NumberGame) gameObject;
        }
        numberGame.generateNewArray();
        return ResponseEntity.ok().body("");
    }

    @RequestMapping("/get-series")
    public ResponseEntity getSeries(
            HttpSession session
    ) {
        Object gameObject = session.getAttribute("GameObject");
        NumberGame numberGame;
        if (gameObject == null) {
            numberGame = new NumberGame(this.numberArrayLength);
            gameObject = numberGame;
            session.setAttribute("GameObject", gameObject);
        } else {
            numberGame = (NumberGame) gameObject;
        }
        numberGame.generateNewArray();
        return ResponseEntity.ok().body(numberGame.getNumberArray());
    }
}
