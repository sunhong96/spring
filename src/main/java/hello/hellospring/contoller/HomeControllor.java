package hello.hellospring.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllor {
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
