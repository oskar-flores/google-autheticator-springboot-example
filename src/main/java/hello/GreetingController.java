package hello;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    private String secretKey = "*^l002_a#diw#thda36pvz#4q_3m&q!nujoo=)lvh^l1jp@jbb";
    GoogleAuthenticator gAuth = new GoogleAuthenticator();
    final GoogleAuthenticatorKey key = gAuth.createCredentials();


    @RequestMapping("/totp")
    public String totp(Model model) {
        int code = gAuth.getTotpPassword(secretKey);
        model.addAttribute("key", code);
        return "password";
    }

    @RequestMapping("/validate")
    public String validate(@RequestParam(value = "code") Integer code, Model model) {
        final boolean authorize = gAuth.authorize(secretKey, code);
        model.addAttribute("authorize", authorize);
        return "result";
    }

}
