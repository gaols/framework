package com.xilidou.framework.aop.advisor;

import com.xilidou.framework.aop.interceptor.AopMethodInterceptor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zhengxin
 */

public class AdvisedSupport extends Advisor {

    private TargetSource targetSource;

    private List<AopMethodInterceptor> list = new LinkedList<>();

    public void addAopMethodInterceptor(AopMethodInterceptor interceptor) {
        list.add(interceptor);
    }

    public void addAopMethodInterceptors(List<AopMethodInterceptor> interceptors) {
        list.addAll(interceptors);
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public List<AopMethodInterceptor> getList() {
        return list;
    }

    public void setList(List<AopMethodInterceptor> list) {
        this.list = list;
    }
}
