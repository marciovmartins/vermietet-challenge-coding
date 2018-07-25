create table vermietet.consumptions
(
  id          int unsigned auto_increment primary key,
  village_id  int unsigned    not null,
  consumption double unsigned not null,
  datetime    timestamp       not null
  on update CURRENT_TIMESTAMP
);

CREATE TABLE villages
(
  id   int unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name varchar(100)             NOT NULL
);
