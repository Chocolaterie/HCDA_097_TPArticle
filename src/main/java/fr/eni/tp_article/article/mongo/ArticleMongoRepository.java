package fr.eni.tp_article.article.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import fr.eni.tp_article.article.Article;

public interface ArticleMongoRepository extends MongoRepository<Article, String> {

	public Article findFirstByTitle(String title);
	
	/**
	 * $ne : ?0 => n'est pas egal au premier parametre de la fonction
	 * $ne : ?1 => n'est pas egal au deuxieme parametre de la fonction
	 * @param title
	 * @param id
	 * @return
	 */
	@Query("{ 'id' : { $ne : ?1} }")
	public Article findFirstByTitleAndIdNotEqual(String title, String id);
}
