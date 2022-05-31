package com.csiris.commentservice.persistence.listener;

import org.slf4j.Logger;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.csiris.commentservice.persistence.Comment;

@RepositoryEventHandler(Comment.class)
public class DataRestEventHandler {
    private final Logger logger;

    public DataRestEventHandler(Logger logger) {
		super();
		this.logger = logger;
	}

	@HandleBeforeCreate
    public void beforeCreateHook(Comment comment){
        logger.info("beforeCreateHook...");
    }

    @HandleAfterCreate
    public void aterCreateHook(Comment comment){
        logger.info("aterCreateHook ....");
    }

    @HandleBeforeDelete
    public void beforeDeleteHook(Comment comment){
        logger.info("beforeDeleteHook ....");
    }

    @HandleAfterDelete
    public void afterDeleteHook(Comment comment){
        logger.info("afterDeleteHook ....");
    }
}