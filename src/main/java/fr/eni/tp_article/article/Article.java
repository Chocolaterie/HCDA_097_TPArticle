package fr.eni.tp_article.article;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
public class Article {
	
	
	@Id
	public String id;
	
	public String title;
	
	public Article() {
		
	}
	
	public Article(String id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
}
