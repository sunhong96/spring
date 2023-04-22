package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    // @Around("execution(* hello.hellospring.service..*(..))") // 서비스만 하고싶으면
    @Around("execution(* hello.hellospring..*(..))")

    //execution(* hello.hellospring..*(..))는 Pointcut(포인트컷)을 정의하는 표현식으로, 어떤 메소드가 대상인지를 지정합니다.
    //
    //*는 리턴 타입을 의미합니다. hello.hellospring 패키지와 그 하위 패키지에 있는 모든 메소드를 대상으로 하며,
    // ..는 0개 이상의 인자를 의미합니다. 따라서,
    // execution(* hello.hellospring..*(..))는 hello.hellospring 패키지와 그 하위 패키지에 있는 모든 메소드를 대상으로 하며,
    // 인자의 개수와 타입에 제한이 없다는 의미입니다.

    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try {
            Object result = joinPoint.proceed();
            // proceed() 는 @Around 어노테이션이 적용된 메소드에서 호출되며,
            // Advice가 적용된 메소드를 호출하고 그 결과를 반환하는 역할을 합니다.
            return result;
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + timeMs + "ms");
        }
    }
}
