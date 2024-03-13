package ru.stepup.homework;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FractionableInvHandler implements InvocationHandler {
    private Object obj;
    private boolean cached = false;
    

    public FractionableInvHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = obj.getClass().getMethod(method.getName(), method.getParameterTypes());

        if(!cached) {
            System.out.println("invoke double value");
            cached = true;
        }
        if(m.getAnnotation(Mutator.class) != null) cached=false;
        return method.invoke(obj, args);
    }
}
