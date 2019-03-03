package be.beeckman.steven.linkshortener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LinkRedirectController {

	@Autowired
	LinkRedirectService linkRedirectService;
	
	@GetMapping("admin/api/linkredirects")
	public List<LinkRedirect> getAllLinkRedirects(){
		List<LinkRedirect> list= linkRedirectService.findAll();
		System.out.println(list);
		return list;
	}
	
	@PostMapping(value = "/admin/redirect/new", produces = {MediaType.APPLICATION_JSON_VALUE})
	public LinkRedirect submitNewLinkRedirect(@RequestBody LinkRedirect linkRedirect) {
		System.out.println("Received a new LinkRedirect:");
		System.out.println(linkRedirect.getAuthor());
		return linkRedirectService.save(linkRedirect);
	}
	
	/**
	 * See https://stackoverflow.com/questions/29085295/spring-mvc-restcontroller-and-redirect#comment83885489_47411493
	 * @param shortlink
	 * @return redirect to full URL
	 */
	@GetMapping("{shortlink}")
	public ResponseEntity redirect(@PathVariable("shortlink") String shortlink) {
		
		LinkRedirect linkRedirect = linkRedirectService.getLinkRedirect(shortlink);
		if(linkRedirect != null && !linkRedirect.getLongUrl().isEmpty()) {
			linkRedirect.setNumberOfVisits(linkRedirect.getNumberOfVisits() + 1);
			linkRedirectService.save(linkRedirect);
			return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, linkRedirect.getLongUrl()).build();
		}else {
			// if not found throw 404
			/*
			 * return ResponseEntity.status(HttpStatus.NOT_FOUND).body("<!doctype html><html><head><meta charset=\"utf-8\">\r\n" + 

					"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" + 
					"		<link rel=\"stylesheet\" href=\"/lib/bootstrap/4.3.1/css/bootstrap.min.css\">\r\n" + 
					"		<title>Link Shortener</title></head><body><div class='container'><h1>Shortlink not found</h1></div></body></html>");
					*/
			// animation from https://codepen.io/vineethtr/pen/ZbKLmq found through https://bashooka.com/coding/20-super-cool-404-error-animations/
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("<!doctype html><html lang='en'><head><meta charset='utf-8'><style>      *, *:after, *:before {\r\n" + 
					"  box-sizing: border-box;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"html {\r\n" + 
					"  background: #1A1A1A;\r\n" + 
					"  font-family: Arial, \"Helvetica Neue\", Helvetica, sans-serif;\r\n" + 
					"  overflow: hidden;\r\n" + 
					"  color: #1A1A1A;\r\n" + 
					"}\r\n" + 
					"html:after, html:before {\r\n" + 
					"  content: '404';\r\n" + 
					"  position: absolute;\r\n" + 
					"  font-size: 100px;\r\n" + 
					"  left: 0;\r\n" + 
					"  right: 0;\r\n" + 
					"  text-align: center;\r\n" + 
					"  bottom: 340px;\r\n" + 
					"  font-weight: 600;\r\n" + 
					"  z-index: 200;\r\n" + 
					"}\r\n" + 
					"html:before {\r\n" + 
					"  content: 'Page Not Found';\r\n" + 
					"  font-size: 22px;\r\n" + 
					"  font-weight: 400;\r\n" + 
					"  bottom: 330px;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"head {\r\n" + 
					"  display: block;\r\n" + 
					"  width: 35px;\r\n" + 
					"  height: 140px;\r\n" + 
					"  background: #646464;\r\n" + 
					"  border-bottom: 10px solid #333;\r\n" + 
					"  margin: auto;\r\n" + 
					"  -webkit-transform-origin: 50% 100%;\r\n" + 
					"  transform-origin: 50% 100%;\r\n" + 
					"  -webkit-animation: 5s find linear infinite;\r\n" + 
					"  animation: 5s find linear infinite;\r\n" + 
					"  border-bottom-left-radius: 4px;\r\n" + 
					"  border-bottom-right-radius: 4px;\r\n" + 
					"  position: absolute;\r\n" + 
					"  left: 0;\r\n" + 
					"  right: 0;\r\n" + 
					"  bottom: -5px;\r\n" + 
					"}\r\n" + 
					"head:after {\r\n" + 
					"  content: '';\r\n" + 
					"  position: absolute;\r\n" + 
					"  border-style: solid;\r\n" + 
					"  border-width: 25px 25px 0 25px;\r\n" + 
					"  border-color: #333 transparent transparent transparent;\r\n" + 
					"  width: 85px;\r\n" + 
					"  left: -25px;\r\n" + 
					"  top: 0;\r\n" + 
					"  box-shadow: 0px -10px 0 #FEAF33;\r\n" + 
					"}\r\n" + 
					"head:before {\r\n" + 
					"  content: '';\r\n" + 
					"  position: absolute;\r\n" + 
					"  width: 8px;\r\n" + 
					"  height: 15px;\r\n" + 
					"  background: #FEAF33;\r\n" + 
					"  left: 0;\r\n" + 
					"  right: 0;\r\n" + 
					"  margin: auto;\r\n" + 
					"  top: 40px;\r\n" + 
					"  border-radius: 6px;\r\n" + 
					"  box-shadow: 0px 0 1px 2px #333;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"meta {\r\n" + 
					"  display: block;\r\n" + 
					"  width: 250px;\r\n" + 
					"  height: 200px;\r\n" + 
					"  background: #FFFEE2;\r\n" + 
					"  border-radius: 50%;\r\n" + 
					"  position: absolute;\r\n" + 
					"  left: -107px;\r\n" + 
					"  bottom: 270px;\r\n" + 
					"  margin: auto;\r\n" + 
					"  text-align: center;\r\n" + 
					"  color: #333;\r\n" + 
					"  font-size: 90px;\r\n" + 
					"  line-height: 200px;\r\n" + 
					"  font-weight: 600;\r\n" + 
					"}\r\n" + 
					"meta:after {\r\n" + 
					"  content: '';\r\n" + 
					"  position: absolute;\r\n" + 
					"  border-style: solid;\r\n" + 
					"  border-width: 190px 75px 0 75px;\r\n" + 
					"  border-color: rgba(255, 254, 226, 0.3) transparent transparent transparent;\r\n" + 
					"  width: 230px;\r\n" + 
					"  left: 10px;\r\n" + 
					"  top: 100%;\r\n" + 
					"  -webkit-transform: translateY(-60px);\r\n" + 
					"  transform: translateY(-60px);\r\n" + 
					"}\r\n" + 
					"@-webkit-keyframes find {\r\n" + 
					"  25% {\r\n" + 
					"    -webkit-transform: rotate(25deg);\r\n" + 
					"    transform: rotate(25deg);\r\n" + 
					"  }\r\n" + 
					"  0% , 50%  , 100% {\r\n" + 
					"    -webkit-transform: rotate(0deg);\r\n" + 
					"    transform: rotate(0deg);\r\n" + 
					"  }\r\n" + 
					"  75% {\r\n" + 
					"    -webkit-transform: rotate(-25deg);\r\n" + 
					"    transform: rotate(-25deg);\r\n" + 
					"  }\r\n" + 
					"}\r\n" + 
					"@keyframes find {\r\n" + 
					"  25% {\r\n" + 
					"    -webkit-transform: rotate(25deg);\r\n" + 
					"    transform: rotate(25deg);\r\n" + 
					"  }\r\n" + 
					"  0% , 50%  , 100% {\r\n" + 
					"    -webkit-transform: rotate(0deg);\r\n" + 
					"    transform: rotate(0deg);\r\n" + 
					"  }\r\n" + 
					"  75% {\r\n" + 
					"    -webkit-transform: rotate(-25deg);\r\n" + 
					"    transform: rotate(-25deg);\r\n" + 
					"  }\r\n" + 
					"}</style></head><body></body></html>");
		}
		
	}
}
