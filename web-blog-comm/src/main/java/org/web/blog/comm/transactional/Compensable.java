package org.web.blog.comm.transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一个用@interface标注的类就可以作为一个注解使用了，具体的实现还是要通过反射机制去实现具体的功能
 * @author hkrt
 *
 */
//作用于注解上的原始注解
//标注的注解信息会根据不同的类型保存在不同的环境中，默认runtime就好了
@Retention(RetentionPolicy.RUNTIME)
//作用于注解上的原始注解
//标注注解能使用的范围，参数为枚举类型（method为该compensable只能用于方法上 不能作用于类或者参数上）
@Target(ElementType.METHOD)
public @interface Compensable {

}
