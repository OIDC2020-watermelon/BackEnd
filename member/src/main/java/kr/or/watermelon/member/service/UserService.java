package kr.or.watermelon.member.service;

import io.swagger.annotations.ApiParam;
import kr.or.watermelon.member.advice.exception.CEmailSigninFailedException;
import kr.or.watermelon.member.advice.exception.CUserNotFoundException;
import kr.or.watermelon.member.dto.SignupUserDto;
import kr.or.watermelon.member.dto.SimpleUserDto;
import kr.or.watermelon.member.dto.UserDto;
import kr.or.watermelon.member.dto.UserRolesDto;
import kr.or.watermelon.member.entity.User;
import kr.or.watermelon.member.model.response.SingleResult;
import kr.or.watermelon.member.repo.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepo userJpaRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public SignupUserDto signup(SignupUserDto signupUserDto) {
        User user = User.builder()
                .uid(signupUserDto.getUid())
                .name(signupUserDto.getName())
                .password(passwordEncoder.encode(signupUserDto.getPassword()))
                .roles(Collections.singletonList("ROLE_ADMIN"))
                .build();
        User signedUser = userJpaRepo.save(user);
        return modelMapper.map(signedUser, SignupUserDto.class);
    }

    public UserDto signin(String email, String password) {
        User user = userJpaRepo.findByUid(email).orElseThrow(CEmailSigninFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new CEmailSigninFailedException();
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserByUid(String email) {
        User user = userJpaRepo.findByUid(email).orElseThrow(CUserNotFoundException::new);
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserById(Long id) {
        User user = userJpaRepo.findById(id).orElseThrow(CUserNotFoundException::new);
        return modelMapper.map(user, UserDto.class);
    }

    public SimpleUserDto getSimpleUser(String email) {
        User user = userJpaRepo.findByUid(email).orElseThrow(CUserNotFoundException::new);
        return modelMapper.map(user, SimpleUserDto.class);
    }

    public UserRolesDto getUserRoles(String email) {
        User user = userJpaRepo.findByUid(email).orElseThrow(CUserNotFoundException::new);
        return modelMapper.map(user, UserRolesDto.class);
    }

    public UserDto modify(String email, UserDto userDto) {
        User user = userJpaRepo.findByUid(email).orElseThrow(CUserNotFoundException::new);
        user.setUpdate(userDto.getName(), userDto.getPhoneNo(), userDto.getDateOfBirth(), userDto.getGender());
        User modifiedUser = userJpaRepo.save(user);
        return modelMapper.map(modifiedUser, UserDto.class);
    }

    public void delete(String email) {
        User user = userJpaRepo.findByUid(email).orElseThrow(CUserNotFoundException::new);
        userJpaRepo.deleteById(user.getId());
    }
}