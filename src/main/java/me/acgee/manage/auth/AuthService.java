package me.acgee.manage.auth;

import me.acgee.manage.module.permission.entity.Admin;

public interface AuthService {
    Object register(Admin addAdmin);
    String login(String username, String password);
    String refresh(String oldToken);
}
