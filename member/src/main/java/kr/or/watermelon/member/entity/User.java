package kr.or.watermelon.member.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder // builder를 사용할수 있게 합니다.
@Entity // jpa entity임을 알립니다.
@Getter // user 필드값의 getter를 자동으로 생성합니다.
@Setter // user 필드값의 setter를 자동으로 생성합니다.
@ToString
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
@Table(name = "user") // 'user' 테이블과 매핑됨을 명시
public class User implements UserDetails {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Email
    @Column(nullable = false, unique = true, length = 100)
    private String uid;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 100)
    private String password;
    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private String phoneNo;
    @Column(length = 100)
    private String dateOfBirth;
    @Column(length = 100)
    private String ageRange;
    @Column(length = 100)
    private String age;
    @Column(length = 100)
    private String gender;
    @Column(length = 100)
    private String provider;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.uid;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 수정시 데이터 처리
    public User setUpdate(String name, String phoneNo, String dateOfBirth, String gender) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        return this;
    }
}