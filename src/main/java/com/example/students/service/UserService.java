package com.example.students.service;

import com.example.students.entity.User;
import com.example.students.payload.GetUserRequest;
import com.example.students.payload.UserRequest;
import com.example.students.repository.RoleRepository;
import com.example.students.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;

  public boolean addUser(UserRequest userRequest) {
    User user = new User();
    user.setFullname(userRequest.getFullname());
    user.setUsername(userRequest.getUsername());
    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    user.setLastname(userRequest.getLastname());
    user.setPhoneNumber(userRequest.getPhoneNumber());
//    user.setAttachment(userRequest.getAttachment());
    user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
    return userRepository.save(user) != null;
  }

  public boolean deleteStudent(UUID id) {
    Optional<User> user = userRepository.findById(id);
    try {
      userRepository.delete(user.get());
      return true;
    } catch (Exception e) {
      return false;
    }
  }

//  public List<GetUserRequest> getAllStudents() {
//    List<GetUserRequest> userRequests = new ArrayList<>();
//    List<User> users = userRepository.findAll();
//    for (int i = 0; i < users.size(); i++) {
//      userRequests.get(i).setLastname(users.get(i).getLastname());
//      userRequests.get(i).setUsername(users.get(i).getUsername());
//      userRequests.get(i).setFullname(users.get(i).getFullname());
//      userRequests.get(i).setPhoneNumber(users.get(i).getPhoneNumber());
//    }
//    return userRequests;
//  }

}
