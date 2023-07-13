-- --------------------------------------------------------------------------------------------------------------------
-- FLYWAY: V7.0 - create cloud_requirement.sql
-- --------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS cloud_requirement (
    requirement_id SERIAL,
    version int default 0,
    id varchar(255),
    project_id varchar(255),
    title varchar(255),
    type varchar(255),
    status varchar(255),
    external_id varchar(255),
    startDate varchar(255),
    dueDate varchar(255),
    priority_id BIGINT,
    assignee_id varchar(255),
    assignee_name varchar(255),
    timebox_name varchar(255),
    timebox_start_date varchar(255),
    timebox_end_date varchar(255),
    parent_id varchar(255),
    sub_status varchar(255),
    approval_state varchar(255),
    description varchar(255),
    scope_id varchar(255),
    scope_name varchar(255),
    assigned_role_id varchar(255),
    assigned_role_name varchar(255),
    story_points varchar(255),
    obsolete varchar(255),
    workstream varchar(255),
    deliverable_id varchar(255),
    created_timestamp varchar(255),
    last_changed_timestamp varchar(255),
    effort INTEGER,
    involved_parties varchar(255),
    PRIMARY KEY(requirement_id)
);