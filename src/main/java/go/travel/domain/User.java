package go.travel.domain;

import go.travel.domain.code.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userName;
    private String email;
    private String userPicture;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Builder
    public User(String userName, String email, String userPicture, UserRole userRole) {
        this.userName = userName;
        this.email = email;
        this.userPicture = userPicture;
        this.userRole = userRole;
    }

    public User update(String name, String picture) {
        userName = name;
        userPicture = picture;
        return this;
    }

    //    @OneToMany(mappedBy = "user")
//    private List<Member> members = new ArrayList<>();
}
