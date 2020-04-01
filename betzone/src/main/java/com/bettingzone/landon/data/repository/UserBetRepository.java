package com.bettingzone.landon.data.repository;

import com.bettingzone.landon.data.entity.UserBet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public interface UserBetRepository extends PagingAndSortingRepository<UserBet, Integer> {
    List<UserBet> findByDate(Date date);
}
