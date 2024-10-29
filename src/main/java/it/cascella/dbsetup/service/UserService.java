package it.cascella.dbsetup.service;

import it.cascella.dbsetup.dto.UserDto;
import it.cascella.dbsetup.entities.User;
import it.cascella.dbsetup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<UserDto> getUsers() {
        List<UserDto> lista = new LinkedList<>();
        userRepository.findAll().forEach(user -> {
            lista.add(UserDto.fromEntity(user));
        });
        return lista;
    }
}
