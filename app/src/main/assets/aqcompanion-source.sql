-- Creating all tables
CREATE TABLE 'icon' ('_id' integer primary key, 'name' text, 'img_file' text);

CREATE TABLE 'campaign_static' ('_id' integer primary key, 'title' text);
CREATE TABLE 'quest_static' ('_id' integer primary key, 'campaign_id' integer, 'title' text, 'difficulty' text, 'circle' integer, 'has_reward' boolean, 'has_title' boolean, 'gives_icon' integer);

CREATE TABLE 'uses_icon' ('quest_id' integer, 'icon_id' integer);

CREATE TABLE 'campaign_play' ('_id' integer primary key autoincrement, 'campaign_id' integer, 'completed' boolean);
CREATE TABLE 'quest_play' ('_id' integer primary key autoincrement, 'campaign_id' integer, 'quest_id' integer);
CREATE TABLE 'hero_play' ('_id' integer primary key autoincrement, 'player_id' integer, 'name' text, 'card1' text, 'card2' text, 'card3' text, 'card4' text, 'curse' text);
CREATE TABLE 'player' ('_id' integer primary key autoincrement, 'campaign_id' integer, 'name' text, 'color' text, 'saved_gold' boolean);
CREATE TABLE 'quest_attribute' ('_id' integer primary key autoincrement, 'quest_id' integer, 'player_id' integer, 'name' text);

-- Populating data in the static ones
INSERT INTO 'icon' VALUES (0, 'circle', 'icon_circle.png');
INSERT INTO 'icon' VALUES (1, 'diamond', 'icon_diamond.png');
INSERT INTO 'icon' VALUES (2, 'spiral', 'icon_spiral.png');
INSERT INTO 'icon' VALUES (3, 'fivestar', 'icon_fivestar.png');
INSERT INTO 'icon' VALUES (4, 'hexagon', 'icon_hexagon.png');
INSERT INTO 'icon' VALUES (5, 'triangle', 'icon_triangle.png');
INSERT INTO 'icon' VALUES (6, 'sixstar', 'icon_sixstar.png');
INSERT INTO 'icon' VALUES (7, 'x', 'icon_x.png');
INSERT INTO 'icon' VALUES (8, 'square', 'icon_square.png');

-- Arcadia Quest
INSERT INTO 'campaign_static' VALUES (0, 'Arcadia Quest');

INSERT INTO 'quest_static' VALUES (0, 0, 'District of Hammers', 'easy', 1, 1, 0, -1);
INSERT INTO 'quest_static' VALUES (1, 0, 'Brightsun Arena', 'easy', 1, 1, 0, -1);
INSERT INTO 'quest_static' VALUES (2, 0, 'The Moon Gate', 'medium', 1, 1, 1, 0);
INSERT INTO 'quest_static' VALUES (3, 0, 'The Rookery', 'medium', 1, 1, 1, 1);
INSERT INTO "quest_static" VALUES (4, 0, "The Orcs' Hive", "hard", 1, 1, 1, 4);
INSERT INTO "quest_static" VALUES (5, 0, "The Manor", "hard", 1, 1, 1, 5);
INSERT INTO "quest_static" VALUES (6, 0, "Alchemist's District", "easy", 2, 1, 1, 7);
INSERT INTO "quest_static" VALUES (7, 0, "The University Plaza", "medium", 2, 1, 1, 6);
INSERT INTO "quest_static" VALUES (8, 0, "Red Dawn Square", "medium", 2, 1, 1, 3);
INSERT INTO "quest_static" VALUES (9, 0, "Evershadow District", "hard", 2, 1, 1, 2);
INSERT INTO "quest_static" VALUES (10, 0, "The Temple of Dawning Twilight", "", 3, 0, 0, -1);

INSERT INTO 'uses_icon' VALUES (6, 0);
INSERT INTO 'uses_icon' VALUES (6, 5);
INSERT INTO 'uses_icon' VALUES (7, 1);
INSERT INTO 'uses_icon' VALUES (7, 4);
INSERT INTO 'uses_icon' VALUES (8, 4);
INSERT INTO 'uses_icon' VALUES (8, 5);
INSERT INTO 'uses_icon' VALUES (9, 0);
INSERT INTO 'uses_icon' VALUES (9, 1);
INSERT INTO 'uses_icon' VALUES (10, 2);
INSERT INTO 'uses_icon' VALUES (10, 3);
INSERT INTO 'uses_icon' VALUES (10, 6);
INSERT INTO 'uses_icon' VALUES (10, 7);

-- Beyond The Grave
INSERT INTO 'campaign_static' VALUES (1, 'Beyond The Grave');

INSERT INTO 'quest_static' VALUES (11, 1, 'Haunted Barracks', 'easy', 1, 1, 1, 6);
INSERT INTO 'quest_static' VALUES (12, 1, 'Slaughterhouse', 'medium', 1, 1, 1, 0);
INSERT INTO 'quest_static' VALUES (13, 1, 'Crypts', 'medium', 1, 1, 1, 8);
INSERT INTO 'quest_static' VALUES (14, 1, 'The Graveyard', 'hard', 1, 1, 1, 4);
INSERT INTO 'quest_static' VALUES (15, 1, 'Bridge of the Damned', 'hard', 1, 1, 1, 5);
INSERT INTO 'quest_static' VALUES (16, 1, 'Black Shrine', 'easy', 2, 1, 1, 7);
INSERT INTO 'quest_static' VALUES (17, 1, 'Mill of Souls', 'medium', 2, 1, 1, 3);
INSERT INTO 'quest_static' VALUES (18, 1, 'Secret Laboratory', 'hard', 2, 1, 1, 2);
INSERT INTO 'quest_static' VALUES (19, 1, 'The Unholy Mausoleum', '', 3, 0, 0, -1);

INSERT INTO 'uses_icon' VALUES (16, 6);
INSERT INTO 'uses_icon' VALUES (16, 8);
INSERT INTO 'uses_icon' VALUES (17, 6);
INSERT INTO 'uses_icon' VALUES (17, 0);
INSERT INTO 'uses_icon' VALUES (18, 0);
INSERT INTO 'uses_icon' VALUES (18, 8);
INSERT INTO 'uses_icon' VALUES (19, 5);
INSERT INTO 'uses_icon' VALUES (19, 4);
INSERT INTO 'uses_icon' VALUES (19, 3);
INSERT INTO 'uses_icon' VALUES (19, 2);
INSERT INTO 'uses_icon' VALUES (19, 7);

-- The Nameless Campaign
INSERT INTO 'campaign_static' VALUES (2, 'The Nameless Campaign');

INSERT INTO 'quest_static' VALUES (20, 2, 'District of Hammers', 'easy', 1, 1, 0, -1);
INSERT INTO 'quest_static' VALUES (21, 2, 'Brightsun Arena', 'easy', 1, 1, 0, -1);
INSERT INTO 'quest_static' VALUES (22, 2, 'The Moon Gate', 'medium', 1, 1, 1, 0);
INSERT INTO 'quest_static' VALUES (23, 2, 'The Rookery', 'medium', 1, 1, 1, 1);
INSERT INTO "quest_static" VALUES (24, 2, "The Orcs' Hive", "hard", 1, 1, 1, 4);
INSERT INTO "quest_static" VALUES (25, 2, "The Manor", "hard", 1, 1, 1, 5);
INSERT INTO "quest_static" VALUES (26, 2, "Alchemist's District", "easy", 2, 1, 1, 7);
INSERT INTO "quest_static" VALUES (27, 2, "The University Plaza", "medium", 2, 1, 1, 6);
INSERT INTO "quest_static" VALUES (28, 2, "Red Dawn Square", "medium", 2, 1, 1, 3);
INSERT INTO "quest_static" VALUES (29, 2, "Evershadow District", "hard", 2, 1, 1, 2);
INSERT INTO "quest_static" VALUES (30, 2, "The Temple of Dawning Twilight", "", 3, 0, 0, -1);

INSERT INTO 'uses_icon' VALUES (26, 0);
INSERT INTO 'uses_icon' VALUES (26, 5);
INSERT INTO 'uses_icon' VALUES (27, 1);
INSERT INTO 'uses_icon' VALUES (27, 4);
INSERT INTO 'uses_icon' VALUES (28, 4);
INSERT INTO 'uses_icon' VALUES (28, 5);
INSERT INTO 'uses_icon' VALUES (29, 0);
INSERT INTO 'uses_icon' VALUES (29, 1);
INSERT INTO 'uses_icon' VALUES (30, 2);
INSERT INTO 'uses_icon' VALUES (30, 3);
INSERT INTO 'uses_icon' VALUES (30, 6);
INSERT INTO 'uses_icon' VALUES (30, 7);