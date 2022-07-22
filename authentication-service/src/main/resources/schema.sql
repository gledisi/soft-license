DROP TABLE IF EXISTS licenses;

CREATE TABLE users (
  id                NUMERIC PRIMARY KEY NOT NULL,
  organization_id   TEXT NOT NULL,
  license_type      TEXT NOT NULL,
  product_name      TEXT NOT NULL,
  license_max       INT   NOT NULL,
  license_allocated INT,
  comment           VARCHAR(100));

CREATE TABLE IF NOT EXISTS oauth_access_token (
    token_id varchar(255) NOT NULL,
    token blob,
    authentication_id varchar(255) DEFAULT NULL,
    user_name varchar(255) DEFAULT NULL,
    client_id varchar(255) DEFAULT NULL,
    authentication blob,
    refresh_token varchar(255) DEFAULT NULL,
    PRIMARY KEY (token_id));

CREATE TABLE IF NOT EXISTS oauth_refresh_token (
    token_id varchar(255) NOT NULL,
    token blob,
    authentication blob,
    PRIMARY KEY (token_id));

INSERT INTO licenses (license_id,  organization_id, license_type, product_name, license_max, license_allocated)
VALUES ('f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a', 'e254f8c-c442-4ebe-a82a-e2fc1d1ff78a', 'user','CustomerPro', 100,5);
INSERT INTO licenses (license_id,  organization_id, license_type, product_name, license_max, license_allocated)
VALUES ('t9876f8c-c338-4abc-zf6a-ttt1', 'e254f8c-c442-4ebe-a82a-e2fc1d1ff78a', 'user','suitability-plus', 200,189);
INSERT INTO licenses (license_id,  organization_id, license_type, product_name, license_max, license_allocated)
VALUES ('38777179-7094-4200-9d61-edb101c6ea84', '442adb6e-fa58-47f3-9ca2-ed1fecdfe86c', 'user','HR-PowerSuite', 100,4);
INSERT INTO licenses (license_id,  organization_id, license_type, product_name, license_max, license_allocated)
VALUES ('08dbe05-606e-4dad-9d33-90ef10e334f9', '442adb6e-fa58-47f3-9ca2-ed1fecdfe86c', 'core-prod','WildCat Application Gateway', 16,16);