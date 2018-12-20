package com.core.interceptor;

import com.core.response.ResponseJSONResult;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义REST类型的返回值处理器
 * 将REST的返回结果进行进一步的封装，如原本返回的是data，那么封装后将会是：
 * json:ResponseJSONResult
 *
 **/
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler proxyObject;
    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate){
        this.proxyObject=delegate;
    }
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        if (returnType.hasMethodAnnotation(ResponseBody.class)
                || (!returnType.getDeclaringClass().equals(ModelAndView.class))
                && returnType.getMethod().getDeclaringClass().isAnnotationPresent(RestController.class)) {
            return true;
        }

        return false;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        if(returnValue instanceof  ResponseJSONResult){
            proxyObject.handleReturnValue(returnValue,returnType,mavContainer,webRequest);
        }else{
            ResponseJSONResult result = ResponseJSONResult.ok(returnValue);
            proxyObject.handleReturnValue(result,returnType,mavContainer,webRequest);
        }
        mavContainer.setRequestHandled(true);
    }

}