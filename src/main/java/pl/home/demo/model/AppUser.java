package pl.home.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotNull
    private String username;
    @NotNull
    private String password;

    @OneToMany
    private List<Reservation> reservations;

    public AppUser() {
    }

    public AppUser(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }
}
