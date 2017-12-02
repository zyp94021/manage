package me.acgee.manage.secruity;

import me.acgee.manage.module.permission.dao.AdminRepository;
import me.acgee.manage.module.permission.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminRepository adminDao;

    @Override
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {
        Admin admin = adminDao.findByAdminName(adminName);

        if (admin == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", adminName));
        } else {
            return JwtUserFactory.create(admin);
        }
    }
}
