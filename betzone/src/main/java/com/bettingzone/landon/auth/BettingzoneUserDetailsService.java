package com.bettingzone.landon.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BettingzoneUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthGroupRepository authGroupRepository;

    public BettingzoneUserDetailsService(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        super();
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if(null==user){
            throw new UsernameNotFoundException("Username:" + username+" does not exist!");
        }
        List<AuthGroup> authGroups = this.authGroupRepository.findByUsername(username);
        for (AuthGroup authGroup : authGroups) {
            System.out.println(authGroup.getUsername());
            System.out.println(authGroup.getAuthGroup());
            System.out.println(authGroup.getId());
        }
        return new BettingzoneUserPrinciple(user, authGroups);
    }
}
