package aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Audience {

    // 定义切点
    @Pointcut("execution(* interfaces.Performance.perform(..))")
    public void performance() {}

    // 前置通知
    @Before("performance()")
    public void silenceCellPhone() {
        System.out.println("手机调静音");
    }

    // 前置通知
    @Before("performance()")
    public void takeSeats() {
        System.out.println("就坐");
    }

    // 后置返回通知
    @AfterReturning("performance()")
    public void applause() {
        System.out.println("鼓掌！！！");
    }

    // 后置异常通知
    @AfterThrowing("performance()")
    public void demandRefund() {
        System.out.println("要求退票退钱");
    }

}
