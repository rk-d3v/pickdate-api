CREATE TABLE IF NOT EXISTS keys
(
    id      varchar(255) PRIMARY KEY,
    salt    varchar(255),
    version int
);

CREATE TABLE IF NOT EXISTS config
(
    id           varchar(255) PRIMARY KEY,
    domain_url   varchar(255),
    key          varchar(255) REFERENCES keys (id),
    setup_status varchar(255)
);

