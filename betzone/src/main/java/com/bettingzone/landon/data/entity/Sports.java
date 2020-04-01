package com.bettingzone.landon.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "sports")
public class Sports {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sport_id")
    private Integer sportId;
    @Column(name = "sport_name")
    private String sportName;

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }
}
