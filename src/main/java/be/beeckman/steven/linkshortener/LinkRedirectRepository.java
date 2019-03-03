/**
 * 
 */
package be.beeckman.steven.linkshortener;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 *
 */
public interface LinkRedirectRepository extends CrudRepository<LinkRedirect, String> {

	public LinkRedirect findByShortUrl(String shortUrl);
	
	public List<LinkRedirect> findAll();
}
