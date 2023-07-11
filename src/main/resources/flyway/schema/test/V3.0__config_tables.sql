-- --------------------------------------------------------------------------------------------------------------------
-- FLYWAY: V3.0 - create configuration tables for ui5 and mapper
-- --------------------------------------------------------------------------------------------------------------------

-- CALM_FIELD
CREATE TABLE IF NOT EXISTS calm_field (
    id INTEGER not null,
    version INTEGER not null,
    technical_name VARCHAR(255),
    description VARCHAR(255),
    PRIMARY KEY(id)
    );

-- CALM_STATUS
CREATE TABLE IF NOT EXISTS calm_status (
    id INTEGER not null,
    version INTEGER not null,
    technical_name VARCHAR(255),
    description VARCHAR(255),
    PRIMARY KEY(id)
    );

-- UPDATE_TYPE
CREATE TABLE IF NOT EXISTS update_type (
    id INTEGER not null,
    version INTEGER not null,
    technical_name VARCHAR(255),
    description VARCHAR(255),
    PRIMARY KEY(id)
    );

-- ABAP_TYPE
CREATE TABLE IF NOT EXISTS abap_type (
    id INTEGER not null,
    version INTEGER not null,
    technical_name VARCHAR(255),
    description VARCHAR(255),
    PRIMARY KEY(id)
    );

-- --------------------------------------------------------------

-- TRANSACTION_TYPE_CONFIG
CREATE TABLE IF NOT EXISTS transaction_type_config (
    id  INTEGER not null,
    version INTEGER not null,
    abap_transaction VARCHAR(10),
    calm_field_id INTEGER,
    calm_value VARCHAR(255),
    description VARCHAR(255),
    PRIMARY KEY(id),
    FOREIGN KEY(calm_field_id) REFERENCES calm_field(id)
    );


-- MAPPER_CONFIG
CREATE TABLE IF NOT EXISTS mapper_confg (
    id INTEGER not null,
    version INTEGER not null,
    transaction_type_config_id INTEGER,
    abap_type_id INTEGER,
    calm_field_id INTEGER,
    PRIMARY KEY(id),
    FOREIGN KEY(transaction_type_config_id) REFERENCES transaction_type_config(id),
    FOREIGN KEY(abap_type_id) REFERENCES abap_type(id),
    FOREIGN KEY(calm_field_id) REFERENCES calm_field(id)
    );

-- INTERNAL_MAPPER_CONFIG
CREATE TABLE IF NOT EXISTS internal_mapper_config (
    id INTEGER not null,
    version INTEGER not null,
    abap_type_id INTEGER,
    calm_field_id INTEGER,
    PRIMARY KEY(id),
    FOREIGN KEY(abap_type_id) REFERENCES abap_type(id),
    FOREIGN KEY(calm_field_id) REFERENCES calm_field(id)
    );

-- BP_MAPPING
CREATE TABLE IF NOT EXISTS bp_mapping (
    id INTEGER not null,
    version INTEGER not null,
    transaction_type_config_id INTEGER,
    abap_bp VARCHAR(255),
    calm_bp VARCHAR(255),
    PRIMARY KEY(id),
    FOREIGN KEY(transaction_type_config_id) REFERENCES transaction_type_config(id)
    );

-- STATUS_CONFIG
CREATE TABLE IF NOT EXISTS status_config (
    id INTEGER not null,
    version INTEGER not null,
    transaction_type_config_id INTEGER,
    abap_status VARCHAR(255),
    calm_status_id INTEGER,
    update_type_id INTEGER,
    PRIMARY KEY(id),
    FOREIGN KEY(transaction_type_config_id) REFERENCES transaction_type_config(id),
    FOREIGN KEY(calm_status_id) REFERENCES calm_status(id),
    FOREIGN KEY(update_type_id) REFERENCES update_type(id)
    );



