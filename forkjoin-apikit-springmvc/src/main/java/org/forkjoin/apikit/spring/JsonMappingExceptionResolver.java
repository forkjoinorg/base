package org.forkjoin.apikit.spring;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.forkjoin.apikit.core.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.Ordered;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

/**
 * @author zuoge85 on 15/4/18.
 */
public class JsonMappingExceptionResolver
		extends
			DefaultHandlerExceptionResolver implements MessageSourceAware {
	private static final Logger log = LoggerFactory
			.getLogger(JsonMappingExceptionResolver.class);

	public JsonMappingExceptionResolver() {
		super();
		setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
	private MessageSourceAccessor messageAccessor;

	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		log.error("处理错误:{},{}", ex.getMessage(), ex.getClass().getName(), ex);
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			if (isJson(handlerMethod)) {
				if (ex instanceof BindException) {
					BindingResult result = ((BindException) ex)
							.getBindingResult();
					ModelAndView modelAndView = new ModelAndView();
					modelAndView.addObject(Result.FIELD_STATUS,
							Result.VALIDATOR);
					StringBuilder sb = new StringBuilder();
					Map<String, String> messageMap = new HashMap<>();
					for (ObjectError error : result.getAllErrors()) {
						String key = (error instanceof FieldError
								? ((FieldError) error).getField()
								: error.getObjectName());
						String message = messageAccessor.getMessage(error);
						messageMap.put(key, message);
						if (sb.length() > 0) {
							sb.append(";");
						}
						sb.append(message);
					}
					String message = messageAccessor.getMessage(
							"server.validator", new Object[]{sb});
					modelAndView.addObject("msgMap", messageMap);
					// modelAndView.addObject("data", messageMap);
					log.info(message);
					return modelAndView;
				} else if (ex instanceof AccountRuntimeException) {
					ModelAndView modelAndView = new ModelAndView();
					modelAndView.addObject(Result.FIELD_STATUS,
							Result.ACCOUNT_ERROR);
					String message = messageAccessor.getMessage("server.error",
							new Object[]{ex.getMessage()});
					modelAndView.addObject("msg", message);
					log.info(message);
					return modelAndView;
				} else {
					ModelAndView modelAndView = new ModelAndView();
					modelAndView.addObject(Result.FIELD_STATUS, Result.ERROR);
					String message = messageAccessor.getMessage("server.error",
							new Object[]{ex.getMessage()});
					modelAndView.addObject("msg", message);
					log.info(message);
					return modelAndView;
				}
			}
		}
		return noJsonResolveException(request, response, handler, ex);
	}

	protected ModelAndView noJsonResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		return super.doResolveException(request, response, handler, ex);
	}

	protected boolean isJson(HandlerMethod handlerMethod) {
		RequestMapping methodAnnotation = handlerMethod
				.getMethodAnnotation(RequestMapping.class);
		return isJson(methodAnnotation)
				|| isJson(handlerMethod.getBeanType().getAnnotation(
						RequestMapping.class));
	}

	protected boolean isJson(RequestMapping methodAnnotation) {
		String[] produces = methodAnnotation.produces();
		for (String produce : produces) {
			if (produce.contains("json")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageAccessor = new MessageSourceAccessor(messageSource);
	}
}
