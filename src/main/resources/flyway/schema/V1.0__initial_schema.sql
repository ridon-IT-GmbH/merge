-- --------------------------------------------------------------------------------------------------------------------
-- FLYWAY: V1.0
-- --------------------------------------------------------------------------------------------------------------------

-- LANGUAGE
CREATE TABLE IF NOT EXISTS language
(
    id                  int(11) NOT NULL AUTO_INCREMENT,
    version             int(11) NOT NULL,
    language_long       varchar(20) NOT NULL,
    language_short      varchar(2) NOT NULL,
    PRIMARY KEY (id)
);