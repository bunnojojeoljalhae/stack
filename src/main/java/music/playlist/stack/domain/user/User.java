package music.playlist.stack.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import music.playlist.stack.domain.Time;
import music.playlist.stack.domain.user.Role;

import javax.persistence.*;

//@Data
@Getter
@NoArgsConstructor
@Entity
public class User extends Time {

//    private String birthdate;
//    private String country;
/*    private String displayName;
    private String email;
    private ExternalUrl externalUrls;
    private Followers followers;
    private String href;
    private String id;
    private Image[] images;*/
//    private String product;

    // The object type: "user"
/*    private String type;
    private String uri;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
