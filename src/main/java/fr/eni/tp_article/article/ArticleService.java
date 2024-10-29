package fr.eni.tp_article.article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ArticleService {

	List<Article> articles = new ArrayList<Article>();
	
	public ArticleService() {
		articles.add(new Article(1L, "Chocolatine"));
		articles.add(new Article(2L, "Escargot au Chocolat"));
		articles.add(new Article(3L, "Pain au chocolat"));
	}
	
	public String getAll() {
		return "202";
	}

	public String getById(Long id) {
		
		// Solution 1
		//Optional<Article> foundArticle = articles.stream().filter(article -> article.id == id).findFirst();
		
		// Solution 2
		// Essayer de trouver un  article avec le même id que celui en paramètre
		Article foundArticle = articles.stream().filter(article -> article.id == id).findFirst().orElse(null);
		
		// Si on trouve pas
		if (foundArticle == null) {
			return "703";
		}
		
		return "202";
	}
	
	public String save(Article article) {
		// Essayer de trouver un article existant
		Article foundArticle = articles.stream().filter(value -> value.id == article.id).findFirst().orElse(null);
		
		// Si on trouve Edition
		if (foundArticle != null) {
			// Mettre à jour l'article
			foundArticle.title = article.title;
			
			return "202";
		}
		
		// Sinon creation
		articles.add(article);
		return "202";
	}
	
	public String deleteById(Long id) {
		
		// Essayer de trouver un  article avec le même id que celui en paramètre
		Article foundArticle = articles.stream().filter(article -> article.id == id).findFirst().orElse(null);
		
		// Si on trouve pas alors on supprime pas
		if (foundArticle == null) {
			return "703";
		}
		
		// supprimer dans la liste
		int index = articles.indexOf(foundArticle);
		
		articles.remove(index);
		
		return "202";
	}
	
}
