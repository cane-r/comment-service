package com.csiris.commentservice.persistence.storage;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import com.csiris.commentservice.persistence.Comment;

@CrossOrigin()
@RepositoryRestResource(collectionResourceRel = "comments", path = "comments")
public interface CommentStorage extends PagingAndSortingRepository<Comment, Long> {

	@RestResource(path = "commentsByPostId", rel = "commentsByPostId")
	@Query("select c from Comment c where c.postId = :postId and c.status = 'APPROVED'")
	List<Comment> findByPostIdAndStatusIsApproved(@PathVariable Long postId);

	@RestResource(path = "count", rel = "commentCountByPostId")
	@Query("select count(c) from Comment c where c.postId = :postId and c.status = 'APPROVED'")
	Long commentCountByPostId(@PathVariable Long postId);

	@Override
	@RestResource(exported = false)
	void deleteAll();

	@Override
	@RestResource(exported = false)
	void deleteAllById(Iterable<? extends Long> ids);

	@Override
	@RestResource(exported = false)
	void deleteAll(Iterable<? extends Comment> entities);
}