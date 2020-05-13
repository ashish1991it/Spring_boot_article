package springboot2.article;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import springboot2.article.Application;
import springboot2.article.model.Article;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, 
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllArticles() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/articles",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetArticleById() {
		Article article = restTemplate.getForObject(getRootUrl() + "/api/articles/1", Article.class);
		System.out.println(article.getBody());
		assertNotNull(article);
	}

	@Test
	public void testCreateArticle() {
		Article article = new Article();
		article.setBody("java");
		article.setDescription("spring boot demo");
		article.setTitle("spring boot");
		ResponseEntity<Article> postResponse = restTemplate.postForEntity(getRootUrl() +
				"/api/articles", article, Article.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}


	/*
	 * @Test public void testDeleteDelete() { int id = 1; Article employee =
	 * restTemplate.getForObject(getRootUrl() + "/api/articles/" + id,
	 * Article.class); assertNotNull(employee);
	 * 
	 * restTemplate.delete(getRootUrl() + "/api/articles/" + id);
	 * 
	 * try { employee = restTemplate.getForObject(getRootUrl() + "/api/articles/" +
	 * id, Article.class); } catch (final HttpClientErrorException e) {
	 * assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND); } }
	 */
}
