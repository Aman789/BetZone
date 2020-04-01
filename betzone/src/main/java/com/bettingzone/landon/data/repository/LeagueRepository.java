package com.bettingzone.landon.data.repository;

import com.bettingzone.landon.data.entity.League;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends CrudRepository<League, Integer> {

}
