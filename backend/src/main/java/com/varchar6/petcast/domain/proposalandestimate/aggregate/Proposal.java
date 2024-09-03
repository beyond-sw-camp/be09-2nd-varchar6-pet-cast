package com.varchar6.petcast.domain.proposalandestimate.aggregate;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "tbl_proposal")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "cost")
    private int cost;

    @Column(name = "location")
    private String location;

    @Column(name = "time")
    private String time;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProposalStatus status;

    @Column(name = "created_at")
    private String created_at;

    @Column(name = "updated_at")
    private String updated_at;

    @Column(name = "active")
    private boolean active;

    @Column(name = "member_id")
    private int member_id;

}