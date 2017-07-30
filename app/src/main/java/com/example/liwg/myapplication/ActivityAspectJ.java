package com.example.liwg.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by liwg on 2017/7/30.
 */
@Aspect
public class ActivityAspectJ {
    //进行类似于正则表达式的匹配被匹配到的方法都会被截获
    //    http://blog.csdn.net/kjfcpua/article/details/7513273
    ////截获任何包中以类名以Activity结尾并且该目标类和当前类是一个Object的对象的所有方法
    private static final String POINTCUT_METHOD = "execution(* *..Activity+.*(..)";

    @Before("execution(* android.app.Activity.on**(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.d("ActivityAspectJ", "onActivityMethodBefore: " + key);
    }

    @After("execution(* com.example.liwg.myapplication..*Activity.on**(..))")
    public void onActivityMethodAfter(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.e("ActivityAspectJ", "onActivityMethodAfter: " + key);
    }

    @Around("execution(@com.example.liwg.myapplication.CheckLogin * * (..))")
    public void onCheckLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String key = signature.toString();
        Log.e("onCheckLogin", "onCheckLogin: "+(Activity.class.isAssignableFrom(signature.getDeclaringType())) );
        Log.e("onCheckLogin", "onCheckLogin: "+(Fragment.class.isAssignableFrom(signature.getDeclaringType())) );
        Log.e("ActivityAspectJ", joinPoint.getTarget()+"onCheckLogin: 正在检查登录..." + signature.getDeclaringType()+"---"+
                signature.getDeclaringTypeName()+"----"+signature.getName());
        joinPoint.proceed();
    }
}
