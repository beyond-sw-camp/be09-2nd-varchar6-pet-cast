package com.varchar6.petcast.domain.proposalandestimate.command.domain.repository;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Proposals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalsRepository extends JpaRepository<Proposals, Integer> {

}
