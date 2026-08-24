package com.campushub.interceptor;

import com.campushub.utils.JwtUtil;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    public JwtInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
       String authorheader = request.getHeader("Authorization");
       if (authorheader==null||!authorheader.startsWith("Bearer")){
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           return false;
       }
       String token = authorheader.substring(7);

        try {
            String userId =jwtUtil.parseToken(token);
            request.setAttribute("userId",userId);
            return true;
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            e.printStackTrace();
            return false;
        }
    }
}
