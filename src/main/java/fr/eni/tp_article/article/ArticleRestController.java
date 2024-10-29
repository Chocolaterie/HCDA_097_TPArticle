package fr.eni.tp_article.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleRestController {

	@Autowired
	ArticleService articleService;
	
	@GetMapping("/articles")
	public String getAll() {
		return articleService.getAll();
	}
	
	@GetMapping("/article/{id}")
	public String getById(@PathVariable("id") String id) {
		return articleService.getById(id);
	}

	@PostMapping("/article")
	public String save(@RequestBody Article article) {
		return articleService.save(article);
	}
	
	@DeleteMapping("/article/{id}")
	public String deletById(@PathVariable("id") String id) {
		return articleService.deleteById(id);
	}
}
