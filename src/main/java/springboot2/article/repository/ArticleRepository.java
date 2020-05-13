package springboot2.article.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import springboot2.article.model.Article;
import springboot2.article.model.ArticleTags;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long>{
	

	@Query(value="SELECT a.tags,COUNT(a.tags) as count FROM article_tags a GROUP BY tags",nativeQuery = true)
	
	public List<Map<String,Integer>> getArticlesTags();

}
