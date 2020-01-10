package net.broscorp.community_app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthBody {

  private String email;
  private String password;
}