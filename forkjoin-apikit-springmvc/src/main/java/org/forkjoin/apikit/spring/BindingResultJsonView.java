package org.forkjoin.apikit.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zuoge85 on 15/4/19.
 */
public class BindingResultJsonView extends MappingJackson2JsonView{
    public BindingResultJsonView() {

    }

    @Override
    protected Object filterModel(Map<String, Object> model) {
        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) super.filterModel(model);
        Map<String, Object> errors = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            if (entry.getValue() instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult)entry.getValue();
                addBindingErrors(errors, bindingResult);
            }
        }

        if (errors.isEmpty()) {
            if (!result.containsKey("success")) {
                result.put("success",true);
            }
        }
        else {
            result.clear();
            result.put("errors",errors);
            result.put("success",false);
        }
        return result;
    }

    protected void addBindingErrors(Map<String, Object> errors, BindingResult bindingResult) {
        for (ObjectError error : bindingResult.getAllErrors()) {
            String key = (error instanceof FieldError ? ((FieldError)error).getField() : error.getObjectName());
            errors.put(key,super.getMessageSourceAccessor().getMessage(error.getCode(),error.getArguments()));
        }
    }


}
