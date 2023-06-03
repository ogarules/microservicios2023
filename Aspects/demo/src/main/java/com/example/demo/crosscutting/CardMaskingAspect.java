package com.example.demo.crosscutting;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import com.example.demo.models.Card;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class CardMaskingAspect {
    
    @Around("execution(Iterable<com.example.demo.models.Card> com.example.demo.services.*.*(..))")
    public Object maskCards(ProceedingJoinPoint pjp) throws Throwable{
        log.info("Masking card numbers");

        Object result = pjp.proceed();
        
        Iterable<Card> cardItems = (Iterable<Card>) result;

        if(cardItems == null){
            return null;
        }

        for(Card cardItem : cardItems){
            String number = cardItem.getCardNumber();
            cardItem.setCardNumber("XXXX-XXXX-XXXX-XX" + number.substring(number.length() - 2, number.length()));

            log.info("Maskingcard {} to {}", number, cardItem.getCardNumber());
        }

        return cardItems;
    }
}
