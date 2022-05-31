package com.csiris.commentservice.persistence.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.csiris.commentservice.persistence.Comment;

@Component
public class CommentAuditListener {

	private final Logger logger;

	public CommentAuditListener(Logger logger) {
		this.logger = logger;
	}

	@PrePersist
	@PreUpdate
	@PreRemove
	private void beforeAnyUpdate(Comment comment) {
		if (comment.getId() == 0) {
			logger.info(String.format("Comment %s is about to be added", comment));
		} else {
			logger.info(String.format("Comment %s is about to be updated/deleted", comment));
		}
	}

	@PostPersist
	@PostUpdate
	@PostRemove
	private void afterAnyUpdate(Comment comment) {
		logger.info(String.format("Comment %s has been updated/deleted", comment));
	}

	@PostLoad
	private void afterLoad(Comment comment) {
		logger.info(String.format("Comment %s has been loaded from the DB", comment));

	}
}
