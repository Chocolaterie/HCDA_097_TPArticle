package fr.eni.tp_article.article.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.tp_article.article.Article;
import fr.eni.tp_article.article.IDAOArticle;

@Component
public class DAOArticleMongo implements IDAOArticle {

	@Autowired 
	ArticleMongoRepository articleMongoRepository;
	
	@Override
	public List<Article> findAll() {
		return articleMongoRepository.findAll();
	}

	@Override
	public Article findById(String id) {
		if (id == null) {
			return null;
		}
		return articleMongoRepository.findById(id).orElse(null);
	}

	@Override
	public Article findByTitle(String title) {
		return articleMongoRepository.findFirstByTitle(title);
	}
	
	@Override
	public Article findByTitleAndIdNotEqual(String title, String id) {
		return articleMongoRepository.findFirstByTitleAndIdNotEqual(title, id);
	}
	
	@Override
	public Article save(Article article) {
		
		articleMongoRepository.save(article);
		
		return article;
	}

	@Override
	public Article deleteById(String id) {
		
		// Find article
		Article foundArticle = findById(id);
		
		articleMongoRepository.deleteById(id);
		
		return foundArticle;
	}


}
