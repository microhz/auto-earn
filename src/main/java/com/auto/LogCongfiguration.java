package com.auto;

import com.alibaba.dubbo.rpc.Invoker;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

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
@Profile({"test", "dev", "default"})
public class LogCongfiguration {

    public static void main(String[] args) {
        String a = "a";
        System.out.println(a.getClass().getDeclaringClass().isAssignableFrom(String.class));
    }

    @PostConstruct
    public void init() {
        LoggerFactory.getLogger(Contants.TEST_LOG).info("init test log.");
    }

    @Bean
    public DubboConsumerAspect dubboConsumerAspect() {
        return new DubboConsumerAspect();
    }

    @Bean
    public ControllerAop controllerAop() {
        return new ControllerAop();
    }

}

interface Contants {
    String TEST_LOG = "test-log";
}

class MethodSupport {

    private ObjectMapper objectMapper = new ObjectMapper();

    protected void printMethod(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            MethodRecord methodRecord = getMethodCall(proceedingJoinPoint);
            logOut(methodRecord);
        } catch (Throwable throwable) {
            logOut("日志切面异常");
            logOut(throwable.toString());
        }
    }

    protected void printInvacationMethod(MethodInvocation methodInvocation, long cost, Object ret) {
        Method method = methodInvocation.getMethod();
        String methodName = method.getDeclaringClass() + "." + method.getName();
        StringBuffer params = new StringBuffer();
        if (methodInvocation.getArguments().length > 0) {
            for (Object p : methodInvocation.getArguments()) {
                params.append(p).append(",");
            }
            if (params.length() > 0) params.delete(params.length() - 1, params.length());
        }
        logOut(methodName + "#" + params + ";ret:" + ret);
    }

    private void logOut(MethodRecord callString) {
        logOut(callString.getCallString() + ",cost:" + callString.getCost() + "ms");
    }

    private void logOut(String log) {
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
        return new MethodRecord(retStr.toString(), (end - start));
    }

    static class MethodRecord {
        private String callString;
        private long cost;

        public MethodRecord(String callString, long cost) {
            this.callString = callString;
            this.cost = cost;
        }

        public String getCallString() {
            return callString;
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

class DubboConsumerAspect extends StaticMethodMatcherPointcutAdvisor {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        setAdvice(new LogApiAdvice());
        return targetClass.isAssignableFrom(Invoker.class);
    }
}


/**
 * controller aop
 */
class ControllerAop extends StaticMethodMatcherPointcutAdvisor {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        setAdvice(new LogApiAdvice());
        return method.getDeclaringClass().isAnnotationPresent(Controller.class) || method.getDeclaringClass().isAnnotationPresent(RestController.class);
    }

}

/**
 * log advice
 */
class LogApiAdvice extends MethodSupport implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        Object ret = invocation.proceed();
        long end = System.currentTimeMillis();
        super.printInvacationMethod(invocation, end - start, ret);
        return ret;
    }

}


