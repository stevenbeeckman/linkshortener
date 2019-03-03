package be.beeckman.steven.linkshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LinkShortenerAdminPage {

	@Autowired
	LinkRedirectService linkRedirectService;
	
	@GetMapping("/admin")
	public String homepage() {
		return "admin/index";
	}
	
	@GetMapping("/admin/redirect/new")
	public String newLinkRedirect() {
		return "admin/redirect/new";
	}
	
}
