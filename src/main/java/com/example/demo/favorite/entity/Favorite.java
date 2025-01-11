package com.example.demo.favorite.entity;

import com.example.demo.emergency.entity.Emergency;
import com.example.demo.social.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity(name = "favorite")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emergency_id", nullable = false)
    private Emergency emergency;

}