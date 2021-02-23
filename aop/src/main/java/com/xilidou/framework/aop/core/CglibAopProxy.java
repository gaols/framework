package com.xilidou.framework.aop.core;

import com.xilidou.framework.aop.advisor.AdvisedSupport;
import com.xilidou.framework.ioc.utils.ClassUtils;
import lombok.Data;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

public class CglibAopProxy implements AopProxy {

    private AdvisedSupport advised;

    private Object[] constructorArgs;

    private Class<?>[] constructorArgTypes;

    public CglibAopProxy(AdvisedSupport config) {
        this.advised = config;
    }


    @Override
    public Object getProxy() {
        return getProxy(null);
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {

        Class<?> rootClass = advised.getTargetSource().getTargetClass();

        if (classLoader == null) {
            classLoader = ClassUtils.getDefultClassLoader();
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(rootClass.getSuperclass());
        //增加拦截器的核心方法
        Callback callbacks = getCallBack(advised);
        enhancer.setCallback(callbacks);
        enhancer.setClassLoader(classLoader);
        if (constructorArgs != null && constructorArgs.length > 0) {
            return enhancer.create(constructorArgTypes, constructorArgs);
        }

        return enhancer.create();
    }

    private Callback getCallBack(AdvisedSupport advised) {
        return new DynamicAdvisedInterceptor(advised.getList(), advised.getTargetSource());
    }

    public AdvisedSupport getAdvised() {
        return advised;
    }

    public void setAdvised(AdvisedSupport advised) {
        this.advised = advised;
    }

    public Object[] getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(Object[] constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public Class<?>[] getConstructorArgTypes() {
        return constructorArgTypes;
    }

    public void setConstructorArgTypes(Class<?>[] constructorArgTypes) {
        this.constructorArgTypes = constructorArgTypes;
    }
}
