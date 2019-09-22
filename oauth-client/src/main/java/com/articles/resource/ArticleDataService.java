package com.articles.resource;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.articles.domain.Article;

@Service
public class ArticleDataService {
	private Map<String, Article> mapOfArticles = new ConcurrentHashMap<>();

	public Article saveArticle(Article article)  {
		Supplier<String> uuidSupplier = () -> UUID.randomUUID().toString();
		article.setDocId(uuidSupplier.get());
		mapOfArticles.put(article.getTitle(), article);
		return article;
	}

	public List<Article> getAtricles() {
		return mapOfArticles.values().stream().collect(Collectors.toList());
	}

	public Article findAtricleByTitle(String title) {
		return mapOfArticles.getOrDefault(title, new Article());
	}

	public Article updateAtricle(Article inputArticle) {
		String title = inputArticle.getTitle();
		mapOfArticles.computeIfPresent(title, (k,v)->inputArticle);
		
		return mapOfArticles.getOrDefault(title, new Article());
	}

	public boolean deleteAtricleByTitle(String title) {
		if(mapOfArticles.containsKey(title)) {
			mapOfArticles.remove(title);
			return true;
		}
		return false;
	}
	
}
