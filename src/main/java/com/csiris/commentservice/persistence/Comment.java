package com.csiris.commentservice.persistence;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.csiris.commentservice.persistence.listener.CommentAuditListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@EntityListeners(CommentAuditListener.class)
// java11 doesn't have records and I don't like Lombok ..
public class Comment  {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@JsonIgnore
	private Long id;

	@NotNull(message = "Content is missing")
	private String content;

	@NotNull(message = "Associated postId is required")
	private Long postId;

	@Enumerated(EnumType.STRING)
	private Status status = Status.IN_MODERATION;

	public enum Status {

		APPROVED("APPROVED"),IN_MODERATION("IN_MODERATION"),REJECTED("REJECTED");

	    private String status;

	    Status(String status){
	        this.status=status;
	    }

	    //@JsonValue
	    public String getStatus() {
	        return status;
	    }
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Comment(String content,
			 Long postId) {
		super();
		this.content = content;
		this.postId = postId;
	}

	public Comment() {
		super();
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Comment that = (Comment) obj;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
