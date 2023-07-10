-- --------------------------------------------------------------------------------------------------------------------
-- FLYWAY: V2.0
-- INITIAL DATA
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

-- language
INSERT INTO language(id, version, language_long, language_short)
VALUES (1, 0, 'Deutsch', 'DE'),
       (2, 0, 'Englisch', 'EN'),
       (3, 0, 'Italienisch', 'IT'),
       (4,0,'Spanisch', 'ES'),
       (5,0,'bloedisch', 'hr');