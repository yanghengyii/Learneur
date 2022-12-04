package edu.whu.learneur.dto;

import lombok.Data;

@Data
public class ForgetPasswordDTO {
    private String code;
    private String password;
}
