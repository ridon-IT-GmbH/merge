-- --------------------------------------------------------------------------------------------------------------------
-- FLYWAY: V7.0 - create cloud_requirement.sql
-- --------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS cloud_requirement (
    requirement_id SERIAL,
    version int default 0,
    id varchar(255),
    projectId varchar(255),
    title varchar(255),
    type varchar(255),
    status varchar(255),
    externalId varchar(255),
    startDate varchar(255),
    dueDate varchar(255),
    priorityId BIGINT,
    assigneeId varchar(255),
    assigneeName varchar(255),
    timeboxName varchar(255),
    timeboxStartDate varchar(255),
    timeboxEndDate varchar(255),
    parentId varchar(255),
    subStatus varchar(255),
    approvalState varchar(255),
    description varchar(255),
    scopeId varchar(255),
    scopeName varchar(255),
    assignedRoleId varchar(255),
    assignedRoleName varchar(255),
    storyPoints varchar(255),
    obsolete varchar(255),
    workstream varchar(255),
    deliverableId varchar(255),
    createdTimestamp varchar(255),
    lastChangedTimestamp varchar(255),
    effort INTEGER,
    involvedParties varchar(255),
    PRIMARY KEY(requirement_id)
);