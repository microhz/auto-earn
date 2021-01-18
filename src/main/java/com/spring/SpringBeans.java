package com.spring;

import org.springframework.beans.annotation.AnnotationBeanUtils;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author : jihai
 * @date : 2020/12/30
 * @description :
 */
public class SpringBeans {

    public static void main(String[] args) {
        User user = new User();
        user.setName("a");
        AnnotationBeanUtils.copyPropertiesToBean(() -> Property.class, user, new String[]{"userId"});
    }

    @Target( { TYPE, METHOD, FIELD })
    @Retention(RUNTIME)
    @interface Property {

    }

    static class User {

        @Property
        private Long userId;

        @Property
        private String name;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
