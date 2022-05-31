package com.csiris.commentservice.configuration;

import static java.util.Optional.ofNullable;

import java.lang.reflect.Field;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.csiris.commentservice.persistence.Comment;
import com.csiris.commentservice.persistence.Comment.Status;
import com.csiris.commentservice.persistence.storage.CommentStorage;

@Configuration
public class BeanConfig {

	@Bean
	@Scope("prototype")
	public Logger logger(final InjectionPoint ip) {
		return LoggerFactory.getLogger(ofNullable(ip.getMethodParameter())
				.<Class<?>>map(MethodParameter::getContainingClass).orElseGet(() -> ofNullable(ip.getField())
						.map(Field::getDeclaringClass).orElseThrow(IllegalArgumentException::new)));
	}

//	@Bean
//	public ApplicationRunner initializeWithService(CommentService service, Logger logger) {
//		return new ApplicationRunner() {
//			@Override
//			public void run(ApplicationArguments args) throws Exception {
//				logger.info(String.format("Contains java runtime arg named \"test\" ? : %s , value : %s",
//						args.containsOption("test"), args.getOptionValues("test")));
//				service.saveComment(new Comment("Comment-1", 1L));
//				service.saveComment(new Comment("Comment-2", 2L));
//				logger.info("Started..");
//			}
//		};
//	}

	@Bean
	public ApplicationRunner initializeWithDataRestRepository(CommentStorage repository, Logger logger) {
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				logger.info(String.format("Contains java runtime arg named \"test\" ? : %s , value : %s",
						args.containsOption("test"), args.getOptionValues("test")));

				var entity = new Comment("Comment-1", 2L);
				entity.setStatus(Status.APPROVED);
				repository.save(entity);
				var entity2 = new Comment("Comment-2", 2L);
				entity2.setStatus(Status.APPROVED);
				repository.save(entity2);
				var entity3 = new Comment("Comment-1", 1L);
				entity3.setStatus(Status.APPROVED);
				repository.save(entity3);

			}
		};
	}

	@Component
	public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

		@Override
		public void onApplicationEvent(ContextRefreshedEvent event) {
			ApplicationContext applicationContext = event.getApplicationContext();
			// get any bean here..
		}
	}

}
