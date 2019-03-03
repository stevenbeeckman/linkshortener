package be.beeckman.steven.linkshortener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkRedirectService {

	@Autowired
	LinkRedirectRepository linkRedirectRepository;
	
	public LinkRedirect getLinkRedirect(String shortUrl) {
		return linkRedirectRepository.findByShortUrl(shortUrl);
	}
	
	public List<LinkRedirect> findAll(){
		return linkRedirectRepository.findAll();
	}
	
	public LinkRedirect save(LinkRedirect linkRedirect) {
		return linkRedirectRepository.save(linkRedirect);
	}
	
}
