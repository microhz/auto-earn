package com.auto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;

/**
 * @author : jihai
 * @date : 2021/1/14
 * @description : system edge log record
 * record dubbo api call log
 * restful api call log
 * default open on dev or test envir
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Profile({"test", "default"})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LogCongfiguration {

    @PostConstruct
    public void init() {
        LoggerFactory.getLogger(Contants.TEST_LOG).info("当前环境打开rest拦截.");
    }

    @Bean
    public LogAspect controllerAop() {
        return new LogAspect();
    }

    @Bean
    public Monitor monitor() {
        return new Monitor();
    }
}

interface Contants {
    String TEST_LOG = "test-log";
}

class MethodSupport {

    private ObjectMapper objectMapper = new ObjectMapper();

    protected Object printMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            MethodRecord methodRecord = getMethodCall(proceedingJoinPoint);
            logOut(methodRecord);
            return methodRecord.getRet();
        } catch (Throwable throwable) {
            logOut("日志切面异常");
            logOut(throwable.toString());
            return proceedingJoinPoint.proceed();
        }
    }

    protected void logOut(MethodRecord callString) {
        logOut(callString.getCallString() + ",cost:" + callString.getCost() + "ms");
    }

    protected void logOut(String log) {
        Logger logger = LoggerFactory.getLogger(Contants.TEST_LOG);
        logger.info(log);
    }

    private MethodRecord getMethodCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        StringBuffer retStr = new StringBuffer(signature.getMethod().getDeclaringClass().getSimpleName()).append(".")
                .append(signature.getName());

        Object[] args = proceedingJoinPoint.getArgs();
        StringBuffer params = new StringBuffer();
        for (Object arg : args) {
            params.append(objectMapper.writeValueAsString(arg)).append(",");
        }
        if (params.length() > 0) params.delete(params.length() - 1, params.length());
        retStr.append(",").append("params:").append(params);
        long start = System.currentTimeMillis();
        Object ret = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        retStr.append(";ret:").append(objectMapper.writeValueAsString(ret));
        return new MethodRecord(retStr.toString(), (end - start), ret);
    }

    static class MethodRecord {
        private String callString;
        private long cost;
        private Object ret;

        public MethodRecord(String callString, long cost, Object ret) {
            this.callString = callString;
            this.cost = cost;
            this.ret = ret;
        }

        public String getCallString() {
            return callString;
        }

        public Object getRet() {
            return ret;
        }

        public void setRet(Object ret) {
            this.ret = ret;
        }

        public void setCallString(String callString) {
            this.callString = callString;
        }

        public long getCost() {
            return cost;
        }

        public void setCost(long cost) {
            this.cost = cost;
        }
    }
}

@Aspect
class LogAspect extends MethodSupport {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void point() {
    }


    @Around("point()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 打印切面
        return super.printMethod(proceedingJoinPoint);
    }
}

class Monitor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("load bean : " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}



