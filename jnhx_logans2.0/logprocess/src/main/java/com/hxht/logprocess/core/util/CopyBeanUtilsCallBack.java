package com.hxht.logprocess.core.util;

@FunctionalInterface
public interface CopyBeanUtilsCallBack<S, T> {

    void callBack(S t, T s);
}
