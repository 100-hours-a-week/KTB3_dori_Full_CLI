package com.example.week4.common.jwt;

import com.example.week4.common.exception.custom.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String header = request.getHeader(jwtUtil.getHeader());

        if (header == null || !header.startsWith(jwtUtil.getPrefix())) {
            throw new UnauthorizedException("권한이 없습니다");
        }

        String token = header.substring(jwtUtil.getPrefix().length());

        if (!jwtUtil.validateToken(token)) {
            throw new UnauthorizedException("권한이 없습니다");
        }
        String email = jwtUtil.getEmail(token);
        request.setAttribute("email", email);

        return true;
    }
}
