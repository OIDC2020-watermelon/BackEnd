package kr.or.watermelon.member.service;

import kr.or.watermelon.member.advice.exception.CUserNotFoundException;
import kr.or.watermelon.member.dto.SignupUserDto;
import kr.or.watermelon.member.dto.UserIdDto;
import kr.or.watermelon.member.dto.UserDto;
import kr.or.watermelon.member.entity.User;
import kr.or.watermelon.member.repo.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
        User signedUser = userJpaRepo.save(user);
        return modelMapper.map(signedUser, SignupUserDto.class);
    }

    public UserDto getUser(String email) {
        User user = userJpaRepo.findByUid(email).orElseThrow(CUserNotFoundException::new);
        return modelMapper.map(user, UserDto.class);
    }

    public UserIdDto getUserId(String email) {
        User user = userJpaRepo.findByUid(email).orElseThrow(CUserNotFoundException::new);
        return modelMapper.map(user, UserIdDto.class);
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