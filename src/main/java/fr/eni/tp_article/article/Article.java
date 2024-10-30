package fr.eni.tp_article.article;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	public String title;
	
	public Article() {
		
	}
	
	public Article(Long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
}
