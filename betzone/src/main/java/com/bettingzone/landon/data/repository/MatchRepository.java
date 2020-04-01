package com.bettingzone.landon.data.repository;

import com.bettingzone.landon.data.entity.Matchs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Matchs, Integer> {
    List<Matchs> findByDate(Date date);

    Iterable<Matchs> findAllByDateBetween(java.sql.Date date, java.sql.Date date1);
}
