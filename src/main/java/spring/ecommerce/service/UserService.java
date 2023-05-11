package spring.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.ecommerce.domain.Product;
import spring.ecommerce.domain.User;
import spring.ecommerce.model.UserDTO;
import spring.ecommerce.repos.UserRepository;
import spring.ecommerce.util.NotFoundException;


@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Endpoints DTO (automatic)

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map((user) -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setAddress(user.getAddress());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    // Endpoints REST (manual)

    // Pagination users (pageNumber, pageSize)
    public List<User> filterUsers (Integer pageNumber, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<User> pagedResult = userRepository.findAll(paging);
        return pagedResult.toList();
    }

    // GET users
    public List<User> findAllUsers () {
        List<User> usersList = userRepository.findAll();
        return usersList;
    }

    // GET users (by id)
    public Optional<User> findUserById (final Long id) {
        return userRepository.findById(id);
    }

    // POST users
    public Long createUserReturnId(final User user) {
        return userRepository.save(user).getId();
    }

    // PUT users
    public void updateUserById(final Long id, final User userUpdated) {
        userRepository.findById(id)
                .map(user -> {
                    user.setName(userUpdated.getName());
                    user.setSurname(userUpdated.getSurname());
                    user.setAddress(userUpdated.getAddress());
                    user.setEmail(userUpdated.getEmail());
                    user.setPassword(userUpdated.getPassword());
                    userRepository.save(user);
                    return null;
                });
    }

    // DELETE users by id
    public void deleteUserById(final Long id) {
        userRepository.deleteById(id);
    }

}
