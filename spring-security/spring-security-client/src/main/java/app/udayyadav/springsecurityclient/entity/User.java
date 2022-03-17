package app.udayyadav.springsecurityclient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long userId;
    private String firstName;
    private String lastName;
    private String userEmail;
    @Column(length = 60)
    private String password;
    private String role;
    private boolean isEnabled = false;
}
