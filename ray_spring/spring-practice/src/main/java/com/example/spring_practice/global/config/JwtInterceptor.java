package com.example.spring_practice.global.config;

import com.example.spring_practice.global.security.AuthContext;
import com.example.spring_practice.global.security.JwtUtil;
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
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("토큰이 없습니다");
            return false;
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("유효하지 않은 토큰입니다");
            return false;
        }

        String email = jwtUtil.getEmailFromToken(token);
        AuthContext.setCurrentUserEmail(email);

        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        AuthContext.clear();
    }
}
