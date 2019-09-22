package com.articles.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.articles.domain.Article;

public class ArticleDataServiceTest {
	private ArticleDataService testSubject = new ArticleDataService();
	private Map<String, Article> mapOfArticles = new ConcurrentHashMap<>();
	private Article article = new Article();
	@Before
	public void setUp() throws Exception {
		
		article.setContent("content");
		article.setTitle("title");
		article.setDocId("1");
		
	}

	@Test
	public void testFindArticleById() {
		mapOfArticles.put(article.getTitle(), article);
		ReflectionTestUtils.setField(testSubject, "mapOfArticles", mapOfArticles);
		
		Article result= testSubject.findAtricleByTitle("title");
		assertThat(result.getContent(), is("content"));
		assertThat(result.getDocId(), is("1"));
	}

	@Test
	public void testNotFoundArticleById() {
		mapOfArticles.put(article.getTitle(), article);
		ReflectionTestUtils.setField(testSubject, "mapOfArticles", mapOfArticles);
		
		Article result= testSubject.findAtricleByTitle("invalidtitle");
		assertThat(result.getContent(), is(nullValue()));
	}
	
	@Test
	public void testSaveArticle() {
		Article article = new Article();
		article.setContent("content");
		article.setTitle("title");
		
		Article result= testSubject.saveArticle(article);
		assertThat(result.getContent(), is("content"));
		assertThat(result.getTitle(), is("title"));
		assertThat(result.getDocId(), is(notNullValue()));
	}

	@Test
	public void testUpdateArticle() {
		article.setTitle("new Title");
		mapOfArticles.put(article.getTitle(),article);
		ReflectionTestUtils.setField(testSubject, "mapOfArticles", mapOfArticles);
		
		Article result= testSubject.updateAtricle(article);
		assertThat(result.getContent(), is("content"));
		assertThat(result.getTitle(), is("new Title"));
		assertThat(result.getDocId(), is("1"));
	}

}
