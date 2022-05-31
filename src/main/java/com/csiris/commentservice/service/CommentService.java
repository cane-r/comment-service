package com.csiris.commentservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.csiris.commentservice.persistence.Comment;

//@Service
//@Transactional(readOnly = true,isolation = Isolation.REPEATABLE_READ)
public class CommentService {

	public void saveComment(Comment comment) {
	}

}
