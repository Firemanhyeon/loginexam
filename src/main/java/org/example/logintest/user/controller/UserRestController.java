package org.example.logintest.user.controller;

import java.util.List;
import org.example.logintest.jwt.util.JwtUtil;
import org.example.logintest.user.domain.AuthResponse;
import org.example.logintest.user.domain.User;
import org.example.logintest.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("123");
        User user = userService.findByUserName(username);
        if(user==null){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if(!passwordEncoder.matches(password, user.getPassword())){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        String accessToken = jwtUtil.generateAccessToken(username);
        String refreshToken = jwtUtil.generateRefreshToken(username);

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam String refreshToken) {
        // 리프레시 토큰이 유효한지 확인
        if (jwtUtil.validateRefreshToken(refreshToken)) {
            // 리프레시 토큰에서 사용자 이름을 추출
            String username = jwtUtil.getUsernameFromRefreshToken(refreshToken);
            // 새로운 액세스 토큰 생성
            String newAccessToken = jwtUtil.generateAccessToken(username);
            // 새로운 액세스 토큰과 기존 리프레시 토큰을 반환
            return ResponseEntity.ok(new AuthResponse(newAccessToken, refreshToken));
        } else {
            // 리프레시 토큰이 유효하지 않은 경우 401 상태 반환
            return ResponseEntity.status(401).body("Invalid Refresh Token");
        }
    }
    @PostMapping("/userreg")
    public User userreg(@RequestBody User user){
        System.out.println("11111");
        return userService.regUser(user);
    }

    @GetMapping("/userList")
    public List<User> userList(){
        return userService.userLIst();
    }
}
