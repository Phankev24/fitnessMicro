package app.user.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalUserId;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID externalUserId;

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;


    public User(UUID externalUserId, String userName, String firstName, String lastName, String email, String password, String phoneNumber) {
        this.externalUserId = externalUserId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    @PrePersist
    public void generateUuid(){
        if (this.externalUserId == null){
            this.externalUserId = UUID.randomUUID();
        }
    }
}
