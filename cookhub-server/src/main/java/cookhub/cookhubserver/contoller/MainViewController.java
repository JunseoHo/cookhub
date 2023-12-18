package cookhub.cookhubserver.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainViewController {

    @GetMapping("health_check")
    public void healthCheck() {
        System.out.println("Server is running!");
    }


}
