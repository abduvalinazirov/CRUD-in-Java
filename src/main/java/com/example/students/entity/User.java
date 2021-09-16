package com.example.students.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User implements Serializable {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(strategy = "uuid", name = "uuid")
  private UUID id;

  private String fullname;
  private String lastname;
  private Long phoneNumber;

  @Column(nullable = false)
  private String password;

  @Column(unique = true, nullable = false)
  private String username;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Role> roles;

  @OneToOne(fetch = FetchType.EAGER)
  private Attachment attachment;

  @CreationTimestamp
  private Date createAt;

  @UpdateTimestamp
  private Date updateAt;

}
