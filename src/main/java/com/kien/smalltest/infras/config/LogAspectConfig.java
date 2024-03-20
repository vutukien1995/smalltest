package com.kien.smalltest.infras.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class LogAspectConfig {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerPointCut(){
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before("restControllerPointCut()")
    public void getLogRestBeforeExecute(JoinPoint joinPoint){
        StringBuilder sb = new StringBuilder();
        sb.append(getApiInfo(joinPoint));
        log.info(sb.toString());
    }

    @AfterReturning(pointcut = "restControllerPointCut()" , returning = "returnVal")
    public void getLogRestAfterExecute(JoinPoint joinPoint, Object returnVal){
        StringBuilder sb = new StringBuilder();
        sb.append("Status : Success , ");
        sb.append(getApiInfo(joinPoint));
        sb.append(" response : " + convertObjToJson(returnVal));
        log.info(sb.toString());
    }

    @AfterThrowing(value="restControllerPointCut()",throwing="ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        StringBuilder sb = new StringBuilder();
        log.info("exception:", ex.getMessage());
        sb.append("Status : Fail , ");
        sb.append(getApiInfo(joinPoint));
        log.info(sb.toString());
    }


    private String getApiInfo(JoinPoint joinPoint){
        StringBuilder sb = new StringBuilder();
        sb.append("Api info : ");

        StringBuilder value = new StringBuilder();
        RequestMapping classMapping = joinPoint.getTarget().getClass().getAnnotation(RequestMapping.class);
        if(classMapping != null && classMapping.value() != null && classMapping.value().length > 0){
            value.append(classMapping.value()[0]);
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if(requestMapping != null && requestMapping.value() != null && requestMapping.value().length > 0){
            value.append(requestMapping.value()[0]);
            sb.append(" method : " + requestMapping.method()[0].name() + " , ");
            sb.append(" value : " + value.toString() + " , ");
        }
        for(Object el : joinPoint.getArgs()){
            sb.append("request : " + convertObjToJson(el) + " , ");
        }
        return sb.toString();
    }

    private String convertObjToJson(Object val){
        try {
            return objectMapper.writeValueAsString(val);
        } catch (JsonProcessingException e) {
            return null;
        }
    }



}
