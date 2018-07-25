CREATE TABLE IF NOT EXISTS consumptions
(
  id          int unsigned AUTO_INCREMENT PRIMARY KEY,
  village_id  int unsigned    NOT NULL,
  consumption double unsigned NOT NULL,
  datetime    timestamp       NOT NULL
);

CREATE TABLE IF NOT EXISTS villages
(
  id   int unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name varchar(100)             NOT NULL
);

INSERT INTO vermietet.villages (id, name) VALUES (1, 'Villarriba');
INSERT INTO vermietet.villages (id, name) VALUES (2, 'Villabajo');