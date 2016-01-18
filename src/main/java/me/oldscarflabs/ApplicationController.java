package me.oldscarflabs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApplicationController {

	@RequestMapping("/home")
    public String home(@RequestParam(value="artist", required=false, defaultValue="Kanye West") String artist, Model model) {
        model.addAttribute("artist", artist);
		return "home";
    }
	
}
