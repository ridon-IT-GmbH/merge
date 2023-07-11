SELECT max(id)+1 FROM abap_type;
CREATE SEQUENCE abap_type_id_seq MINVALUE 1;
ALTER TABLE abap_type ALTER id SET DEFAULT nextval('abap_type_id_seq');
ALTER SEQUENCE abap_type_id_seq OWNED BY abap_type.id;
ALTER TABLE abap_type ALTER COLUMN version SET DEFAULT 0;

SELECT max(id)+1 FROM bp_mapping;
CREATE SEQUENCE bp_mapping_id_seq MINVALUE 1;
ALTER TABLE abap_type ALTER id SET DEFAULT nextval('bp_mapping_id_seq');
ALTER SEQUENCE bp_mapping_id_seq OWNED BY bp_mapping.id;
ALTER TABLE bp_mapping ALTER COLUMN version SET DEFAULT 0;

SELECT max(id)+1 FROM calm_field;
CREATE SEQUENCE calm_field_id_seq MINVALUE 1;
ALTER TABLE abap_type ALTER id SET DEFAULT nextval('calm_field_id_seq');
ALTER SEQUENCE calm_field_id_seq OWNED BY calm_field.id;
ALTER TABLE calm_field ALTER COLUMN version SET DEFAULT 0;

SELECT max(id)+1 FROM calm_status;
CREATE SEQUENCE calm_status_id_seq MINVALUE 1;
ALTER TABLE abap_type ALTER id SET DEFAULT nextval('calm_status_id_seq');
ALTER SEQUENCE calm_status_id_seq OWNED BY calm_status.id;
ALTER TABLE calm_status ALTER COLUMN version SET DEFAULT 0;

SELECT max(id)+1 FROM internal_mapper_config;
CREATE SEQUENCE internal_mapper_config_id_seq MINVALUE 1;
ALTER TABLE abap_type ALTER id SET DEFAULT nextval('internal_mapper_config_id_seq');
ALTER SEQUENCE internal_mapper_config_id_seq OWNED BY internal_mapper_config.id;
ALTER TABLE internal_mapper_config ALTER COLUMN version SET DEFAULT 0;

SELECT max(id)+1 FROM mapper_confg;
CREATE SEQUENCE mapper_confg_id_seq MINVALUE 1;
ALTER TABLE abap_type ALTER id SET DEFAULT nextval('mapper_confg_id_seq');
ALTER SEQUENCE mapper_confg_id_seq OWNED BY mapper_confg.id;
ALTER TABLE mapper_confg ALTER COLUMN version SET DEFAULT 0;

SELECT max(id)+1 FROM status_config;
CREATE SEQUENCE status_config_id_seq MINVALUE 1;
ALTER TABLE abap_type ALTER id SET DEFAULT nextval('status_config_id_seq');
ALTER SEQUENCE status_config_id_seq OWNED BY status_config.id;
ALTER TABLE status_config ALTER COLUMN version SET DEFAULT 0;

SELECT max(id)+1 FROM transaction_type_config;
CREATE SEQUENCE transaction_type_config_id_seq MINVALUE 1;
ALTER TABLE abap_type ALTER id SET DEFAULT nextval('transaction_type_config_id_seq');
ALTER SEQUENCE transaction_type_config_id_seq OWNED BY transaction_type_config.id;
ALTER TABLE transaction_type_config ALTER COLUMN version SET DEFAULT 0;

SELECT max(id)+1 FROM update_type;
CREATE SEQUENCE update_type_id_seq MINVALUE 1;
ALTER TABLE abap_type ALTER id SET DEFAULT nextval('update_type_id_seq');
ALTER SEQUENCE update_type_id_seq OWNED BY update_type.id;
ALTER TABLE update_type ALTER COLUMN version SET DEFAULT 0;