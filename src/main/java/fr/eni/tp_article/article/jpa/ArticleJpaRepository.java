package fr.eni.tp_article.article.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.tp_article.article.Article;

public interface ArticleJpaRepository extends JpaRepository<Article, Long> {

	public Article findFirstByTitle(String title);
	
	public Article findFirstByTitleAndIdNot(String title, Long id);
}
