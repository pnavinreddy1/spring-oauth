package com.articles.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.articles.domain.Article;
import com.articles.resource.ArticleDataService;
import com.articles.resource.ArticlesBaseResource;

@RunWith(SpringJUnit4ClassRunner.class)
public class ArticlesBaseResourceTest {
	private List<Article> articles = new ArrayList<>();
	private Article article = new Article();
	@Mock
	private ArticleDataService mockService;
	
	@InjectMocks
	ArticlesBaseResource testSubject;
	
	@Before
	public void setUp() throws Exception {
		article.setContent("content");
		article.setTitle("title");
		article.setDocId("1");
	}

	@Test
	public void testNoArticles() {
		when(mockService.getAtricles()).thenReturn(articles);
		Response actualResponse = testSubject.getAllArticles();
		assertThat(actualResponse.getStatusInfo(), is(Status.NOT_FOUND));
		assertThat(actualResponse.getEntity(), is(notNullValue()));
		
	}
	
	@Test
	public void testGetAllArticles() {
		articles.add(article);
		when(mockService.getAtricles()).thenReturn(articles);
		Response actualResponse = testSubject.getAllArticles();
		assertThat(actualResponse.getStatusInfo(), is(Status.OK));
		assertThat(actualResponse.getEntity(), is(notNullValue()));
		
	}
	
	@Test
	public void testNotDeletedArticle() {
		articles.add(article);
		when(mockService.deleteAtricleByTitle(Mockito.anyString())).thenReturn(false);
		Response actualResponse = testSubject.deleteArticleByTitle("1");
		assertThat(actualResponse.getStatusInfo(), is(Status.BAD_REQUEST));
		assertThat(actualResponse.getEntity(), is(notNullValue()));
		
	}
	
	@Test
	public void testDeletedArticle() {
		articles.add(article);
		when(mockService.deleteAtricleByTitle(Mockito.anyString())).thenReturn(true);
		Response actualResponse = testSubject.deleteArticleByTitle("1");
		assertThat(actualResponse.getStatusInfo(), is(Status.OK));
		assertThat(actualResponse.getEntity(), is(notNullValue()));
		
	}

}
