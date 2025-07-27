package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author: yuan
 * @Date: 2025-07-23 16:48
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    //切点
    @Pointcut("@annotation(com.sky.annotation.AutoFill)")
    public void pt1(){}

    //增强
    @Before("pt1()")
    public void fillProperty(JoinPoint joinPoint){
        try{
            log.warn("自动填充属性AOP增强:{}",joinPoint);
            //1.拿到参数
            Object[] args = joinPoint.getArgs();
            if(Objects.isNull(args) || args.length == 0){
                return;
            }
            Object pojo = args[0];
            LocalDateTime now = LocalDateTime.now();
            Long empId = BaseContext.getCurrentId();
            //通过反射获取方法上的AutoFill注解
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            AutoFill autoFill = method.getAnnotation(AutoFill.class);
            if(Objects.isNull(autoFill)){
                return;
            }
            //3.获取注解属性,判断是增加还是更新
            OperationType value = autoFill.value();
            Class<?> clazz = pojo.getClass();
            if(OperationType.INSERT == value){
                Method setCreateTimeMethod = clazz.getMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTimeMethod = clazz.getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setCreateUserMethod = clazz.getMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateUserMethod = clazz.getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setCreateTimeMethod.invoke(pojo, now);
                setUpdateTimeMethod.invoke(pojo, now);
                setCreateUserMethod.invoke(pojo, empId);
                setUpdateUserMethod.invoke(pojo, empId);
            }
            else if(OperationType.UPDATE == value){
                Method setUpdateTimeMethod = clazz.getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUserMethod = clazz.getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setUpdateTimeMethod.invoke(pojo, now);
                setUpdateUserMethod.invoke(pojo, empId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
