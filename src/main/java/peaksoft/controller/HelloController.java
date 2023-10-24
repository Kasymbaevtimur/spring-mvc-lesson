package peaksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class HelloController {
    @RequestMapping()
    public String getHello(){
        return "hello";
    }
    @RequestMapping("/salam")
    public String getSalam(){
        System.out.println("Salam duino");
        return "salam";
    }
    @RequestMapping("/privet")
    public String getPrivet(){
        return "privet";
    }

}
