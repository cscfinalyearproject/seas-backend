package com.tumbwe.examandclassattendanceapi.service.Impl;


import com.tumbwe.examandclassattendanceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameAndIsVerified(username,true).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
