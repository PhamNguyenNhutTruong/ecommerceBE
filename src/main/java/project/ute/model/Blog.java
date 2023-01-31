package project.ute.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name="Blog")
public class Blog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String content;

	@Column(name="create_at")
	private Timestamp createAt;

	private String name;

	private String title;

	//bi-directional many-to-one association to BlogType
	@ManyToOne
	@JoinColumn(name="blog_type")
	private BlogType blogTypeBean;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="create_by")
	private User user;

	public Blog() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BlogType getBlogTypeBean() {
		return this.blogTypeBean;
	}

	public void setBlogTypeBean(BlogType blogTypeBean) {
		this.blogTypeBean = blogTypeBean;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}