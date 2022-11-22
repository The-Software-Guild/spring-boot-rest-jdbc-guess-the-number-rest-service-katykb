drop database if exists GuessTheNumber;

create database GuessTheNumber;

use GuessTheNumber;

create table game(
game_id int primary key auto_increment,
answer varchar (4),
isFinished boolean null default false
);

create table round(
round_id int primary key auto_increment,
game_id int,
whenPlayed timestamp null default current_timestamp,
guess varchar(10),
guessResult varchar(10) null,
Constraint foreign key (game_id) references game(game_id)
);

insert into game values(1, 2365, 1);

insert into round values(101, 1, '2022-11-11 13:23:44', 3625, 'e:1::p:2' );


