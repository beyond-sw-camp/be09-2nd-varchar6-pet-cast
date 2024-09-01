package com.varchar6.petcast.domain.proposalandestimate.quary.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "tbl_estimate")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estimate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cost")
    private int cost;

    @Enumerated(EnumType.STRING)
    private estimateStatus status;

    @Column(name = "created_at")
    private String created_at;

    @Column(name = "updated_at")
    private String updated_at;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyInfo companyInfo;

    @ManyToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;
}
