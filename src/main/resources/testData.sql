INSERT INTO sport (name) VALUES ('Football'), ('Basketball'), ('Tennis'), ('Volleyball'), ('Baseball'), ('Boxing');

INSERT INTO team (team_id, name, city, founding_date, _sport) VALUES
    (1, 'Manchester United', 'Manchester', 1878, 'Football'),
    (2, 'Los Angeles Lakers', 'Los Angeles', 1947, 'Basketball'),
    (3, 'Golden State Warriors', 'San Francisco', 1946, 'Basketball'),
    (4, 'New York Yankees', 'New York City', 1903, 'Baseball'),
    (5, 'Chicago Bulls', 'Chicago', 1966, 'Basketball'),
    (6, 'Miami Heat', 'Miami', 1988, 'Basketball'),
    (7, 'Boston Red Sox', 'Boston', 1901, 'Baseball'),
    (8, 'Arsenal', 'London', 1886, 'Football');

INSERT INTO venue (name, address, city, country, capacity) VALUES
    ('Old Trafford', 'Sir Matt Busby Way', 'Manchester', 'United Kingdom', 74140),
    ('Staples Center', '1111 S Figueroa St', 'Los Angeles', 'United States', 20000),
    ('Oracle Park', '24 Willie Mays Plaza', 'San Francisco', 'United States', 41000),
    ('Yankee Stadium', '1 E 161st St', 'New York City', 'United States', 54251),
    ('United Center', '1901 W Madison St', 'Chicago', 'United States', 23500);

INSERT INTO event (event_id, date, time, description, entrance_fee, _sport, _venue_name, _venue_address) VALUES
    (1, '2024-12-01', '18:00:00', 'Premier League Match: Manchester United vs Arsenal', 75, 'Football', 'Old Trafford', 'Sir Matt Busby Way'),
    (2, '2024-12-05', '20:00:00', 'NBA Match: Los Angeles Lakers vs Golden State Warriors', 120, 'Basketball', 'Staples Center', '1111 S Figueroa St'),
    (3, '2024-12-10', '15:00:00', 'MLB Match: New York Yankees vs Boston Red Sox', 90, 'Baseball', 'Yankee Stadium', '1 E 161st St'),
    (4, '2024-12-15', '19:00:00', 'NBA Match: Chicago Bulls vs Miami Heat', 110, 'Basketball', 'United Center', '1901 W Madison St');


INSERT INTO event_team (_event_id, _team_id) VALUES
     (1, 1), -- Manchester United in Premier League Match
     (1, 8), -- Arsenal in Premier League Match
     (2, 2), -- Los Angeles Lakers in NBA Match
     (2, 3), -- Golden State Warriors in NBA Match
     (3, 4), -- New York Yankees in MLB Match
     (3, 7), -- Boston Red Sox in MLB Match
     (4, 5), -- Chicago Bulls in NBA Match
     (4, 6); -- Miami Heat in NBA Match