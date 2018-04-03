# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table note (
  id                            integer auto_increment not null,
  title                         varchar(255),
  description                   varchar(255),
  last_edited                   integer not null,
  constraint pk_note primary key (id)
);


# --- !Downs

drop table if exists note;

