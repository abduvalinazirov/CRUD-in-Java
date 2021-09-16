package com.example.students.payload;

import com.example.students.entity.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserRequest {
  private String fullname;
  private String lastname;
  private Long phoneNumber;
  private String password;
  private String username;
}
