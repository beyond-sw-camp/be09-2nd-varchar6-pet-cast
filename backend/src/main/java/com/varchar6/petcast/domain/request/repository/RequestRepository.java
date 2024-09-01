package com.varchar6.petcast.domain.request.repository;

import com.varchar6.petcast.domain.request.aggregate.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, int> {

}
