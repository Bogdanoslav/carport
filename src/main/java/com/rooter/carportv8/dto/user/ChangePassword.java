package com.rooter.carportv8.dto.user;

import com.rooter.carportv8.validation.FieldsValueMatch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldsValueMatch(field = "newPassword", fieldMatch = "confirmPassword")
public class ChangePassword {
    @NotNull
    @Size(min = 8, max = 256)
    private String oldPassword;
    @NotNull
    @Size(min = 8, max = 256)
    private String newPassword;
    @NotNull
    @Size(min = 8, max = 256)
    private String confirmPassword;
}
