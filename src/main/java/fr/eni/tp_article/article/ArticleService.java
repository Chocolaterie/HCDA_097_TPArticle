package fr.eni.tp_article.article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.tp_article.MessageHelper;
import fr.eni.tp_article.ServiceResponse;

@Service
public class ArticleService {
	
	@Autowired
	IDAOArticle daoArticle;

	@Autowired
	MessageHelper messageHelper;
	
	public ServiceResponse<List<Article>> getAll() {
		
		List<Article> articles = daoArticle.findAll();
		
		return ServiceResponse.buildReponse("200", messageHelper.i18n("SERVICE_MSG_RG_001_202_01"), articles);
	}
	
	public ServiceResponse<Article> getById(Long id) {
		// Essayer de trouver un  article avec le même id que celui en paramètre
		Article foundArticle = daoArticle.findById(id);
		
		// Si on trouve pas
		if (foundArticle == null) {
			return ServiceResponse.buildReponse("702", messageHelper.i18n("SERVICE_MSG_RG_002_701_01", id), foundArticle);
		}
		
		return ServiceResponse.buildReponse("200", messageHelper.i18n("SERVICE_MSG_RG_002_202_01"), foundArticle);
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
				return ServiceResponse.buildReponse("701", messageHelper.i18n("SERVICE_MSG_RG_003_701_01"), null);
			}
			
			// Mettre à jour l'article
			foundArticle.title = article.title;
			
			daoArticle.save(foundArticle);
			
			return ServiceResponse.buildReponse("200", messageHelper.i18n("SERVICE_MSG_RG_003_200_01"), foundArticle);
		}
		
		// ======================================================================
		// CREATION
		// ======================================================================
		// Si le titre existe deja alors erreur
		Article articleTitle = daoArticle.findByTitle(article.title);
		
		if (articleTitle != null) {
			return ServiceResponse.buildReponse("701", messageHelper.i18n("SERVICE_MSG_RG_004_701_01"), null);
		}
		
		// Sinon creation
		daoArticle.save(article);
		
		return ServiceResponse.buildReponse("200", messageHelper.i18n("SERVICE_MSG_RG_004_200_01"), article);
	}
	
	public ServiceResponse<Article> deleteById(Long id) {
		// Essayer de trouver un  article avec le même id que celui en paramètre
		Article foundArticle = daoArticle.findById(id);
		
		// Si on trouve pas alors on supprime pas
		if (foundArticle == null) {
			return ServiceResponse.buildReponse("702", messageHelper.i18n("SERVICE_MSG_RG_005_702_01"), foundArticle);
		}
		
		// supprimer dans la liste
		daoArticle.deleteById(id);
	
		return ServiceResponse.buildReponse("200", messageHelper.i18n("SERVICE_MSG_RG_005_200_01", id), foundArticle);
	}
	
}
