package com.auto;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : jihai
 * @date : 2021/1/22
 * @description :  记得配置dubbo spi 文件夹
 */
@Activate(group = {Constants.CONSUMER, Constants.PROVIDER})
@Profile(value = {"test", "dev"})
@Component
public class LogFilter extends MethodSupport implements Filter {

    private volatile static boolean isOpen = false;

    public static void setOpen(boolean open) {
        isOpen = open;
    }

    @PostConstruct
    public void init() {
        logOut("当前环境开启dubbo日志拦截");
        isOpen = true;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (isIgnoreClass(invoker.getInterface()) || !isOpen) return invoker.invoke(invocation);
        long start = System.currentTimeMillis();
        Result ret = invoker.invoke(invocation);
        long end = System.currentTimeMillis();

        Object[] arguments = invocation.getArguments();
        StringBuilder sb = new StringBuilder();
        for (Object arg : arguments) {
            sb.append(JSONObject.toJSONString(arg)).append(",");
        }

        if (sb.length() > 0) sb.delete(sb.length() - 1, sb.length());
        String methodName = invoker.getInterface().getSimpleName() + "." + invocation.getMethodName();
        String call = methodName + ",param : " + sb.toString() + ",ret : " + JSONObject.toJSONString(ret.getValue());
        MethodRecord methodRecord = new MethodRecord(call , end - start, ret);
        logOut(methodRecord);
        return ret;
    }

    protected boolean isIgnoreClass(Class<?> anInterface) {
        if (anInterface.getPackage().getName().contains("com.ggj.platform.sentry")) return true;
//        return isInterface(anInterface, SentryApi.class)
//                || isInterface(anInterface, MonitorService.class);
        return false;
    }

    private boolean isInterface(Class<?> anInterface, Class<?> inter) {
        if (anInterface.isAssignableFrom(inter)) return true;

        for (Class<?> clazz : anInterface.getInterfaces()) {
            if (clazz.isAssignableFrom(inter)) return true;
        }
        return false;
    }
}
