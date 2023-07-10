package com.ridonit.alm.payload;

import lombok.Data;

@Data
public class CloudRequirementDto {

    private String id;
    private String projectId;
    private String title;
    private String type;
    private String status;
    private String externalId;
    private String startDate;
    private String dueDate;
    private String priorityId;
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
