-- --------------------------------------------------------------------------------------------------------------------
-- FLYWAY: V1.0
-- --------------------------------------------------------------------------------------------------------------------

-- LANGUAGE
CREATE TABLE IF NOT EXISTS language
(
    id                  integer,
    version             integer,
    language_long       varchar(20) NOT NULL,
    language_short      varchar(2) NOT NULL,
    PRIMARY KEY (id)
);