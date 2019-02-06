package com.elitbet.wagers.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.logging.Logger;

/**
 * @author Roman Malyarchuk
 */
public class SecurityUtil {

    private final static Logger logger = Logger.getLogger(SecurityUtil.class.getName());

    public static String getCurrentUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            return ((UserDetails)principal).getUsername();
        }else{
            return principal.toString();
        }
    }
}
