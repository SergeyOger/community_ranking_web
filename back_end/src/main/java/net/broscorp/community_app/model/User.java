package net.broscorp.community_app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APP_USERS")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  private String repository;

  private String phoneNumber;

  private boolean active;

  private String role;

  public User() {
  }

  public int getId() {
    return this.id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public String getRepository() {
    return this.repository;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public boolean isActive() {
    return this.active;
  }

  public String getRole() {
    return this.role;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRepository(String repository) {
    this.repository = repository;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    final User other = (User) o;
    if (!other.canEqual((Object) this)) {
      return false;
    }
    if (this.getId() != other.getId()) {
      return false;
    }
    final Object this$firstName = this.getFirstName();
    final Object other$firstName = other.getFirstName();
    if (this$firstName == null ? other$firstName != null
        : !this$firstName.equals(other$firstName)) {
      return false;
    }
    final Object this$lastName = this.getLastName();
    final Object other$lastName = other.getLastName();
    if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) {
      return false;
    }
    final Object this$email = this.getEmail();
    final Object other$email = other.getEmail();
    if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
      return false;
    }
    final Object this$password = this.getPassword();
    final Object other$password = other.getPassword();
    if (this$password == null ? other$password != null : !this$password.equals(other$password)) {
      return false;
    }
    final Object this$repository = this.getRepository();
    final Object other$repository = other.getRepository();
    if (this$repository == null ? other$repository != null
        : !this$repository.equals(other$repository)) {
      return false;
    }
    final Object this$phoneNumber = this.getPhoneNumber();
    final Object other$phoneNumber = other.getPhoneNumber();
    if (this$phoneNumber == null ? other$phoneNumber != null
        : !this$phoneNumber.equals(other$phoneNumber)) {
      return false;
    }
    if (this.isActive() != other.isActive()) {
      return false;
    }
    final Object this$role = this.getRole();
    final Object other$role = other.getRole();
    if (this$role == null ? other$role != null : !this$role.equals(other$role)) {
      return false;
    }
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof User;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.getId();
    final Object $firstName = this.getFirstName();
    result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
    final Object $lastName = this.getLastName();
    result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
    final Object $email = this.getEmail();
    result = result * PRIME + ($email == null ? 43 : $email.hashCode());
    final Object $password = this.getPassword();
    result = result * PRIME + ($password == null ? 43 : $password.hashCode());
    final Object $repository = this.getRepository();
    result = result * PRIME + ($repository == null ? 43 : $repository.hashCode());
    final Object $phoneNumber = this.getPhoneNumber();
    result = result * PRIME + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
    result = result * PRIME + (this.isActive() ? 79 : 97);
    final Object $role = this.getRole();
    result = result * PRIME + ($role == null ? 43 : $role.hashCode());
    return result;
  }

  public String toString() {
    return "User(id=" + this.getId() + ", firstName=" + this.getFirstName() + ", lastName=" + this
        .getLastName() + ", email=" + this.getEmail() + ", password=" + this.getPassword()
        + ", repository=" + this.getRepository() + ", phoneNumber=" + this.getPhoneNumber()
        + ", active=" + this.isActive() + ", role=" + this.getRole() + ")";
  }
}
