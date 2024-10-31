package fr.eni.tp_article.article.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import fr.eni.tp_article.article.Article;
import fr.eni.tp_article.article.IDAOArticle;

@Component
public class DAOArticleMock implements IDAOArticle {

	List<Article> article = new ArrayList<Article>();
	
	public DAOArticleMock() {
		
		article.add(new Article(1L, "Teletubies"));
		article.add(new Article(2L, "Sharknado"));
		article.add(new Article(3L, "Incroyable Bulk"));
	}


	@Override
	public List<Article> findAll() {
		return article;
	}
	
	@Override
	public Article findById(Long id) {
		return article.stream().filter(movie -> movie.id == id).findFirst().orElse(null);
	}

	@Override
	public Article findByTitle(String title) {
		return article.stream().filter(movie -> movie.title.equals(title)).findFirst().orElse(null);
	}

	@Override
	public Article save(Article movie) {
		Article foundMovie = article.stream().filter(value -> value.id == movie.id).findFirst().orElse(null);
		if (foundMovie != null) {
			foundMovie.title = movie.title;
			
			
			return foundMovie;
		}
		
		article.add(movie);
		
		return movie;
		
	}


	@Override
	public Article findByTitleAndIdNotEqual(String title, Long id) {
		return null;
	}


	@Override
	public Article deleteById(Long id) {
		return null;
	}
}
