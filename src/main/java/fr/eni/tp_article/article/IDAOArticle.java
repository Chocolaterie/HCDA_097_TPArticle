package fr.eni.tp_article.article;

import java.util.List;

public interface IDAOArticle {

	public List<Article> findAll();
	
	public Article findById(Long id);
	
	public Article findByTitle(String title);
	
	public Article findByTitleAndIdNotEqual(String title, Long id);
	
	public Article save(Article article);
	
	public Article deleteById(Long id);
}
