CREATE TABLE authority (
  id   BIGINT NOT NULL,
  name VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT authority_name UNIQUE (name)
);

CREATE TABLE user (
  id                  BIGINT NOT NULL,
  user_name           VARCHAR(255),
  password            VARCHAR(255),
  account_expired     BOOLEAN,
  account_locked      BOOLEAN,
  credentials_expired BOOLEAN,
  enabled             BOOLEAN,
  PRIMARY KEY (id),
  CONSTRAINT user_user_name UNIQUE (user_name)
);

CREATE TABLE credentials (
  id       INTEGER,
  enabled  BOOLEAN      NOT NULL,
  name     VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  version  INTEGER,
  PRIMARY KEY (id)
);

CREATE TABLE users_authorities (
  user_id      BIGINT NOT NULL,
  authority_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, authority_id),
  CONSTRAINT FK_authority_id FOREIGN KEY (authority_id) REFERENCES authority (id),
  CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);

INSERT INTO authority VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority VALUES (2, 'ROLE_USER');

INSERT INTO user (id, user_name, password, account_expired, account_locked, credentials_expired, enabled) VALUES
  (1, 'admin', '$2a$10$BurTWIy5NTF9GJJH4magz.hLoyNEbvd.JI4kBleDUN5KZYK2ezulu', FALSE, FALSE, FALSE, TRUE),
  (2, 'vgeorgiou', '$2a$10$BurTWIy5NTF9GJJH4magz.hLoyNEbvd.JI4kBleDUN5KZYK2ezulu', FALSE, FALSE, FALSE, TRUE);

INSERT INTO users_authorities VALUES
  (1, 1),
  (2, 2);