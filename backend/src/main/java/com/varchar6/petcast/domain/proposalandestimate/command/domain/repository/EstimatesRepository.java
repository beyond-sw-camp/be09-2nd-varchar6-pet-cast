package com.varchar6.petcast.domain.proposalandestimate.command.domain.repository;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Estimates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimatesRepository extends JpaRepository<Estimates, Integer> {
}
