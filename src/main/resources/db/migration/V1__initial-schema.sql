CREATE TABLE IF NOT EXISTS consumptions
(
  id          SERIAL PRIMARY KEY,
  village_id  int    NOT NULL,
  consumption decimal NOT NULL,
  datetime    timestamp       NOT NULL default current_timestamp
);

CREATE TABLE IF NOT EXISTS villages
(
  id   SERIAL  PRIMARY KEY NOT NULL,
  name varchar(100)             NOT NULL
);
