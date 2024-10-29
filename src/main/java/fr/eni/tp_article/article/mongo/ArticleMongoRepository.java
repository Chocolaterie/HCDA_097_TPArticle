package fr.eni.tp_article.article.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.eni.tp_article.article.Article;

public interface ArticleMongoRepository extends MongoRepository<Article, String> {

}
