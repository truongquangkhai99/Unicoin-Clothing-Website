package com.unicoin.order.resstresponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Slf4j
public class Translator {

    private  static MessageSource messageSource;

    @Autowired
    Translator(MessageSource messageSource){Translator.messageSource = messageSource;}

    public static String toLocale(String msgCode, Object[] args){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, args, locale);
    }

    public static String toLocale(String msgCode){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, null, locale);
    }
}
