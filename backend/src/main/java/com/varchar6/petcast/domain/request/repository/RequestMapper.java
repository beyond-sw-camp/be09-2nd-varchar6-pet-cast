package com.varchar6.petcast.domain.request.repository;

import com.varchar6.petcast.domain.request.aggregate.Request;

import java.util.List;

public interface RequestMapper {
    List<Request> findAllRequestsByMemberId(int memberId);

    List<Request> findAllRequestsByCompanyId(int companyId);

    Request findRequestById(int requestId);
}
