--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-06-01 05:15:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 182 (class 1259 OID 16402)
-- Name: institution; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE institution (
    institution_id bigint NOT NULL,
    created_ts timestamp without time zone NOT NULL,
    created_user character varying(255) NOT NULL,
    updated_ts timestamp without time zone,
    updated_user character varying(255),
    home_url character varying(256),
    last_synced_ts timestamp without time zone,
    phone character varying(256),
    supported_institution_id bigint NOT NULL
);


ALTER TABLE institution OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 16410)
-- Name: institution_field; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE institution_field (
    institution_field_id bigint NOT NULL,
    created_ts timestamp without time zone NOT NULL,
    created_user character varying(255) NOT NULL,
    updated_ts timestamp without time zone,
    updated_user character varying(255),
    display_name character varying(256),
    external_id character varying(255) NOT NULL,
    instruction character varying(2056),
    is_display boolean NOT NULL,
    is_masked boolean NOT NULL,
    name character varying(256) NOT NULL,
    field_order integer,
    validation_maximum_length integer,
    validation_minimum_length integer,
    value character varying(256),
    institution_id bigint NOT NULL
);


ALTER TABLE institution_field OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16508)
-- Name: supported_institution; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE supported_institution (
    supported_institution_id bigint NOT NULL,
    created_ts timestamp without time zone NOT NULL,
    created_user character varying(255) NOT NULL,
    updated_ts timestamp without time zone,
    updated_user character varying(255),
    aggregator_name character varying(255) NOT NULL,
    aggregator_qualifier character varying(255) NOT NULL,
    display_name character varying(256) NOT NULL,
    external_id character varying(50) NOT NULL,
    logo_code character varying(20) NOT NULL,
    name character varying(256) NOT NULL,
    priority smallint NOT NULL,
    supports_test_mode boolean NOT NULL,
    type character varying(256) NOT NULL
);


ALTER TABLE supported_institution OWNER TO postgres;

--
-- TOC entry 2174 (class 0 OID 16402)
-- Dependencies: 182
-- Data for Name: institution; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (17, '2016-05-24 21:38:52.755', 'sysscheduler', NULL, NULL, 'https://www.pnc.com/webapp/unsec/Homepage.do?siteArea=/PNCCorp/PNC/Home/Personal', '2016-05-24 21:38:52.755', NULL, 7);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (18, '2016-05-24 21:38:52.757', 'sysscheduler', NULL, NULL, 'https://www.usaa.com/inet/ent_logon/Logon', '2016-05-24 21:38:52.757', NULL, 6);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (16, '2016-05-24 21:38:52.72', 'sysscheduler', NULL, NULL, 'https://www.wellsfargo.com/', '2016-05-24 21:38:52.72', NULL, 5);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (30, '2016-05-24 21:38:52.773', 'sysscheduler', NULL, NULL, 'https://home.capitalone360.com/', '2016-05-24 21:38:52.773', NULL, 8);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (55, '2016-05-24 21:38:54.85', 'sysscheduler', NULL, NULL, 'http://www.usbank.com/index.html', '2016-05-24 21:38:54.85', NULL, 9);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (59, '2016-05-24 21:38:54.876', 'sysscheduler', NULL, NULL, 'http://www.intuit.com', '2016-05-24 21:38:54.876', NULL, 10);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (72, '2016-05-24 21:38:57.931', 'sysscheduler', NULL, NULL, 'https://www.salliemae.com/', '2016-05-24 21:38:57.931', NULL, 22);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (80, '2016-05-24 21:38:59.261', 'sysscheduler', NULL, NULL, 'https://www.mygreatlakes.org/', '2016-05-24 21:38:59.261', NULL, 25);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (19, '2016-05-24 21:38:52.768', 'sysscheduler', NULL, NULL, 'https://www.bankofamerica.com/', '2016-05-24 21:38:52.768', NULL, 3);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (49, '2016-05-24 21:38:53.101', 'sysscheduler', NULL, NULL, 'http://www.bbvacompass.com/', '2016-05-24 21:38:53.101', NULL, 4);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (52, '2016-05-24 21:38:53.292', 'sysscheduler', NULL, NULL, 'https://www.americanexpress.com/?location=globalsplash', '2016-05-24 21:38:53.292', NULL, 2);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (62, '2016-05-24 21:38:56.737', 'sysscheduler', NULL, NULL, 'https://www.chase.com/', '2016-05-24 21:38:56.737', NULL, 1);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (68, '2016-05-24 21:38:57.891', 'sysscheduler', NULL, NULL, 'https://www.mohela.com/default.aspx', '2016-05-24 21:38:57.891', NULL, 21);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (77, '2016-05-24 21:38:58.275', 'sysscheduler', NULL, NULL, 'https://www.discover.com/', '2016-05-24 21:38:58.275', NULL, 23);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (84, '2016-05-24 21:38:59.391', 'sysscheduler', NULL, NULL, 'http://www.nelnet.com/', '2016-05-24 21:38:59.391', NULL, 24);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (88, '2016-05-24 21:38:59.453', 'sysscheduler', NULL, NULL, 'https://studentloan.citibank.com/s/slcsite/manage_your_account.asp', '2016-05-24 21:38:59.453', NULL, 26);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (155, '2016-05-28 12:56:25.513', 'sysscheduler', NULL, NULL, 'http://www.chase.com', '2016-05-28 12:56:25.513', NULL, 11);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (158, '2016-05-28 12:56:27.541', 'sysscheduler', NULL, NULL, 'http://www.americanexpress.com/homepage/personal.shtml', '2016-05-28 12:56:27.541', NULL, 12);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (178, '2016-05-28 12:56:30.925', 'sysscheduler', NULL, NULL, 'https://www.bankofamerica.com/', '2016-05-28 12:56:30.925', NULL, 13);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (193, '2016-05-28 12:56:31.707', 'sysscheduler', NULL, NULL, 'http://www.wellsfargo.com', '2016-05-28 12:56:31.707', NULL, 15);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (196, '2016-05-28 12:56:31.725', 'sysscheduler', NULL, NULL, 'https://www.usaa.com/inet/ent_logon/Logon', '2016-05-28 12:56:31.725', NULL, 16);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (200, '2016-05-28 12:56:32.1', 'sysscheduler', NULL, NULL, 'http://www.pnc.com/', '2016-05-28 12:56:32.1', NULL, 17);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (207, '2016-05-28 12:56:34.44', 'sysscheduler', NULL, NULL, 'http://www.compassweb.com/index.cfm', '2016-05-28 12:56:34.44', NULL, 14);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (210, '2016-05-28 12:56:35.881', 'sysscheduler', NULL, NULL, 'http://www.mohela.com', '2016-05-28 12:56:35.881', NULL, 31);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (213, '2016-05-28 12:56:36.311', 'sysscheduler', NULL, NULL, 'http://www.usbank.com/', '2016-05-28 12:56:36.311', NULL, 19);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (218, '2016-05-28 12:56:37.559', 'sysscheduler', NULL, NULL, 'https://www.discoverstudentloans.com', '2016-05-28 12:56:37.559', NULL, 33);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (221, '2016-05-28 12:56:37.819', 'sysscheduler', NULL, NULL, 'https://secure.nelnet.net/', '2016-05-28 12:56:37.819', NULL, 34);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (224, '2016-05-28 12:56:37.926', 'sysscheduler', NULL, NULL, 'https://home.capitalone360.com/', '2016-05-28 12:56:37.926', NULL, 18);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (229, '2016-05-28 12:56:38.294', 'sysscheduler', NULL, NULL, 'http://www.mygreatlakes.com', '2016-05-28 12:56:38.294', NULL, 35);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (232, '2016-05-28 12:56:38.936', 'sysscheduler', NULL, NULL, 'http://www.studentloan.com/', '2016-05-28 12:56:38.936', NULL, 36);
INSERT INTO institution (institution_id, created_ts, created_user, updated_ts, updated_user, home_url, last_synced_ts, phone, supported_institution_id) VALUES (235, '2016-05-28 12:56:39.05', 'sysscheduler', NULL, NULL, 'http://www.finbank.com', '2016-05-28 12:56:39.05', NULL, 37);


--
-- TOC entry 2175 (class 0 OID 16410)
-- Dependencies: 183
-- Data for Name: institution_field; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (50, '2016-05-24 21:38:53.101', 'sysscheduler', NULL, NULL, 'Password', 'Password', 'No more than 12 characters long', true, true, 'Password', 2, 12, 1, NULL, 49);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (42, '2016-05-24 21:38:52.755', 'sysscheduler', NULL, NULL, 'User ID', 'userId', NULL, true, false, 'userId', 1, NULL, NULL, NULL, 17);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (35, '2016-05-24 21:38:52.757', 'sysscheduler', NULL, NULL, 'Online ID', 'j_username', NULL, true, false, 'j_username', 1, 20, 1, NULL, 18);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (51, '2016-05-24 21:38:53.101', 'sysscheduler', NULL, NULL, 'User ID or Nickname', 'UserName', NULL, true, false, 'UserName', 1, 20, 1, NULL, 49);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (46, '2016-05-24 21:38:52.757', 'sysscheduler', NULL, NULL, 'TAX_AGGR_ENABLED', 'TAX_AGGR_ENABLED', 'TAX_AGGR_ENABLED', true, false, 'TAX_AGGR_ENABLED', 4, 20, 1, 'FALSE', 18);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (44, '2016-05-24 21:38:52.755', 'sysscheduler', NULL, NULL, 'Password', 'password', NULL, true, true, 'password', 2, NULL, NULL, NULL, 17);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (47, '2016-05-24 21:38:52.757', 'sysscheduler', NULL, NULL, 'Password', 'j_password', NULL, true, true, 'j_password', 2, 12, 1, NULL, 18);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (48, '2016-05-24 21:38:52.757', 'sysscheduler', NULL, NULL, 'PIN', 'cppindatacontainer.verifypin', NULL, true, true, 'cppindatacontainer.verifypin', 3, 4, 4, NULL, 18);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (60, '2016-05-24 21:38:54.876', 'sysscheduler', NULL, NULL, 'Banking Userid', 'Banking Userid', 'Enter banking userid (demo)', true, false, 'Banking Userid', 1, 20, 1, NULL, 59);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (61, '2016-05-24 21:38:54.876', 'sysscheduler', NULL, NULL, 'Banking Password', 'Banking Password', 'Enter banking password (go)', true, true, 'Banking Password', 2, 20, 1, NULL, 59);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (73, '2016-05-24 21:38:57.931', 'sysscheduler', NULL, NULL, 'Social Security Number or Account number (no space)', 'SSN', NULL, true, true, 'SSN', 3, 10, 1, NULL, 72);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (74, '2016-05-24 21:38:57.931', 'sysscheduler', NULL, NULL, 'Date of Birth(MMDDYYYY - no space)', 'DOB', NULL, true, false, 'DOB', 4, 8, 1, NULL, 72);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (75, '2016-05-24 21:38:57.931', 'sysscheduler', NULL, NULL, 'User ID', 'Username', NULL, true, false, 'Username', 1, NULL, NULL, NULL, 72);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (76, '2016-05-24 21:38:57.931', 'sysscheduler', NULL, NULL, 'Password', 'Password', NULL, true, true, 'Password', 2, NULL, NULL, NULL, 72);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (85, '2016-05-24 21:38:59.391', 'sysscheduler', NULL, NULL, NULL, 'DELETED_5640003', NULL, true, false, 'DELETED_5640003', 3, NULL, NULL, 'https://mma.nelnet.net/Login.aspx', 84);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (86, '2016-05-24 21:38:59.391', 'sysscheduler', NULL, NULL, 'Password', 'password', NULL, true, true, 'password', 2, NULL, NULL, NULL, 84);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (87, '2016-05-24 21:38:59.391', 'sysscheduler', NULL, NULL, 'Username', 'username', NULL, true, false, 'username', 1, NULL, NULL, NULL, 84);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (31, '2016-05-24 21:38:52.773', 'sysscheduler', NULL, NULL, NULL, 'OFX_BROKERID', NULL, true, false, 'OFX_BROKERID', 7, 20, 1, '031176110', 30);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (32, '2016-05-24 21:38:52.773', 'sysscheduler', NULL, NULL, NULL, 'OFX_ORG', NULL, true, false, 'OFX_ORG', 5, 20, 1, 'ING Direct', 30);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (33, '2016-05-24 21:38:52.773', 'sysscheduler', NULL, NULL, NULL, 'OFX_APPVER', NULL, true, false, 'OFX_APPVER', 9, 20, 1, '1900', 30);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (34, '2016-05-24 21:38:52.773', 'sysscheduler', NULL, NULL, 'ERROR MESSAGE FOR CREDENTIAL REQUIRED', 'REQUIRED_CRED_MESSAGE', 'ERROR MESSAGE FOR CREDENTIAL REQUIRED', true, false, 'REQUIRED_CRED_MESSAGE', 11, 20, 1, 'Customer needs an Access Code', 30);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (36, '2016-05-24 21:38:52.773', 'sysscheduler', NULL, NULL, NULL, 'SSL_SERVERCERT_VALIDATION', NULL, true, false, 'SSL_SERVERCERT_VALIDATION', 8, 20, 1, 'false', 30);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (37, '2016-05-24 21:38:52.773', 'sysscheduler', NULL, NULL, NULL, 'OFX_OFXVER', NULL, true, false, 'OFX_OFXVER', 4, 20, 1, '102', 30);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (38, '2016-05-24 21:38:52.773', 'sysscheduler', NULL, NULL, 'Username (Saver ID) or Customer Number', 'USERID', NULL, true, false, 'USERID', 1, 32, 1, NULL, 30);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (39, '2016-05-24 21:38:52.773', 'sysscheduler', NULL, NULL, NULL, 'OFX_URL', NULL, true, false, 'OFX_URL', 3, 20, 1, 'https://ofx.capitalone360.com/OFX/ofx.html', 30);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (40, '2016-05-24 21:38:52.773', 'sysscheduler', NULL, NULL, NULL, 'OFX_BANKID', NULL, true, false, 'OFX_BANKID', 12, NULL, NULL, '031176110', 30);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (41, '2016-05-24 21:38:52.773', 'sysscheduler', NULL, NULL, NULL, 'OFX_APPID', NULL, true, false, 'OFX_APPID', 10, 20, 1, 'INTU', 30);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (43, '2016-05-24 21:38:52.773', 'sysscheduler', NULL, NULL, 'Access Code (12 characters)', 'PASSWORD', NULL, true, true, 'PASSWORD', 2, 20, 4, NULL, 30);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (45, '2016-05-24 21:38:52.773', 'sysscheduler', NULL, NULL, NULL, 'OFX_FID', NULL, true, false, 'OFX_FID', 6, 20, 1, '031176110', 30);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (20, '2016-05-24 21:38:52.72', 'sysscheduler', NULL, NULL, 'Username', 'userid', NULL, true, false, 'userid', 1, 14, NULL, NULL, 16);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (22, '2016-05-24 21:38:52.768', 'sysscheduler', NULL, NULL, NULL, 'TOWERADDL_TAXFORMS', NULL, true, false, 'TOWERADDL_TAXFORMS', 4, NULL, NULL, 'Y', 19);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (23, '2016-05-24 21:38:52.768', 'sysscheduler', NULL, NULL, NULL, 'TOWERACCOUNT_THRESHOLD', NULL, true, false, 'TOWERACCOUNT_THRESHOLD', 3, NULL, NULL, '-1', 19);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (21, '2016-05-24 21:38:52.72', 'sysscheduler', NULL, NULL, 'Password', 'password', NULL, true, true, 'password', 2, 14, NULL, NULL, 16);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (24, '2016-05-24 21:38:52.768', 'sysscheduler', NULL, NULL, 'Online ID', 'onlineID', NULL, true, false, 'onlineID', 1, 32, NULL, NULL, 19);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (27, '2016-05-24 21:38:52.768', 'sysscheduler', NULL, NULL, 'Passcode', 'passcode', NULL, true, true, 'passcode', 2, 20, NULL, NULL, 19);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (28, '2016-05-24 21:38:52.768', 'sysscheduler', NULL, NULL, NULL, 'TAX_AGGR_ENABLED', NULL, true, false, 'TAX_AGGR_ENABLED', 20, NULL, NULL, 'TRUE', 19);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (25, '2016-05-24 21:38:52.72', 'sysscheduler', NULL, NULL, NULL, 'TOWERACCOUNT_THRESHOLD', NULL, true, false, 'TOWERACCOUNT_THRESHOLD', 4, NULL, NULL, '-1', 16);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (26, '2016-05-24 21:38:52.72', 'sysscheduler', NULL, NULL, 'TAX_AGGR_ENABLED', 'TAX_AGGR_ENABLED', 'TAX_AGGR_ENABLED', true, false, 'TAX_AGGR_ENABLED', 20, NULL, NULL, 'TRUE', 16);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (29, '2016-05-24 21:38:52.72', 'sysscheduler', NULL, NULL, NULL, 'TOWERADDL_TAXFORMS', NULL, true, false, 'TOWERADDL_TAXFORMS', 3, NULL, NULL, 'Y', 16);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (53, '2016-05-24 21:38:53.292', 'sysscheduler', NULL, NULL, 'PASSWORD', 'Password', NULL, true, true, 'Password', 2, 20, NULL, NULL, 52);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (54, '2016-05-24 21:38:53.292', 'sysscheduler', NULL, NULL, 'USER ID', 'UserID', NULL, true, false, 'UserID', 1, 32, NULL, NULL, 52);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (56, '2016-05-24 21:38:54.85', 'sysscheduler', NULL, NULL, 'Password', 'PSWD', NULL, true, true, 'PSWD', 2, NULL, NULL, NULL, 55);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (57, '2016-05-24 21:38:54.85', 'sysscheduler', NULL, NULL, NULL, 'TAX_AGGR_ENABLED', NULL, true, false, 'TAX_AGGR_ENABLED', 20, NULL, NULL, 'TRUE', 55);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (58, '2016-05-24 21:38:54.85', 'sysscheduler', NULL, NULL, 'Personal ID', 'USERID', NULL, true, false, 'USERID', 1, 22, 7, NULL, 55);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (63, '2016-05-24 21:38:56.737', 'sysscheduler', NULL, NULL, 'User ID', 'usr_name', NULL, true, false, 'usr_name', 1, NULL, NULL, NULL, 62);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (64, '2016-05-24 21:38:56.737', 'sysscheduler', NULL, NULL, 'Password', 'usr_password', NULL, true, true, 'usr_password', 2, NULL, NULL, NULL, 62);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (65, '2016-05-24 21:38:56.737', 'sysscheduler', NULL, NULL, NULL, 'TOWERACCOUNT_THRESHOLD', NULL, true, false, 'TOWERACCOUNT_THRESHOLD', 3, NULL, NULL, '-1', 62);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (66, '2016-05-24 21:38:56.737', 'sysscheduler', NULL, NULL, 'TAX_AGGR_ENABLED', 'TAX_AGGR_ENABLED', 'TAX_AGGR_ENABLED', true, false, 'TAX_AGGR_ENABLED', 20, NULL, NULL, 'TRUE', 62);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (67, '2016-05-24 21:38:56.737', 'sysscheduler', NULL, NULL, NULL, 'TOWERADDL_TAXFORMS', NULL, true, false, 'TOWERADDL_TAXFORMS', 4, NULL, NULL, 'Y', 62);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (69, '2016-05-24 21:38:57.891', 'sysscheduler', NULL, NULL, 'User Name', 'ctl00$cphMainForm$txtLoginID', NULL, true, false, 'ctl00$cphMainForm$txtLoginID', 1, 50, 8, NULL, 68);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (70, '2016-05-24 21:38:57.891', 'sysscheduler', NULL, NULL, 'Password', 'ctl00$cphMainForm$txtPassword', NULL, true, true, 'ctl00$cphMainForm$txtPassword', 2, 25, 8, NULL, 68);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (71, '2016-05-24 21:38:57.891', 'sysscheduler', NULL, NULL, NULL, 'TOWERWEBSITE', NULL, true, false, 'TOWERWEBSITE', 3, NULL, NULL, 'https://www.mohela.com/DL/secure/account/loginStep1.aspx', 68);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (78, '2016-05-24 21:38:58.275', 'sysscheduler', NULL, NULL, 'User ID', 'ctl00$ctl00$ctl00$siteBodyPlaceHolder$uB$b$usernameTextBox', NULL, true, false, 'ctl00$ctl00$ctl00$siteBodyPlaceHolder$uB$b$usernameTextBox', 1, NULL, NULL, NULL, 77);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (79, '2016-05-24 21:38:58.275', 'sysscheduler', NULL, NULL, 'Password', 'ctl00$ctl00$ctl00$siteBodyPlaceHolder$uB$b$passwordTextBox', NULL, true, true, 'ctl00$ctl00$ctl00$siteBodyPlaceHolder$uB$b$passwordTextBox', 2, NULL, NULL, NULL, 77);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (81, '2016-05-24 21:38:59.261', 'sysscheduler', NULL, NULL, 'Password', 'txtPassword', NULL, true, true, 'txtPassword', 2, 50, 1, NULL, 80);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (82, '2016-05-24 21:38:59.261', 'sysscheduler', NULL, NULL, 'PIN', 'DELETED_909100403', NULL, true, true, 'DELETED_909100403', 3, NULL, NULL, NULL, 80);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (83, '2016-05-24 21:38:59.261', 'sysscheduler', NULL, NULL, 'User ID', 'txtSsn', NULL, true, false, 'txtSsn', 1, 50, 5, NULL, 80);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (89, '2016-05-24 21:38:59.453', 'sysscheduler', NULL, NULL, 'Username', 'UserName', NULL, true, false, 'UserName', 1, 20, NULL, NULL, 88);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (90, '2016-05-24 21:38:59.453', 'sysscheduler', NULL, NULL, 'Password', 'Password', NULL, true, true, 'Password', 2, 8, NULL, NULL, 88);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (156, '2016-05-28 12:56:25.513', 'sysscheduler', NULL, NULL, 'User ID', '13278001', NULL, true, false, 'usr_name', NULL, NULL, NULL, NULL, 155);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (157, '2016-05-28 12:56:25.513', 'sysscheduler', NULL, NULL, 'Password', '13278002', NULL, true, true, 'usr_password', NULL, NULL, NULL, NULL, 155);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (159, '2016-05-28 12:56:27.541', 'sysscheduler', NULL, NULL, 'USER ID', '1002001', 'Please see instructions above', true, false, 'UserID', NULL, NULL, NULL, NULL, 158);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (160, '2016-05-28 12:56:27.541', 'sysscheduler', NULL, NULL, 'PASSWORD', '1002002', 'Please see instructions above', true, true, 'Password', NULL, NULL, NULL, NULL, 158);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (179, '2016-05-28 12:56:30.925', 'sysscheduler', NULL, NULL, 'Online ID', '14007001', NULL, true, false, 'onlineID', NULL, NULL, NULL, NULL, 178);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (180, '2016-05-28 12:56:30.925', 'sysscheduler', NULL, NULL, 'Passcode', '14007002', NULL, true, true, 'passcode', NULL, 20, NULL, NULL, 178);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (194, '2016-05-28 12:56:31.707', 'sysscheduler', NULL, NULL, 'Username', '31001', NULL, true, false, 'userid', NULL, 14, NULL, NULL, 193);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (195, '2016-05-28 12:56:31.707', 'sysscheduler', NULL, NULL, 'Password', '31002', NULL, true, true, 'password', NULL, 14, NULL, NULL, 193);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (197, '2016-05-28 12:56:31.725', 'sysscheduler', NULL, NULL, 'Online ID', '2875001', NULL, true, false, 'j_username', NULL, 20, NULL, NULL, 196);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (198, '2016-05-28 12:56:31.725', 'sysscheduler', NULL, NULL, 'Password', '2875002', NULL, true, true, 'j_password', NULL, 12, NULL, NULL, 196);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (199, '2016-05-28 12:56:31.725', 'sysscheduler', NULL, NULL, 'PIN', '2875003', NULL, true, true, 'cppindatacontainer.verifypin', NULL, 4, NULL, NULL, 196);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (201, '2016-05-28 12:56:32.1', 'sysscheduler', NULL, NULL, 'User ID', '2866001', NULL, true, false, 'userId', NULL, NULL, NULL, NULL, 200);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (202, '2016-05-28 12:56:32.1', 'sysscheduler', NULL, NULL, 'Password', '2866002', NULL, true, true, 'password', NULL, 20, NULL, NULL, 200);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (208, '2016-05-28 12:56:34.44', 'sysscheduler', NULL, NULL, 'User ID or Nickname', '3144001', NULL, true, false, 'UserName', NULL, NULL, NULL, NULL, 207);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (209, '2016-05-28 12:56:34.44', 'sysscheduler', NULL, NULL, 'Password', '3144002', 'No more than 13 characters long', true, true, 'Password', NULL, 13, NULL, NULL, 207);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (211, '2016-05-28 12:56:35.881', 'sysscheduler', NULL, NULL, 'User ID', '13319001', 'Please see instructions above', true, false, 'ctl00$cphMainForm$txtLoginID', NULL, NULL, NULL, NULL, 210);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (212, '2016-05-28 12:56:35.881', 'sysscheduler', NULL, NULL, 'Password', '13319002', NULL, true, true, 'ctl00$cphMainForm$txtPassword', NULL, NULL, NULL, NULL, 210);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (214, '2016-05-28 12:56:36.311', 'sysscheduler', NULL, NULL, 'Personal ID', '676001', NULL, true, false, 'USERID', NULL, NULL, NULL, NULL, 213);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (215, '2016-05-28 12:56:36.311', 'sysscheduler', NULL, NULL, 'Password', '676002', NULL, true, true, 'PSWD', NULL, NULL, NULL, NULL, 213);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (219, '2016-05-28 12:56:37.559', 'sysscheduler', NULL, NULL, 'User ID', '100844001', NULL, true, false, 'Username', NULL, NULL, NULL, NULL, 218);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (220, '2016-05-28 12:56:37.559', 'sysscheduler', NULL, NULL, 'Password', '100844002', NULL, true, true, 'Password', NULL, NULL, NULL, NULL, 218);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (222, '2016-05-28 12:56:37.819', 'sysscheduler', NULL, NULL, 'Username', '5640001', 'Please see instructions above', true, false, 'ctl00$PageBodyPlaceHolder$UserID', NULL, NULL, NULL, NULL, 221);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (223, '2016-05-28 12:56:37.819', 'sysscheduler', NULL, NULL, 'Password', '5640002', 'Please see instructions above', true, true, 'ctl00$PageBodyPlaceHolder$Pwd', NULL, NULL, NULL, NULL, 221);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (225, '2016-05-28 12:56:37.926', 'sysscheduler', NULL, NULL, 'Username', '3038001', NULL, true, false, 'ACN', NULL, NULL, NULL, NULL, 224);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (226, '2016-05-28 12:56:37.926', 'sysscheduler', NULL, NULL, '12-digit Access Code', '3038002', NULL, true, true, 'PIN', NULL, NULL, NULL, NULL, 224);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (230, '2016-05-28 12:56:38.294', 'sysscheduler', NULL, NULL, 'User ID', '11814001', NULL, true, false, 'txtSsn', NULL, NULL, NULL, NULL, 229);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (231, '2016-05-28 12:56:38.294', 'sysscheduler', NULL, NULL, 'Password', '11814002', NULL, true, true, 'txtPassword', NULL, NULL, NULL, NULL, 229);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (233, '2016-05-28 12:56:38.936', 'sysscheduler', NULL, NULL, 'User Name', '3296001', NULL, true, false, 'UserName', NULL, NULL, NULL, NULL, 232);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (234, '2016-05-28 12:56:38.936', 'sysscheduler', NULL, NULL, 'Password', '3296002', NULL, true, true, 'Password', NULL, 8, NULL, NULL, 232);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (236, '2016-05-28 12:56:39.05', 'sysscheduler', NULL, NULL, 'Banking Userid', '101732001', NULL, true, false, 'Banking Userid', NULL, NULL, NULL, NULL, 235);
INSERT INTO institution_field (institution_field_id, created_ts, created_user, updated_ts, updated_user, display_name, external_id, instruction, is_display, is_masked, name, field_order, validation_maximum_length, validation_minimum_length, value, institution_id) VALUES (237, '2016-05-28 12:56:39.05', 'sysscheduler', NULL, NULL, 'Banking Password', '101732002', NULL, true, true, 'Banking Password', NULL, NULL, NULL, NULL, 235);


--
-- TOC entry 2176 (class 0 OID 16508)
-- Dependencies: 196
-- Data for Name: supported_institution; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (3, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'Bank of America', '14007', 'logo_bofa', 'Bank of America', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (8, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'Capital One', '3038', 'logo_capone', 'Capital One 360', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (4, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'BBVA Compass', '3144', 'logo_bbva', 'BBVA Compass', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (5, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'Wells Fargo', '23284', 'logo_wells', 'Wells Fargo', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (7, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'PNC', '2866', 'logo_pnc', 'PNC Bank', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (6, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'USAA', '2875', 'logo_usaa', 'USAA Bank', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (9, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'US Bank', '676', 'logo_us', 'US Bank', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (11, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'Chase Bank', '13278', 'logo_chase', 'JP Morgan Chase Bank', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (12, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'American Express', '1002', 'logo_amexcc', 'American Express Credit Card', 2, true, 'credit_card');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (13, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'Bank of America', '14007', 'logo_bofa', 'Bank of America', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (15, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'Wells Fargo', '31', 'logo_wells', 'Wells Fargo', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (16, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'USAA', '2875', 'logo_usaa', 'USAA Bank', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (17, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'PNC', '2866', 'logo_pnc', 'PNC Bank', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (14, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'BBVA Compass', '3144', 'logo_bbva', 'BBVA Compass', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (31, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'Mohela', '13319', 'logo_mohela', 'Mohela', 2, true, 'student_loan');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (19, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'US Bank', '676', 'logo_us', 'US Bank', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (32, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'Sallie Mae', '3148', 'logo_sallimae', 'Sallie Mae Loan', 2, true, 'student_loan');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (2, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'American Express', '1002', 'logo_amexcc', 'American Express Credit Card', 1, true, 'credit_card');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (33, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'Discover Loans', '100844', 'logo_discoverloan', 'Discover Student Loans', 2, true, 'student_loan');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (34, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'NelNet', '5640', 'logo_nelnet', 'NelNet Student Loan', 2, true, 'student_loan');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (18, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'Capital One', '3038', 'logo_capone', 'Capital One 360', 2, true, 'banking');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (35, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'Great Lakes Loans', '11814', 'logo_greatlakes', 'Great Lakes Educational Loan Services Inc.', 2, true, 'student_loan');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (36, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'Citibank Loans', '3296', 'logo_citiloan', 'Citibank Student Loan', 2, true, 'student_loan');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (37, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'finicity', 'finicitySao', 'Fin Bank', '101732', 'logo_finbank', 'FinBank', 2, true, 'test');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (10, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'CC Bank', '100000', 'logo_ccbank', 'CC Bank', 1, true, 'test');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (21, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'Mohela', '24382', 'logo_mohela', 'MOHELA - Loan', 1, true, 'student_loan');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (22, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'Sallie Mae', '31246', 'logo_sallimae', 'Sallie Mae Loans', 1, true, 'student_loan');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (23, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'Discover Loans', '26602', 'logo_discoverloan', 'Discover Student Loans', 1, true, 'student_loan');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (25, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'Great Lakes Loans', '11814', 'logo_greatlakes', 'Great Lakes Educational Loan Services Inc.', 1, true, 'student_loan');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (24, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'NelNet', '5640', 'logo_nelnet', 'NelNet Student Loan', 1, true, 'student_loan');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (26, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'Citibank Loans', '3296', 'logo_citiloan', 'Citibank Student Loan', 1, true, 'student_loan');
INSERT INTO supported_institution (supported_institution_id, created_ts, created_user, updated_ts, updated_user, aggregator_name, aggregator_qualifier, display_name, external_id, logo_code, name, priority, supports_test_mode, type) VALUES (1, '2016-05-18 04:46:00.395319', 'sysuser', NULL, NULL, 'intuit', 'intuitCadSao', 'Chase Bank', '13278', 'logo_chase', 'JP Morgan Chase Bank', 2, true, 'banking');


--
-- TOC entry 2049 (class 2606 OID 16417)
-- Name: institution_field_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY institution_field
    ADD CONSTRAINT institution_field_pkey PRIMARY KEY (institution_field_id);


--
-- TOC entry 2047 (class 2606 OID 16409)
-- Name: institution_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY institution
    ADD CONSTRAINT institution_pkey PRIMARY KEY (institution_id);


--
-- TOC entry 2051 (class 2606 OID 16515)
-- Name: supported_institution_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY supported_institution
    ADD CONSTRAINT supported_institution_pkey PRIMARY KEY (supported_institution_id);


--
-- TOC entry 2054 (class 2606 OID 16543)
-- Name: uk_7s34oyfdfiixuofajuc7hvnce; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY supported_institution
    ADD CONSTRAINT uk_7s34oyfdfiixuofajuc7hvnce UNIQUE (external_id, aggregator_name);


--
-- TOC entry 2052 (class 1259 OID 16547)
-- Name: uk_678l99eb73gkiw11j162bqml7; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX uk_678l99eb73gkiw11j162bqml7 ON supported_institution USING btree (aggregator_name);


--
-- TOC entry 2055 (class 1259 OID 16545)
-- Name: uk_ef9g10u8sbutl4cgkg9m28a0c; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX uk_ef9g10u8sbutl4cgkg9m28a0c ON supported_institution USING btree (name);


--
-- TOC entry 2056 (class 1259 OID 16546)
-- Name: uk_j2ps056u31y5j9b2dnnp362kk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX uk_j2ps056u31y5j9b2dnnp362kk ON supported_institution USING btree (external_id);


--
-- TOC entry 2057 (class 1259 OID 16548)
-- Name: uk_t2qpc0533ndpcmjt64fgfirfp; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX uk_t2qpc0533ndpcmjt64fgfirfp ON supported_institution USING btree (type);


--
-- TOC entry 2058 (class 2606 OID 16556)
-- Name: fk_1r2j4rt29xg2pjquw1e55j6rl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY institution
    ADD CONSTRAINT fk_1r2j4rt29xg2pjquw1e55j6rl FOREIGN KEY (supported_institution_id) REFERENCES supported_institution(supported_institution_id);


--
-- TOC entry 2059 (class 2606 OID 16561)
-- Name: fk_ho1dnybe2p9qllkoq2axpxo4f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY institution_field
    ADD CONSTRAINT fk_ho1dnybe2p9qllkoq2axpxo4f FOREIGN KEY (institution_id) REFERENCES institution(institution_id);


-- Completed on 2016-06-01 05:15:39

--
-- PostgreSQL database dump complete
--

