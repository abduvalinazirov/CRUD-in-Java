package com.example.students.controller;

import com.example.students.entity.User;
import com.example.students.payload.UserRequest;
import com.example.students.repository.UserRepository;
import com.example.students.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminController {
  private final UserService userService;
  private final UserRepository userRepository;

  @PostMapping("/add/student")
  private ResponseEntity save(@RequestBody UserRequest userRequest) {
    return userService.addUser(userRequest) ? ResponseEntity.ok("Qo'shildi") : new ResponseEntity("Xatolik", HttpStatus.INTERNAL_SERVER_ERROR);
  }

//  @GetMapping("/getAllStudents")
//  private ResponseEntity getStudents() {
//    return ResponseEntity.ok(userService.getAllStudents());
//  }

  @GetMapping("/getAllStudents")
  private ResponseEntity getStudents() {
    return ResponseEntity.ok(userRepository.findAll());
  }

  @DeleteMapping("/deleteStudent/{id}")
  private ResponseEntity deleteStudent(@PathVariable UUID id) {
    return userService.deleteStudent(id) ? ResponseEntity.ok("O'chirildi") : new ResponseEntity("Xatolik", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @PostMapping("/update/student/{id}")
  private ResponseEntity update(@PathVariable UUID id, @RequestBody User user) {
    Optional<User> user1 = userRepository.findById(id);
    if (user != null) {
      user.setId(id);
      userRepository.save(user);
      return ResponseEntity.ok("Yaratildi");
    } else {
      return (ResponseEntity) ResponseEntity.notFound();
    }
  }
}
