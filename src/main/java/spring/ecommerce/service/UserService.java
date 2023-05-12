package spring.ecommerce.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    // Pagination users (pageNumber, pageSize)
    public List<User> filterUsers (Integer pageNumber, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<User> pagedResult = userRepository.findAll(paging);
        return pagedResult.toList();
    }

    // GET users
    public List<User> findAllUsers () {
        return userRepository.findAll();
    }

    // GET users (by id)
    public Optional<User> findUserById (final Long id) {
        return userRepository.findById(id);
    }

    // POST users
    public Long createUserReturnId(final User userRegistered) {
        User user = new User();
        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
        user.setName(userRegistered.getName());
        user.setSurname(userRegistered.getSurname());
        user.setPassword(bcrypt.encode(userRegistered.getPassword()));
        user.setEmail(userRegistered.getEmail());
        user.setAddress(userRegistered.getAddress());
        return userRepository.save(user).getId();
    }

    // LOGIN users
    public Boolean loginUser(final User userCredentials) {
        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Optional<User> user = userRepository.findByEmail(userCredentials.getEmail());
        boolean isPasswordMatches = false;
        if (user.isPresent()) {
            User userDatabase = user.get();
            isPasswordMatches = bcrypt.matches(userCredentials.getPassword(), userDatabase.getPassword());
        }
        return isPasswordMatches;
    }

    // PUT users
    public void updateUserById(final Long id, final User userUpdated) {
        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
        userRepository.findById(id)
                .map(user -> {
                    user.setName(userUpdated.getName());
                    user.setSurname(userUpdated.getSurname());
                    user.setAddress(userUpdated.getAddress());
                    user.setEmail(userUpdated.getEmail());
                    user.setPassword(bcrypt.encode(userUpdated.getPassword()));
                    userRepository.save(user);
                    return "User Updated Successfully";
                });
    }

    // DELETE users by id
    public void deleteUserById(final Long id) {
        userRepository.deleteById(id);
    }

}
