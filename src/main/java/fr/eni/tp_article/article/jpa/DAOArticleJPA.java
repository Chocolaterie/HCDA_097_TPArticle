package fr.eni.tp_article.article.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.tp_article.article.Article;
import fr.eni.tp_article.article.IDAOArticle;

@Component
public class DAOArticleJPA implements IDAOArticle {

	@Autowired 
	ArticleJpaRepository articleJpaRepository;
	
	@Override
	public List<Article> findAll() {
		return articleJpaRepository.findAll();
	}

	@Override
	public Article findById(Long id) {
		if (id == null) {
			return null;
		}
		return articleJpaRepository.findById(id).orElse(null);
	}

	@Override
	public Article findByTitle(String title) {
		return articleJpaRepository.findFirstByTitle(title);
	}
	
	@Override
	public Article findByTitleAndIdNotEqual(String title, Long id) {
		return articleJpaRepository.findFirstByTitleAndIdNot(title, id);
	}
	
	@Override
	public Article save(Article article) {
		
		articleJpaRepository.save(article);
		
		return article;
	}

	@Override
	public Article deleteById(Long id) {
		
		// Find article
		Article foundArticle = findById(id);
		
		articleJpaRepository.deleteById(id);
		
		return foundArticle;
	}


}
