package demo;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect {

    @Pointcut("within(demo..*)")
    public void authenticatingPointCut() {

    }

    @Pointcut("within(demo..*)")
    public void authorizingPointCut() {

    }

    @Before("authenticatingPointCut() || authorizingPointCut()")
    public void authenticate() {
        System.out.println("authenticating the request");
    }
}
