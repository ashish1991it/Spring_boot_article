package springboot2.article.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springboot2.article.exception.ResourceNotFoundException;
import springboot2.article.model.Article;
import springboot2.article.repository.ArticleRepository;

@RestController
@RequestMapping("/api")
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;

	@GetMapping("/articles")
	public ResponseEntity<Page<Article>> getAllarticles(Pageable pageable ) {
		try {
			Page<Article> articles = articleRepository.findAll(pageable);
			int totalPages = articles.getTotalPages();
			 if(totalPages>=0)
			 {
				 return new ResponseEntity<>(articles, HttpStatus.OK);
			 }
			 else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/articles/{slug-uid}")
	public ResponseEntity<Article> getarticleById(@PathVariable(value = "slug-uid") Long slug_uid)
			throws ResourceNotFoundException {
		Article article = articleRepository.findById(slug_uid)
				.orElseThrow(() -> new ResourceNotFoundException("article not found for this id :: " + slug_uid));
		return ResponseEntity.ok().body(article);
	}

	@PostMapping("/articles")
	public ResponseEntity<Article> createArticle(@RequestBody Article article) {
		try {
			Article _article = articleRepository.save(article);
			return new ResponseEntity<>(_article, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/articles/{slug-uid}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("slug-uid") long id) {
		try {
			articleRepository.deleteById(id);
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PatchMapping("/articles/{slug-uid}")
	public ResponseEntity<Article> updateTutorial(@PathVariable("slug-uid") long id, @RequestBody Article article) {
		Optional<Article> article1 = articleRepository.findById(id);

		if (article1.isPresent()) {
			Article _article = article1.get();
			_article.setTitle(article.getTitle());
			_article.setDescription(article.getDescription());
			_article.setSlug(article.getSlug());
			return new ResponseEntity<>(articleRepository.save(_article), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@GetMapping("/articles/tags")
	public @ResponseBody ResponseEntity<List<Map<String, Integer>>> getArticlesTags() {
		try {
			List<Map<String, Integer>> articlesTags = articleRepository.getArticlesTags();
			return new ResponseEntity<List<Map<String, Integer>>>(articlesTags, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}
