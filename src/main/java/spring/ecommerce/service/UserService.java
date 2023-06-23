package spring.ecommerce.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.ecommerce.model.User;
import spring.ecommerce.repos.UserRepository;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Map user to another and encrypt password
    public User mapUser (User originalUser, User newUser){
        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
        newUser.setUsername(originalUser.getUsername());
        newUser.setName(originalUser.getName());
        newUser.setSurname(originalUser.getSurname());
        newUser.setPassword(bcrypt.encode(originalUser.getPassword()));
        newUser.setEmail(originalUser.getEmail());
        newUser.setAddress(originalUser.getAddress());
        newUser.setRole(originalUser.getRole());
        return newUser;
    }

    // GET users by page
    public Page<User> findUserByPage (Pageable paging) {
        return userRepository.findAll(paging);
    }

    public Page<User> findUser (Map<String, String> customQuery, Pageable paging) {
        switch (customQuery.keySet().toString()) {
            case "[_username, _page, _limit]" -> {
                String username = customQuery.get("_username");
                return userRepository.findAllByNameLike(username, paging);
            }
            case "[_name, _page, _limit]" -> {
                String name = customQuery.get("_name");
                return userRepository.findAllByNameLike(name, paging);
            }
            case "[_surname, _page, _limit]" -> {
                String surname = customQuery.get("_surname");
                return userRepository.findAllBySurnameLike(surname, paging);
            }
            case "[_address, _page, _limit]" -> {
                String address = customQuery.get("_address");
                return userRepository.findAllByAddressLike(address, paging);
            }
            case "[_email, _page, _limit]" -> {
                String email = customQuery.get("_email");
                return userRepository.findAllByEmailLike(email, paging);
            }
            case "[_role, _page, _limit]" -> {
                String role = customQuery.get("_role");
                return userRepository.findAllByRoleLike(role, paging);
            }
            case "[_column, _order, _page, _limit]" -> {
                String column = customQuery.get("_column");
                String order = customQuery.get("_order");
                Page<User> usersList = switch (order) {
                    case "ASC" -> userRepository.findAll(PageRequest.of(paging.getPageNumber(), paging.getPageSize(), Sort.by(Sort.Direction.ASC, column)));
                    case "DESC" -> userRepository.findAll(PageRequest.of(paging.getPageNumber(), paging.getPageSize(), Sort.by(Sort.Direction.DESC, column)));
                    default -> userRepository.findAll(paging);
                };
                return usersList;
            }
            default -> {
                return userRepository.findAll(paging);
            }
        }
    }

    // GET users
    public List<User> findAllUsers () {
        return userRepository.findAll();
    }

    // GET users (by email)
    public Optional<User> findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    // GET users (by id)
    public Optional<User> findUserById (final Long id) {
        return userRepository.findById(id);
    }

    // POST users
    public Long createUserReturnId(final User userRegistered) {
        Optional<User> userExists = userRepository.findByEmail(userRegistered.getEmail());
        User user = new User();
        if (userExists.isEmpty()) {
            user = mapUser(userRegistered, user);
            return userRepository.save(user).getId();
        }
        return null;
    }

    // PUT users
    public Boolean updateUserById(final User userUpdated) {
        return userRepository.findById(userUpdated.getId())
                .map(user -> {
                    user = mapUser(userUpdated, user);
                    userRepository.save(user);
                    return user;
                }).isPresent();
    }

    // DELETE users by id
    public void deleteUserById(final Long id) {
        userRepository.deleteById(id);
    }

}
