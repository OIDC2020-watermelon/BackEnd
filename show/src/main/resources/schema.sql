drop table if exists artist cascade;
drop table if exists batch_job_execution cascade;
drop table if exists batch_job_execution_context cascade;
drop table if exists batch_job_execution_params cascade;
drop table if exists batch_job_instance cascade;
drop table if exists batch_step_execution cascade;
drop table if exists batch_step_execution_context cascade;
drop table if exists career cascade;
drop table if exists comment cascade;
drop table if exists place cascade;
drop table if exists product cascade;
drop table if exists product_artist cascade;
drop table if exists promotion cascade;
drop table if exists theme cascade;
drop sequence if exists batch_job_execution_seq cascade;
drop sequence if exists batch_job_seq cascade;
drop sequence if exists batch_step_execution_seq cascade;

CREATE TABLE BATCH_JOB_INSTANCE  (
	JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT ,
	JOB_NAME VARCHAR(100) NOT NULL,
	JOB_KEY VARCHAR(32) NOT NULL,
	constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
) ;

CREATE TABLE BATCH_JOB_EXECUTION  (
	JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT  ,
	JOB_INSTANCE_ID BIGINT NOT NULL,
	CREATE_TIME TIMESTAMP NOT NULL,
	START_TIME TIMESTAMP DEFAULT NULL ,
	END_TIME TIMESTAMP DEFAULT NULL ,
	STATUS VARCHAR(10) ,
	EXIT_CODE VARCHAR(2500) ,
	EXIT_MESSAGE VARCHAR(2500) ,
	LAST_UPDATED TIMESTAMP,
	JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL,
	constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
	references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ;

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
	JOB_EXECUTION_ID BIGINT NOT NULL ,
	TYPE_CD VARCHAR(6) NOT NULL ,
	KEY_NAME VARCHAR(100) NOT NULL ,
	STRING_VAL VARCHAR(250) ,
	DATE_VAL TIMESTAMP DEFAULT NULL ,
	LONG_VAL BIGINT ,
	DOUBLE_VAL DOUBLE PRECISION ,
	IDENTIFYING CHAR(1) NOT NULL ,
	constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ;

CREATE TABLE BATCH_STEP_EXECUTION  (
	STEP_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT NOT NULL,
	STEP_NAME VARCHAR(100) NOT NULL,
	JOB_EXECUTION_ID BIGINT NOT NULL,
	START_TIME TIMESTAMP NOT NULL ,
	END_TIME TIMESTAMP DEFAULT NULL ,
	STATUS VARCHAR(10) ,
	COMMIT_COUNT BIGINT ,
	READ_COUNT BIGINT ,
	FILTER_COUNT BIGINT ,
	WRITE_COUNT BIGINT ,
	READ_SKIP_COUNT BIGINT ,
	WRITE_SKIP_COUNT BIGINT ,
	PROCESS_SKIP_COUNT BIGINT ,
	ROLLBACK_COUNT BIGINT ,
	EXIT_CODE VARCHAR(2500) ,
	EXIT_MESSAGE VARCHAR(2500) ,
	LAST_UPDATED TIMESTAMP,
	constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ;

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
	STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT TEXT ,
	constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
	references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ;

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
	JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT TEXT ,
	constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ;

CREATE SEQUENCE BATCH_STEP_EXECUTION_SEQ MAXVALUE 9223372036854775807 NO CYCLE;
CREATE SEQUENCE BATCH_JOB_EXECUTION_SEQ MAXVALUE 9223372036854775807 NO CYCLE;
CREATE SEQUENCE BATCH_JOB_SEQ MAXVALUE 9223372036854775807 NO CYCLE;


create table place
(
    id                bigserial not null
        constraint place_pkey
            primary key,
    address           varchar(255),
    description       text,
    homepage          varchar(2100),
    img_url           varchar(8200),
    name              varchar(255),
    telephone         varchar(20),
    thumbnail_img_url varchar(8200)
);

create table product
(
    id                 bigserial not null
        constraint product_pkey
            primary key,
    category           integer,
    created_date_time  timestamp,
    description        text,
    img_url            varchar(8200),
    modified_date_time timestamp,
    r_rated            integer,
    release_end_time   timestamp,
    release_start_time timestamp,
    running_time       integer,
    thumbnail_img_url  varchar(8200),
    title              varchar(255),
    place_id           bigint
        constraint fkqq95l6h1869asu4r162wld50h
            references place,
    pod                integer,
    serial             uuid
        constraint uk_4fp0iep4apkdxxggya35q11bq
            unique
);

create table artist
(
    id                bigserial not null
        constraint artist_pkey
            primary key,
    birth_date        timestamp,
    debut_date        timestamp,
    description       text,
    facebook_url      varchar(2100),
    height            integer,
    img_url           varchar(8200),
    instagram_url     varchar(2100),
    name              varchar(50),
    occupation        integer,
    thumbnail_img_url varchar(8200),
    twitter_url       varchar(2100),
    weight            integer
);

create table career
(
    id          bigserial not null
        constraint career_pkey
            primary key,
    date        timestamp,
    description text,
    artist_id   bigint
        constraint fkim3ih35792xp7we92it483yjq
            references artist
);

create table comment
(
    id                 bigserial not null
        constraint comment_pkey
            primary key,
    content            text,
    created_date_time  timestamp,
    modified_date_time timestamp,
    type               integer,
    user_id            bigint,
    product_id         bigint
        constraint fkm1rmnfcvq5mk26li4lit88pc5
            references product
);



create table product_artist
(
    artist_id  bigint not null
        constraint fkeoi26sapx9sl59g3429mxgv7a
            references artist,
    product_id bigint not null
        constraint fk3hr66oc2gsdpbvwvljq5gwbh2
            references product
);

create table promotion
(
    id                bigserial not null
        constraint promotion_pkey
            primary key,
    promotion_img_url varchar(8200),
    product_id        bigint
        constraint fkiapjua98u0ltg8nuw9s84iyme
            references product
);

create table theme
(
    id         bigserial not null
        constraint theme_pkey
            primary key,
    type       integer,
    product_id bigint
        constraint fktasb14xgl3iq7waflqvmec8he
            references product
);

alter table artist
    owner to postgres;

alter table career
    owner to postgres;
alter table comment
    owner to postgres;

alter table place
    owner to postgres;

alter table product
    owner to postgres;

alter table product_artist
    owner to postgres;

alter table promotion
    owner to postgres;

alter table theme
    owner to postgres;
