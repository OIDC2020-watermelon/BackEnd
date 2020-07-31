package kr.or.watermelon.member.service;

import kr.or.watermelon.member.advice.exception.CUserNotFoundException;
import kr.or.watermelon.member.dto.UserDto;
import kr.or.watermelon.member.entity.User;
import kr.or.watermelon.member.repo.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepo userJpaRepo;
    private final ModelMapper modelMapper;

    public UserDto signup(UserDto userDto) {
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        User user = User.builder()
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
//        return UserDto.of(user);
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto findUser(String email) {
        User user = userJpaRepo.findByUid(email).orElseThrow(CUserNotFoundException::new);
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
//        return UserDto.of(user);
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto modify(String email, UserDto userDto) {
        User user = userJpaRepo.findByUid(email).orElseThrow(CUserNotFoundException::new);
        user.setUpdate(userDto.getName(), userDto.getPhoneNo(), userDto.getDateOfBirth(), userDto.getGender());
        User modifiedUser = userJpaRepo.save(user);
//        return UserDto.of(modifiedUser);
        return modelMapper.map(modifiedUser, UserDto.class);
    }

    public void delete(String email) {
        User user = userJpaRepo.findByUid(email).orElseThrow(CUserNotFoundException::new);
        userJpaRepo.deleteById(user.getId());
    }
}