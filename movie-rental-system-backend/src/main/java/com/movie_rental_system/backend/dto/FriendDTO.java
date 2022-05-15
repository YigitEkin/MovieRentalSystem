package com.movie_rental_system.backend.dto;

import com.movie_rental_system.backend.entity.Friend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class FriendDTO {
    private final String customer_name;
    private final String friend_name;
    private final String friend_request_date;

    public FriendDTO(Friend friend) {
        this.customer_name = friend.getCustomer().getUser_name();
        this.friend_name = friend.getFriend().getUser_name();
        this.friend_request_date = friend.getFriend_request_date().toString();
    }

    public static List<FriendDTO> toFriendDTOList(List<Friend> friends) {
        return friends.stream().map(FriendDTO::new).collect(java.util.stream.Collectors.toList());
    }
}
