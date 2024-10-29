package fr.eni.tp_article.article;

import java.util.List;

public interface IDAOArticle {

	public List<Article> findAll();
	
	public Article findById(String id);
	
	public Article save(Article article);
	
	public Article deleteById(String id);
}
