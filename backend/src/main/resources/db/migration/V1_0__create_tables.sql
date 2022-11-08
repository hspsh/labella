CREATE TABLE templates
(
    id       LONG PRIMARY KEY AUTO_INCREMENT,
    name     TEXT,
    type     VARCHAR(5),
    template TEXT,
    created  DATETIME,
    updated  DATETIME
);