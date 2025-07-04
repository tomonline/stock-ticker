package online.iamtom.stock_ticker.aspects;

import online.iamtom.stock_ticker.services.NotificationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositorySaveAspect {

    private final NotificationService notificationService;

    public RepositorySaveAspect(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Match any save(...) method in any JpaRepository subclass
    @AfterReturning(
            pointcut = "execution(* org.springframework.data.repository.CrudRepository+.save(..))",
            returning = "savedEntity"
    )
    public void afterSave(JoinPoint joinPoint, Object savedEntity) {
        notificationService.sendMessage(savedEntity);
    }
}
