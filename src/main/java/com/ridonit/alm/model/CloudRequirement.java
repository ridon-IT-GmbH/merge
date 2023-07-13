package com.ridonit.alm.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CloudRequirement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long requirementId;

    @Version
    @EqualsAndHashCode.Exclude
    private Integer version;

    private String id;

    @Column
    private String projectId;

    @Column
    private String title;

    @Column
    private String type;

    @Column
    private String status;

    @Column
    private String externalId;

    @Column
    private String startdate;

    @Column
    private String duedate;

    @Column
    private Long priorityId;

    @Column
    private String assigneeId;

    @Column
    private String assigneeName;

    @Column
    private String timeboxName;

    @Column
    private String timeboxStartDate;

    @Column
    private String timeboxEndDate;

    @Column
    private String parentId;

    @Column
    private String subStatus;

    @Column
    private String approvalState;

    @Column
    private String description;

    @Column
    private String scopeId;

    @Column
    private String scopeName;

    @Column
    private String assignedRoleId;

    @Column
    private String assignedRoleName;

    @Column
    private String storyPoints;

    @Column
    private Boolean obsolete;

    @Column
    private String workstream;

    @Column
    private String deliverableId;

    @Column
    private String createdTimestamp;

    @Column
    private String lastChangedTimestamp;

    @Column
    private Integer effort;

    @Column
    private String involvedParties;
}
