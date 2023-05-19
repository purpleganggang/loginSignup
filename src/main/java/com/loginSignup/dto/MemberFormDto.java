package com.loginSignup.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;

import com.loginSignup.constant.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFormDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식을 맞춰주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해 주세요.")       
    //@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{4,20}",
    //message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 4자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;

    private Role role;

    @NotEmpty(message = "비밀번호 확인을 입력해 주세요.")
    private String passwordConfirm;
    
    
    
    
    /**
     * 커스텀 유효성검사
     */
    public void memberValidate(BindingResult bindingResult) {
    	//비밀번호 와 비밀번호 확인 일치 체크
    	if(!this.password.equals(this.passwordConfirm)) {
    		//rejectValue @Nullable String field, String errorCode, String defaultMessage
    		bindingResult.rejectValue("passwordConfirm",  "error-password-Confirm", "비밀번호와 비밀번호확인 값이 일치하지 않습니다.");
    	}
    }
    
    
    
    
    
}
