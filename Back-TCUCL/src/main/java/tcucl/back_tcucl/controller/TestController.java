package tcucl.back_tcucl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static tcucl.back_tcucl.controller.ControllerConstante.REST_API;

@RestController
@RequestMapping(REST_API + "/test")
public class TestController {

    @GetMapping
    public String testget() {
        return "Test Fonctionnel";
    }

    @PostMapping("/{value}]")
    public String testPost(@PathVariable Object value) {
        return ("Test fonctionnel.\n Valeur reçue : " + value.toString());
    }
}
