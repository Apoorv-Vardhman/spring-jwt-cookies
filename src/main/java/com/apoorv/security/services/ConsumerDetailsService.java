package com.apoorv.security.services;

import com.apoorv.security.dao.ConsumerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Service
public class ConsumerDetailsService implements UserDetailsService {
    @Autowired
    ConsumerDao consumerDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return consumerDao.findByEmail(username);
    }
}
