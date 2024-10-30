package fr.eni.tp_article.article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.tp_article.ServiceResponse;

@Service
public class ArticleService {
	
	@Autowired
	IDAOArticle daoArticle;

	public ServiceResponse<List<Article>> getAll() {
		
		List<Article> articles = daoArticle.findAll();
		
		return ServiceResponse.buildReponse("200", "La liste des articles a été récupérés avec succès", articles);
	}
	
	public ServiceResponse<Article> getById(String id) {
		// Essayer de trouver un  article avec le même id que celui en paramètre
		Article foundArticle = daoArticle.findById(id);
		
		// Si on trouve pas
		if (foundArticle == null) {
			return ServiceResponse.buildReponse("702", String.format("Impossible de récupérer un article avec l'id %s", id), foundArticle);
		}
		
		return ServiceResponse.buildReponse("200", "Article récupéré avec succès", foundArticle);
	}
	
	public ServiceResponse<Article> save(Article article) {
		// Essayer de trouver un article existant
		Article foundArticle = daoArticle.findById(article.id);
		
		
		// ======================================================================
		// EDITION
		// ======================================================================
		// Si on trouve Edition
		if (foundArticle != null) {
			
			// Si le titre de l'article qu'on modifie existe deja
			// TODO : MAIS j'exclus l'article modifié de findByTitle
			Article articleTitle = daoArticle.findByTitleAndIdNotEqual(article.title, article.id);
			
			if (articleTitle != null) {
				return ServiceResponse.buildReponse("701", "Impossible de modifier un article avec un titre déjà existant ", null);
			}
			
			// Mettre à jour l'article
			foundArticle.title = article.title;
			
			daoArticle.save(foundArticle);
			
			return ServiceResponse.buildReponse("200", "Article modifié avec succès", foundArticle);
		}
		
		// ======================================================================
		// CREATION
		// ======================================================================
		// Si le titre existe deja alors erreur
		Article articleTitle = daoArticle.findByTitle(article.title);
		
		if (articleTitle != null) {
			return ServiceResponse.buildReponse("701", "Impossible d'ajouter un article avec un titre déjà existant", null);
		}
		
		// Sinon creation
		daoArticle.save(article);
		
		return ServiceResponse.buildReponse("200", "Article ajouté avec succès", article);
	}
	
	public ServiceResponse<Article> deleteById(String id) {
		// Essayer de trouver un  article avec le même id que celui en paramètre
		Article foundArticle = daoArticle.findById(id);
		
		// Si on trouve pas alors on supprime pas
		if (foundArticle == null) {
			return ServiceResponse.buildReponse("702", "Impossible de supprimer un article dont l'id n'existe pas", foundArticle);
		}
		
		// supprimer dans la liste
		daoArticle.deleteById(id);
	
		return ServiceResponse.buildReponse("200", String.format("L'article %s a été supprimé avec succès", id), foundArticle);
	}
	
}
