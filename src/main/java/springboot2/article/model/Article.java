package springboot2.article.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "articles")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String slug;
	
	@NonNull
	@NotBlank(message = "title can not be blank")
	private String title;
	@NonNull
	@NotBlank(message = "description can not be blank")
	private String description;
	@NonNull
	@NotBlank(message = "body can not be blank")
	private String body;
	
	@ElementCollection
	private List<String> tags;

	@Temporal(TemporalType.TIMESTAMP)
    public java.util.Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    public java.util.Date updateAt;
	
	private boolean favorited;
	private int favoritesCount;

	
	
	private int count;
	
	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public java.util.Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(java.util.Date createdAt) {
		this.createdAt = createdAt;
	}

	public java.util.Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(java.util.Date updateAt) {
		this.updateAt = updateAt;
	}

	public boolean isFavorited() {
		return favorited;
	}

	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}

	public int getFavoritesCount() {
		return favoritesCount;
	}

	public void setFavoritesCount(int favoritesCount) {
		this.favoritesCount = favoritesCount;
	}
	
	

}
