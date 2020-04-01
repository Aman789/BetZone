package com.bettingzone.landon.data.repository;

import com.bettingzone.landon.data.entity.Bets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public interface BetsRepository extends CrudRepository<Bets, Integer> {


    List<Bets> findByStartDate(Date date);
    List<Bets> findAllByStartDateBetween(Date startDate, Date endDate);

}
