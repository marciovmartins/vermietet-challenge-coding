create table consumptions
(
  id          int unsigned auto_increment primary key,
  village_id  int unsigned                        not null,
  consumption double unsigned                     not null,
  datetime    timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
);

