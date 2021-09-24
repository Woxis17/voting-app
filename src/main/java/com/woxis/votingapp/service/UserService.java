package com.woxis.votingapp.service;

import com.woxis.votingapp.dto.UserDTO;
import com.woxis.votingapp.exception.AlreadyExistsException;
import com.woxis.votingapp.mapper.UserMapper;
import com.woxis.votingapp.model.User;
import com.woxis.votingapp.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public Long createUser(UserDTO userDTO) {
    log.info("Creating user with username: {}", userDTO.getUsername());
    if (userRepository.existsByUsernameIgnoreCase(userDTO.getUsername())) {
      throw new AlreadyExistsException();
    }
    User user = userMapper.fromDto(userDTO);
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    userRepository.save(user);
    return user.getId();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsernameIgnoreCase(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
