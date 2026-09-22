package org.champsoft.likeaholic.presentationLayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseModel {
  private Long id;
    private String userId;
    private String name;
    private String email;
    private String password;
}