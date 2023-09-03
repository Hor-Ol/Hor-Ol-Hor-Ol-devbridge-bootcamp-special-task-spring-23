package com.dvbg.employeescsvuploader.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Employee {

  @Column(name = "id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name")
  @NonNull
  private String first_name;

  @Column(name = "last_name")
  @NonNull
  private String last_name;

  @Column(name = "email")
  @NonNull
  private String email;

  @Column(name = "department")
  @NonNull
  private String department;

  @Column(name = "area")
  @NonNull
  private String area;

}
