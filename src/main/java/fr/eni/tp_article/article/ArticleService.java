package fr.eni.tp_article.article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
	
	@Autowired
	IDAOArticle daoArticle;

	public String getAll() {
		
		daoArticle.findAll();
		
		return "202";
	}
	
	public String getById(String id) {
		// Essayer de trouver un  article avec le même id que celui en paramètre
		Article foundArticle = daoArticle.findById(id);
		
		// Si on trouve pas
		if (foundArticle == null) {
			return "703";
		}
		
		return "202";
	}
	
	public String save(Article article) {
		// Essayer de trouver un article existant
		Article foundArticle = daoArticle.findById(article.id);
		
		// Si on trouve Edition
		if (foundArticle != null) {
			// Mettre à jour l'article
			foundArticle.title = article.title;
			
			daoArticle.save(foundArticle);
			
			return "202";
		}
		
		// Sinon creation
		daoArticle.save(article);
		
		return "202";
	}
	
	public String deleteById(String id) {
		// Essayer de trouver un  article avec le même id que celui en paramètre
		Article foundArticle = daoArticle.findById(id);
		
		// Si on trouve pas alors on supprime pas
		if (foundArticle == null) {
			return "703";
		}
		
		// supprimer dans la liste
		daoArticle.deleteById(id);
	
		return "202";
	}
	
}
