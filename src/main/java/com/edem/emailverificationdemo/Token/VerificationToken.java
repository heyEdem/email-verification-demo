package com.edem.emailverificationdemo.Token;

import com.edem.emailverificationdemo.User.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;

    public static final int EXPIRATION_TIME = 15;
    @OneToOne
    @JoinColumn(name="user_id")
    private Users users ;

    public VerificationToken(String token, Users users) {
        super();
        this.token = token;
        this.users = users;
        this.expirationTime = this.getExpirationTime();
    }

    public Date getExpirationTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }

    public VerificationToken(String token) {
        super();
        this.token = token;
        this.expirationTime = this.getExpirationTime();
    }

}
