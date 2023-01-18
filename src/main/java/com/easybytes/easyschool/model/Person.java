package com.easybytes.easyschool.model;

import com.easybytes.easyschool.annotation.FieldsValueMatch;
import com.easybytes.easyschool.annotation.PasswordValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/*
Note: The @Data annotation applies for POJOs w/o dependencies on each other!!
Because the Person and EazyClass classes have dependencies on each other,
the toString() method will be glad whenever an instance of one of the classes
is called. In other word the toString() of the Person will be called, since the
Person has a dependency of EazyClass, the toString() in EazyClass will also be
called and it will also call the toString() of the person. All that ends in a
endless loop which results in a stackoverflow Exception.
The good news is that the lombok is aware about the situation e.g is not a bug.
To resolve, remove the @Data annotation and use @Getter and @Setter instead.

References:
https://stackoverflow.com/questions/34972895/lombok-hashcode-issue-with-java-lang-stackoverflowerror-null
Udemy lecture questions on the issue:
https://www.udemy.com/course/spring-springboot-jpa-hibernate-zero-to-master/learn/lecture/31085738#questions/17812428
 */
//@Data
@Getter
@Setter
@Entity
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Passwords do not match!!"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email addresses do not match!"
        )
})
public class Person extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private int personId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Confirm Email must not be blank")
    @Email(message = "Please provide a valid confirm email address")
    @Transient //Tell Spring Data JPA go ignore and do not construct this field for any type of DB operations.
    @JsonIgnore
    private String confirmEmail;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @PasswordValidator
    @JsonIgnore
    private String pwd;

    @NotBlank(message = "Confirm Password must not be blank")
    @Size(min = 5,message = "Confirm Password must be at least 5 characters long")
    @Transient
    @JsonIgnore
    private String confirmPwd;

    /*
      EAGER - fetch associated records from both Person and Roles tables.
      PERSIST - Update both Person and Roles tables at the same time.
      targetEntity is optional for readability.
      These relationships are unidirectional only.  Meaning from Parent to child only and NOT from child to parent also.
     */
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId",nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private EazyClass eazyClass;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "person_courses",
             joinColumns = {
                   @JoinColumn(name = "person_id", referencedColumnName = "personId")},
            inverseJoinColumns = {
                   @JoinColumn(name = "course_id", referencedColumnName = "courseId")})
    private Set<Courses> courses = new HashSet<>();
}
