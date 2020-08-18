truncate userdb.user;
truncate userdb.user_roles;

-- user테이블 데이터 추가
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (13, null, '19990909', 'male', '호우', '{bcrypt}$2a$10$4ZmuGTUCoBPWISSH4CVSiuMxRiwymv21WYnCBGMpQ92GvzbO7/Syy', '01024242424', null, 'water2@naver.com');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (12, null, '19990909', 'male', '수박', '{bcrypt}$2a$10$xiwGXuFo1gPMXRuNhnHYm.2kQRdMsDmYKIohhoxBba4chWM83afuu', '01088881111', null, 'water1@naver.com');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (14, '20~29', '', 'female', '화목', null, '01090902929', 'kakao', 'hwamoc@kakao.com');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (15, 'null', null, 'null', '김화목', null, null, 'naver', 'hwamok222@naver.com');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (16, 'null', null, 'null', '김화목', null, null, 'naver', 'hmnara0719@naver.com');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (18, null, null, null, '안뇽', 'hi', null, null, 'hi@naver.com');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (22, null, '19750807', 'female', '수박킹왕짱', '{bcrypt}$2a$10$MApnC/lzLz7qplKGMDCG3uSl7RY0ZX/Fun8s7Te23EnBTnF/tYV0q', '01098762323', null, 'watermelon2@naver.com');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (24, 'null', null, 'null', '김화목', null, null, 'naver', 'isingbeauty@naver.com');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (32, 'null', '940318', 'male', '조혜형', null, '01043650214', 'naver', 'dream02143@hanmail.net');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (26, 'null', '1995.01.18', 'female', '정진리', null, '010-2842-6364', 'naver', 'jjo151@naver.com');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (27, null, null, null, '관리자', '{bcrypt}$2a$10$e9R3XkwF2y5NE2h/sU0uWet2QkEpSxRx0CVhOajpg3CDJedQCHAeW', null, null, 'watermelon@wm.com');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (28, null, null, null, '관리자', '{bcrypt}$2a$10$o/aQ1BSm3yxetZQQiPnJU.9phVS3arsP/i2auAWSAmQ.zhPlzS.2.', null, null, 'watermelon1@wm.com');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (29, null, null, null, '관리자', '{bcrypt}$2a$10$9ywdIvsmtWwUVTjHwL0sAeSbnak6Fb2zACJbJDVUMnfvYSwMdfKk2', null, null, 'watermelon2@wm.com');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (30, null, null, null, '관리자', '{bcrypt}$2a$10$WPz3yVag5BQWnBpSbBPKF.WAaMcT4Ns1s2QBbo2awLqWkZ.R5EKky', null, null, 'watermelon3@wm.com');
INSERT INTO userdb.user (id, age_range, date_of_birth, gender, name, password, phone_no, provider, uid) VALUES (31, null, null, null, '관리자', '{bcrypt}$2a$10$aqPSBfShfriwLGXBLMWkCejbviF2kfjpHMurSMvJCO4RwDPaxDBu.', null, null, 'watermelon4@wm.com');

-- user_roles테이블 데이터 추가
INSERT INTO userdb.user_roles (user_id, roles) VALUES (2, 'ROLE_USER');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (12, 'ROLE_USER');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (13, 'ROLE_USER');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (14, 'ROLE_USER');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (15, 'ROLE_USER');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (16, 'ROLE_USER');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (18, 'ROLE_USER');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (22, 'ROLE_USER');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (24, 'ROLE_USER');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (32, 'ROLE_USER');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (26, 'ROLE_USER');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (27, 'ROLE_ADMIN');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (28, 'ROLE_ADMIN');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (29, 'ROLE_ADMIN');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (30, 'ROLE_ADMIN');
INSERT INTO userdb.user_roles (user_id, roles) VALUES (31, 'ROLE_ADMIN');