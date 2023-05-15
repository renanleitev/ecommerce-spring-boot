package spring.ecommerce.service;

import spring.ecommerce.model.User;

public interface AuthService {
    String login(User user);
}