package com.woxis.votingapp.util;

import com.woxis.votingapp.model.User;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Objects;

public class UserIdFilter extends GenericFilterBean {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (principal instanceof User) {
        UserId.set(((User) principal).getId());
      }
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }
}
