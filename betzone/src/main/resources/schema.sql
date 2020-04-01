CREATE TABLE bets
(
    bet_id          BIGINT      NOT NULL,
    enddate         DATE         NOT NULL,
    description     VARCHAR2(75) NOT NULL,
    startdate       DATE         NOT NULL,
    homeodds        DOUBLE       NOT NULL,
    awayodds        DOUBLE       NOT NULL,
    matchs_match_id BIGINT      NOT NULL
);

ALTER TABLE bets
    ADD CONSTRAINT bets_pk PRIMARY KEY (bet_id);

CREATE TABLE league
(
    league_id       BIGINT      NOT NULL,
    league_name     VARCHAR2(25) NOT NULL,
    sports_sport_id BIGINT      NOT NULL
);

ALTER TABLE league
    ADD CONSTRAINT league_pk PRIMARY KEY (league_id);

CREATE TABLE matchs
(
    match_id     BIGINT NOT NULL,
    home_team_id BIGINT NOT NULL,
    away_team_id BIGINT NOT NULL,
    date_FOR     DATE    NOT NULL,
    sport_id     BIGINT NOT NULL
);

ALTER TABLE matchs
    ADD CONSTRAINT matchs_pk PRIMARY KEY (match_id);

CREATE TABLE matchstat
(
    matchstat_id     BIGINT NOT NULL,
    home_team_result BIGINT NOT NULL,
    away_team_result BIGINT NOT NULL,
    matchs_match_id  BIGINT NOT NULL
);

ALTER TABLE matchstat
    ADD CONSTRAINT matchstat_pk PRIMARY KEY (matchstat_id);

CREATE TABLE sports
(
    sport_id   BIGINT      NOT NULL,
    sport_name VARCHAR2(30) NOT NULL
);

ALTER TABLE sports
    ADD CONSTRAINT sports_pk PRIMARY KEY (sport_id);

CREATE TABLE teams
(
    team_id          BIGINT      NOT NULL,
    team_name        VARCHAR2(25) NOT NULL,
    league_league_id BIGINT      NOT NULL
);

ALTER TABLE teams
    ADD CONSTRAINT teams_pk PRIMARY KEY (team_id);

CREATE TABLE userbet
(
    userbet_id    BIGINT NOT NULL,
    bet_amount    BIGINT NOT NULL,
    timestamp     DATE    NOT NULL,
    users_user_id BIGINT NOT NULL,
    bets_bet_id   BIGINT NOT NULL
);

ALTER TABLE userbet
    ADD CONSTRAINT userbet_pk PRIMARY KEY (userbet_id);

CREATE TABLE userinfo
(
    user_id      BIGINT      NOT NULL,
    email        VARCHAR2(75) NOT NULL,
    sel_password VARCHAR2(150) NOT NULL,
    cellphone    BIGINT      NOT NULL,
    username     VARCHAR2(15) NOT NULL,
    fullname     VARCHAR2(75) NOT NULL
);

ALTER TABLE userinfo
    ADD CONSTRAINT users_pk PRIMARY KEY (user_id);

CREATE TABLE role
(
    auth_user_group_id BIGINT  AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(128) NOT NULL,
    auth_group VARCHAR2(128) NOT NULL,
    CONSTRAINT user_auth_group_fk FOREIGN KEY(username) REFERENCES userinfo(username),
    UNIQUE (username, auth_group)
);
CREATE TABLE userpayment
(
    payment_id    BIGINT       NOT NULL,
    payment_type  BIGINT       NOT NULL,
    payment_info  VARCHAR2(100) NOT NULL,
    users_user_id BIGINT       NOT NULL
);

ALTER TABLE userpayment
    ADD CONSTRAINT userpayment_pk PRIMARY KEY (payment_id);

CREATE TABLE userteam
(
    userteam_id     BIGINT NOT NULL,
    users_user_id   BIGINT NOT NULL,
    sports_sport_id BIGINT NOT NULL
);

ALTER TABLE userteam
    ADD CONSTRAINT userteam_pk PRIMARY KEY (userteam_id);

CREATE TABLE usertransaction
(
    transaction_id BIGINT      NOT NULL,
    payment_id     BIGINT      NOT NULL,
    amount         NUMBER       NOT NULL,
    typeof         VARCHAR2(20) NOT NULL,
    users_user_id  BIGINT      NOT NULL
);

ALTER TABLE usertransaction
    ADD CONSTRAINT usertransaction_pk PRIMARY KEY (transaction_id);

ALTER TABLE bets
    ADD CONSTRAINT bets_matchs_fk FOREIGN KEY (matchs_match_id)
        REFERENCES matchs (match_id);

ALTER TABLE league
    ADD CONSTRAINT league_sports_fk FOREIGN KEY (sports_sport_id)
        REFERENCES sports (sport_id);

ALTER TABLE matchstat
    ADD CONSTRAINT matchstat_matchs_fk FOREIGN KEY (matchs_match_id)
        REFERENCES matchs (match_id);

ALTER TABLE teams
    ADD CONSTRAINT teams_league_fk FOREIGN KEY (league_league_id)
        REFERENCES league (league_id);

ALTER TABLE userbet
    ADD CONSTRAINT userbet_bets_fk FOREIGN KEY (bets_bet_id)
        REFERENCES bets (bet_id);

ALTER TABLE userbet
    ADD CONSTRAINT userbet_users_fk FOREIGN KEY (users_user_id)
        REFERENCES userinfo (user_id);

ALTER TABLE userpayment
    ADD CONSTRAINT userpayment_users_fk FOREIGN KEY (users_user_id)
        REFERENCES userinfo (user_id);

ALTER TABLE userteam
    ADD CONSTRAINT userteam_sports_fk FOREIGN KEY (sports_sport_id)
        REFERENCES sports (sport_id);

ALTER TABLE userteam
    ADD CONSTRAINT userteam_users_fk FOREIGN KEY (users_user_id)
        REFERENCES userinfo (user_id);

ALTER TABLE usertransaction
    ADD CONSTRAINT usertransaction_users_fk FOREIGN KEY (users_user_id)
        REFERENCES userinfo (user_id);
