package com.crawler.Entity;


import com.util.entity.AggregateRoot;

import javax.persistence.*;

@Entity
@Table(name = "Friend")
public class Friend extends AggregateRoot{


    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private Long userId;


    @Column(name = "friendId")
    private Long friendId;


    @Column(name = "status")
    private Long status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
