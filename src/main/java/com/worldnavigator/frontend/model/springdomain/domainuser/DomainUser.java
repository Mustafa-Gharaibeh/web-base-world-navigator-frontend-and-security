package com.worldnavigator.frontend.model.springdomain.domainuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainUser {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Size(min = 2, max = 30, message = "The UserName Size must be lie between 2 and 30 ")
  @UniqueElements
  private String userName;

  @NotBlank
  @Size(min = 8, message = "The password will be at least 8 characters")
  private String password;

  private String role;
  private boolean enabled;
}
