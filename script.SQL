DROP DATABASE IF EXISTS leaderboard;
CREATE DATABASE leaderboard;
CREATE TABLE leaderboard.players(
    player_name VARCHAR (20) NOT NULL PRIMARY KEY,
    victories INT NOT NULL DEFAULT (0)
);