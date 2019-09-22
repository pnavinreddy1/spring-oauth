package com.articles.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import javax.ws.rs.container.ResourceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.articles.resource.ArticlesBaseResource;
import com.articles.resource.RootResource;

@RunWith(SpringJUnit4ClassRunner.class)
public class RootResourceTest {

	@Mock
	ResourceContext mockContext;
	
	@Mock 
	ArticlesBaseResource mockResource;
	
	@InjectMocks
	RootResource testSubject;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testArticleBaseResource() {
		when(mockContext.getResource(ArticlesBaseResource.class)).thenReturn(mockResource);
		ArticlesBaseResource actualResult = testSubject.getArticlesBaseResource();
		assertThat(actualResult, is(ArticlesBaseResource.class));
	}

}
