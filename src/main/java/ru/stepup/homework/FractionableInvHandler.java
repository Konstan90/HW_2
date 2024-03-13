package ru.stepup.homework;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FractionableInvHandler implements InvocationHandler {
    private Object obj;
    private boolean cached = false;
    private double cachedValue;

    public FractionableInvHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = obj.getClass().getMethod(method.getName(), method.getParameterTypes());
        if(m.getAnnotation(Mutator.class) != null) cached=false;

        if(m.getAnnotation(Cache.class) != null && !cached) {
            cached = true;
            return cachedValue = (Double)method.invoke(obj, args);
        }
        else if(m.getAnnotation(Cache.class) != null && cached){
            return cachedValue;
        }
        return method.invoke(obj, args);
    }
}
