package go.travel.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.time.LocalTime;

/*
    Aop 동작 순서.
    1. 스프링에서(빈 대상이 되는) 객체생성
    2. 생성한 객체를 빈후처리기에 전달
    3. Advisor 빈을 조회한다 스프링 컨테이너에서 Advisor로 등록된 모든 빈을 조회
    그 후 @Aspect Advisor 빈을 조회한다. @Aspect 어드바이저 빌더 내부에 저장된 Advisor 전부 조회.
    4. 프록시 적용대상을 체크한다. 이때 포인트컷을 기준으로 사용하는데 포인트컷은 대상(빈)을 프록시 객체로 만들지, 그리고
    해당 메서드가 실행 되었을 때 어드바이스를 실행할지의 기준을 알려준다 (2번 사용된다.)
    5.프록시 적용 대상이면 프록시를 생성하고 프록시 반환 , 아니라면 원본 객체를 반환하여 빈으로 등록한다.

    왜 사용하는가 ?
    횡단 관심사를 한 곳에 모아두고 사용하기 위해서.

    @Aspect는 컴포넌트 스캔의 대상이 아니다 따라서 따로 등록을 해줘야한다.
 */

@Aspect
@Slf4j
public class TestAspect {


    //포인트컷
    @Around("execution(* go.travel..*(..))")
    //어드바이스
    public Object testAop(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            log.info("[Default Log] Execution = {} , CurrentTime = {} " , joinPoint.getSignature() , LocalTime.now());
            //프록시에서 실제 Target을 실행 -> 어드바이저가 더 있다면 확인해서 다음 체인(프록시)을 실행.
            Object proceed = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            log.info("[Default Log]  ExecutionTime = {} , EndTime = {} ", endTime - startTime, LocalTime.now());
            return proceed;
        } catch (Exception e) {
            log.info("[Exception] Time = {} ExceptionMessage = {}" , LocalTime.now(), e.getMessage());
            throw e;
        }

    }

}
