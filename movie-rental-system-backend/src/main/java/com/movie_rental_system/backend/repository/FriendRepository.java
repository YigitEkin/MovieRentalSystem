package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.Friend;
import com.movie_rental_system.backend.util.FriendKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, FriendKey> {

    @Query(value = "SELECT * FROM friend WHERE customer_name = ?1 OR friend_name = ?1", nativeQuery = true)
    List<Friend> findFriendsOfCustomer(String customer_name);

    @Query(value = "SELECT * FROM friend WHERE (customer_name = ?1 AND friend_name = ?2) OR " +
            "friend_name = ?1 AND customer_name = ?2", nativeQuery = true)
    Friend findFriend(String customer_name, String friend_name);

    // delete friend
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM friend WHERE (customer_name = ?1 AND friend_name = ?2) OR (customer_name = ?2 AND friend_name = ?1)", nativeQuery = true)
    void deleteFriend(String customer_name, String friend_name);
}
