package com.ridonit.alm.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class CloudRequirementDto {

    @JsonIgnore
    private Long requirementId;

    @JsonIgnore
    private Integer version;

    private String id;
    private String projectId;
    private String title;
    private String type;
    private String status;
    private String externalId;
    private String startDate;
    private String dueDate;
    private Long priorityId;
    private String assigneeId;
    private String assigneeName;
    private String timeboxName;
    private String timeboxStartDate;
    private String timeboxEndDate;
    private String parentId;
    private String subStatus;
    private String approvalState;
    private String description;
    private String scopeId;
    private String scopeName;
    private String assignedRoleId;
    private String assignedRoleName;
    private String storyPoints;
    private Boolean obsolete;
    private String workstream;
    private String deliverableId;
    private String createdTimestamp;
    private String lastChangedTimestamp;
    private Integer effort;
    private String involvedParties;
}
