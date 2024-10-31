package fr.eni.tp_article.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.tp_article.ServiceResponse;

@CrossOrigin
@RestController
public class ArticleRestController {

	@Autowired
	ArticleService articleService;
	
	@GetMapping("/articles")
	public ServiceResponse<List<Article>> getAll() {
		return articleService.getAll();
	}
	
	@GetMapping("/article/{id}")
	public ServiceResponse<Article> getById(@PathVariable("id") Long id) {
		return articleService.getById(id);
	}

	@PostMapping("/article")
	public ServiceResponse<Article> save(@RequestBody Article article) {
		return articleService.save(article);
	}
	
	@DeleteMapping("/article/{id}")
	public ServiceResponse<Article> deletById(@PathVariable("id") Long id) {
		return articleService.deleteById(id);
	}
}
