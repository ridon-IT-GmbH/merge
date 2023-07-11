--
-- PostgreSQL database dump
--

-- Dumped from database version 12.14
-- Dumped by pg_dump version 15.3

-- Started on 2023-07-11 11:02:03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4216 (class 0 OID 18083)
-- Dependencies: 218
-- Data for Name: abap_type; Type: TABLE DATA; Schema: public; Owner: dbo
--

INSERT INTO public.abap_type (id, version, technical_name, description) VALUES (4, 1, 'partner', 'Partner');
INSERT INTO public.abap_type (id, version, technical_name, description) VALUES (3, 1, 'Rich_Text', 'Rich Text');
INSERT INTO public.abap_type (id, version, technical_name, description) VALUES (2, 1, 'Customer_h', 'Customer');
INSERT INTO public.abap_type (id, version, technical_name, description) VALUES (1, 1, 'Orderadm_h', 'Header');
INSERT INTO public.abap_type (id, version, technical_name, description) VALUES (5, 1, 'Activity_h', 'Activity');


--
-- TOC entry 4213 (class 0 OID 18059)
-- Dependencies: 215
-- Data for Name: calm_field; Type: TABLE DATA; Schema: public; Owner: dbo
--

INSERT INTO public.calm_field (id, version, technical_name, description) VALUES (10, 1, 'involvedParties', 'Involved Parties');
INSERT INTO public.calm_field (id, version, technical_name, description) VALUES (9, 1, 'assigneeId', 'Responsible');
INSERT INTO public.calm_field (id, version, technical_name, description) VALUES (8, 1, 'workstream', 'Workstream');
INSERT INTO public.calm_field (id, version, technical_name, description) VALUES (7, 1, 'dueDate', 'Due Date');
INSERT INTO public.calm_field (id, version, technical_name, description) VALUES (6, 1, 'startDate', 'Start Date');
INSERT INTO public.calm_field (id, version, technical_name, description) VALUES (5, 1, 'priorityId', 'Priority');
INSERT INTO public.calm_field (id, version, technical_name, description) VALUES (4, 1, 'subStatus', 'Status');
INSERT INTO public.calm_field (id, version, technical_name, description) VALUES (1, 1, 'title', 'Description');
INSERT INTO public.calm_field (id, version, technical_name, description) VALUES (2, 1, 'description', 'Rich Text');
INSERT INTO public.calm_field (id, version, technical_name, description) VALUES (11, 1, 'projectId', 'Project ID');
INSERT INTO public.calm_field (id, version, technical_name, description) VALUES (3, 1, 'scopeId', 'Scope Id');


--
-- TOC entry 4217 (class 0 OID 18091)
-- Dependencies: 219
-- Data for Name: transaction_type_config; Type: TABLE DATA; Schema: public; Owner: dbo
--

INSERT INTO public.transaction_type_config (id, version, abap_transaction, calm_field_id, calm_value, description) VALUES (1, 1, 'ZRIR', 3, '7aa513b7-e191-4824-a61a-92d418d55b8a', 'IT Requirement');
INSERT INTO public.transaction_type_config (id, version, abap_transaction, calm_field_id, calm_value, description) VALUES (2, 1, 'ZRCR', 3, '64176c3f-2ee2-4457-b7d8-b411947d5f52', 'Request for Change');


--
-- TOC entry 4220 (class 0 OID 18139)
-- Dependencies: 222
-- Data for Name: bp_mapping; Type: TABLE DATA; Schema: public; Owner: dbo
--

INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (1, 1, 1, '374', 'WS005');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (2, 1, 1, '375', 'WS003');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (3, 1, 1, '376', 'WS001');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (4, 1, 1, '378', 'WS010');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (5, 1, 1, '378', 'WS006');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (6, 1, 1, '379', 'WS004');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (7, 1, 1, '380', 'WS011');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (8, 1, 1, '381', 'WS002');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (9, 1, 1, '382', 'WS007');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (10, 1, 1, '383', 'WS008');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (11, 1, 1, '384', 'WS009');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (12, 1, 1, '201', 'wild@ridon.de');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (13, 1, 1, '331', 'wolf@ridon.de');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (14, 1, 1, '197', 'niedermann@ridon.de');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (15, 1, 1, '192', 'waldenmaier@ridon.de');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (16, 1, 1, '194', 'runge@ridon.de');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (17, 1, 2, '374', 'WS005');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (18, 1, 2, '375', 'WS003');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (19, 1, 2, '376', 'WS001');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (20, 1, 2, '377', 'WS010');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (21, 1, 2, '378', 'WS006');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (22, 1, 2, '379', 'WS004');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (23, 1, 2, '380', 'WS011');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (24, 1, 2, '381', 'WS002');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (25, 1, 2, '382', 'WS007');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (26, 1, 2, '383', 'WS008');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (27, 1, 2, '384', 'WS009');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (28, 1, 2, '201', 'wild@ridon.de');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (29, 1, 2, '331', 'wolf@ridon.de');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (30, 1, 2, '197', 'niedermann@ridon.de');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (31, 1, 2, '192', 'waldenmaier@ridon.de');
INSERT INTO public.bp_mapping (id, version, transaction_type_config_id, abap_bp, calm_bp) VALUES (32, 1, 2, '194', 'runge@ridon.de');


--
-- TOC entry 4214 (class 0 OID 18067)
-- Dependencies: 216
-- Data for Name: calm_status; Type: TABLE DATA; Schema: public; Owner: dbo
--

INSERT INTO public.calm_status (id, version, technical_name, description) VALUES (7, 1, 'NOT_PLANNED', 'Not Planned');
INSERT INTO public.calm_status (id, version, technical_name, description) VALUES (6, 1, 'BLOCKED', 'Blocked');
INSERT INTO public.calm_status (id, version, technical_name, description) VALUES (5, 1, 'CONFIRMED', 'Confirmed');
INSERT INTO public.calm_status (id, version, technical_name, description) VALUES (4, 1, 'APPROVED_FOR_DEPLOYMENT', 'In Testing');
INSERT INTO public.calm_status (id, version, technical_name, description) VALUES (3, 1, 'IN_REALIZATION', 'In Realization');
INSERT INTO public.calm_status (id, version, technical_name, description) VALUES (2, 1, 'TO_BE_APPROVED', 'In Approval');
INSERT INTO public.calm_status (id, version, technical_name, description) VALUES (1, 1, 'CREATED', 'In Refinement');




--
-- TOC entry 4218 (class 0 OID 18104)
-- Dependencies: 220
-- Data for Name: mapper_confg; Type: TABLE DATA; Schema: public; Owner: dbo
--

INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (6, 1, 1, NULL, 6, NULL);
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (7, 1, 1, NULL, 7, NULL);
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (10, 1, 1, 4, 10, NULL);
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (11, 1, 1, NULL, 11, NULL);
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (16, 1, 2, NULL, 6, NULL);
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (17, 1, 2, NULL, 7, NULL);
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (20, 1, 2, 4, 10, NULL);
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (21, 1, 2, NULL, 11, NULL);
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (1, 1, 1, 1, 1, 'DESCRIPTION');
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (2, 1, 1, 3, 2, 'ZIR4');
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (12, 1, 2, 1, 1, 'DESCRIPTION');
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (13, 1, 2, 3, 2, 'ZCR2');
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (3, 1, 1, 1, 3, 'PROCESS_TYPE');
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (14, 1, 2, 1, 3, 'PROCESS_TYPE');
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (5, 1, 1, 5, 5, 'PRIORITY');
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (8, 1, 1, 4, 8, 'SMCD0006');
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (9, 1, 1, 4, 9, 'SMIR0001');
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (15, 1, 2, 5, 5, 'PRIORITY');
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (18, 1, 2, 4, 8, 'SMCD0006');
INSERT INTO public.mapper_confg (id, version, transaction_type_config_id, abap_type_id, calm_field_id, abap_key) VALUES (19, 1, 2, 4, 9, 'SDCR0002');


--
-- TOC entry 4215 (class 0 OID 18075)
-- Dependencies: 217
-- Data for Name: update_type; Type: TABLE DATA; Schema: public; Owner: dbo
--

INSERT INTO public.update_type (id, version, technical_name, description) VALUES (2, 1, 'C2A', 'Cloud to ABAP');
INSERT INTO public.update_type (id, version, technical_name, description) VALUES (1, 1, 'A2C', 'ABAP to Cloud');


--
-- TOC entry 4221 (class 0 OID 18152)
-- Dependencies: 223
-- Data for Name: status_config; Type: TABLE DATA; Schema: public; Owner: dbo
--

INSERT INTO public.status_config (id, version, transaction_type_config_id, abap_status, calm_status_id, update_type_id) VALUES (1, 1, 1, 'E0001', 3, 2);
INSERT INTO public.status_config (id, version, transaction_type_config_id, abap_status, calm_status_id, update_type_id) VALUES (2, 1, 2, 'E0001', 3, 2);
INSERT INTO public.status_config (id, version, transaction_type_config_id, abap_status, calm_status_id, update_type_id) VALUES (4, 1, 2, 'E0006', 5, 2);
INSERT INTO public.status_config (id, version, transaction_type_config_id, abap_status, calm_status_id, update_type_id) VALUES (3, 1, 1, 'E0012', 5, 2);
INSERT INTO public.status_config (id, version, transaction_type_config_id, abap_status, calm_status_id, update_type_id) VALUES (7, 1, 2, 'E0005', 4, 1);
INSERT INTO public.status_config (id, version, transaction_type_config_id, abap_status, calm_status_id, update_type_id) VALUES (6, 1, 1, 'E0011', 4, 1);


--
-- TOC entry 4236 (class 0 OID 0)
-- Dependencies: 224
-- Name: abap_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dbo
--

SELECT pg_catalog.setval('public.abap_type_id_seq', 6, false);


--
-- TOC entry 4237 (class 0 OID 0)
-- Dependencies: 225
-- Name: bp_mapping_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dbo
--

SELECT pg_catalog.setval('public.bp_mapping_id_seq', 34, false);


--
-- TOC entry 4238 (class 0 OID 0)
-- Dependencies: 226
-- Name: calm_field_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dbo
--

SELECT pg_catalog.setval('public.calm_field_id_seq', 13, false);


--
-- TOC entry 4239 (class 0 OID 0)
-- Dependencies: 227
-- Name: calm_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dbo
--

SELECT pg_catalog.setval('public.calm_status_id_seq', 9, false);


--
-- TOC entry 4240 (class 0 OID 0)
-- Dependencies: 228
-- Name: internal_mapper_config_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dbo
--

SELECT pg_catalog.setval('public.internal_mapper_config_id_seq', 1, false);


--
-- TOC entry 4241 (class 0 OID 0)
-- Dependencies: 229
-- Name: mapper_confg_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dbo
--

SELECT pg_catalog.setval('public.mapper_confg_id_seq', 4, false);


--
-- TOC entry 4242 (class 0 OID 0)
-- Dependencies: 230
-- Name: status_config_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dbo
--

SELECT pg_catalog.setval('public.status_config_id_seq', 1, false);


--
-- TOC entry 4243 (class 0 OID 0)
-- Dependencies: 231
-- Name: transaction_type_config_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dbo
--

SELECT pg_catalog.setval('public.transaction_type_config_id_seq', 4, false);


--
-- TOC entry 4244 (class 0 OID 0)
-- Dependencies: 232
-- Name: update_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dbo
--

SELECT pg_catalog.setval('public.update_type_id_seq', 4, false);


-- Completed on 2023-07-11 11:02:06

--
-- PostgreSQL database dump complete
--

