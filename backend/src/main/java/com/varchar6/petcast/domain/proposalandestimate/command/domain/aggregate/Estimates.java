package com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_estimate")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estimates {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "expected_cost")
    private int expectedCost;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EstimatesStatus status;


    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "active")
    private boolean active;

    @Column(name = "company_id")
    private int companyId;

    @Column(name = "proposal_id")
    private int proposalId;

    // 견적서 승인
    public void accept() {
        this.status = EstimatesStatus.CONFIRMED;
    }
    // 견적서 거절
    public void reject() {
        this.status = EstimatesStatus.REJECTED;
    }
}