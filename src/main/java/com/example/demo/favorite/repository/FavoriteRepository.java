package com.example.demo.favorite.repository;

import com.example.demo.emergency.entity.Emergency;
import com.example.demo.favorite.entity.Favorite;
import com.example.demo.social.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByUserAndEmergency(User user, Emergency emergency);
}