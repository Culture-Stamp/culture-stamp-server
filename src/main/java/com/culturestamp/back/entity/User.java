package com.culturestamp.back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class User extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginAt;

    @Builder
    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User update(String name) {
        this.nickname = name;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
