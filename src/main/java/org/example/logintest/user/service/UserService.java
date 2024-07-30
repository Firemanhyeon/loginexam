package org.example.logintest.user.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.example.logintest.user.domain.Authority;
import org.example.logintest.user.domain.User;
import org.example.logintest.user.repository.AuthorityRepository;
import org.example.logintest.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public User regUser(User user){
        Authority userRole = authorityRepository.findByAuthority("USER");
        // 사용자에게 역할 할당
        Set<Authority> roles = new HashSet<>();
        roles.add(userRole);
        user.setAuthorities(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User findByUserName(String username){
        return userRepository.findByUsername(username);
    }
    public List<User> userLIst(){
        return userRepository.findAll();
    }
}
