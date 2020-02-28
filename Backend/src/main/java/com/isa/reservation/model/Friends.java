package com.isa.reservation.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Friends implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friends_id")
    private Long id;

    @ManyToOne
    private User user1;

    @ManyToOne
    private User user2;

    @Column
    private boolean isAccepted;

    public Friends(){}

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
