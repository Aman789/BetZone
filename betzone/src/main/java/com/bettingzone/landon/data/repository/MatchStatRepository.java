package com.bettingzone.landon.data.repository;

import com.bettingzone.landon.data.entity.MatchStat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchStatRepository extends CrudRepository<MatchStat, Integer> {
List<MatchStat> findByMatchIdPk(Integer matchId);
}
