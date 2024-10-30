package fr.eni.tp_article.article.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.tp_article.article.Article;

public interface ArticleJpaRepository extends JpaRepository<Article, Long> {

	public Article findFirstByTitle(String title);
	
	/**
	 * $ne : ?0 => n'est pas egal au premier parametre de la fonction
	 * $ne : ?1 => n'est pas egal au deuxieme parametre de la fonction
	 * @param title
	 * @param id
	 * @return
	 */
	//public Article findFirstByTitleAndIdNotEqual(String title, Long id);
}
