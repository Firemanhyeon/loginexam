package org.example.logintest.user.repository;

import org.example.logintest.user.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Authority findByAuthority(String authority);
}
