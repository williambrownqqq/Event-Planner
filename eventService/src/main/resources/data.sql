use diploma;
--insert into roles(id, name) values(1, "ROLE_USER"),
--(2, "ROLE_MODERATOR"),
--(3, "ROLE_ADMIN");

--use diploma;
--
--show tables;
--select * from event;
--select * from facility;
--select * from roles;
--select * from task;

INSERT INTO facility (facility_title, photo_url, description) VALUES ('Facility 1', 'photo1.jpg', 'Description of Facility 1');
INSERT INTO facility (facility_title, photo_url, description) VALUES ('Facility 2', 'photo2.jpg', 'Description of Facility 2');
INSERT INTO facility (facility_title, photo_url, description) VALUES ('Facility 3', 'photo3.jpg', 'Description of Facility 3');
INSERT INTO facility (facility_title, photo_url, description) VALUES ('Facility 4', 'photo4.jpg', 'Description of Facility 4');
INSERT INTO facility (facility_title, photo_url, description) VALUES ('Facility 5', 'photo5.jpg', 'Description of Facility 5');

INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Event 1', 'event_photo1.jpg', 'Description of Event 1', 'Location 1', 1, '2024-02-21', 'HIGH', 'OVERHAUL', 'BACKLOG', '2024-02-21 10:00:00', '2024-02-21 12:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Event 2', 'event_photo2.jpg', 'Description of Event 2', 'Location 2', 2, '2024-02-22', 'MEDIUM', 'OVERHAUL', 'BACKLOG', '2024-02-22 11:00:00', '2024-02-22 13:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Event 3', 'event_photo3.jpg', 'Description of Event 3', 'Location 3', 3, '2024-02-23', 'LOW', 'OVERHAUL', 'BACKLOG', '2024-02-23 12:00:00', '2024-02-23 14:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Event 4', 'event_photo4.jpg', 'Description of Event 4', 'Location 4', 4, '2024-02-24', 'HIGH', 'OVERHAUL', 'BACKLOG', '2024-02-24 13:00:00', '2024-02-24 15:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Event 5', 'event_photo5.jpg', 'Description of Event 5', 'Location 5', 5, '2024-02-25', 'MEDIUM', 'OVERHAUL', 'BACKLOG', '2024-02-25 14:00:00', '2024-02-25 16:00:00');

INSERT INTO task (task_title, task_description, deadline, event_id)
VALUES
('Task for Event 1', 'Description of Task for Event 1', '2024-03-01', 1),
('Task for Event 2', 'Description of Task for Event 2', '2024-03-02', 1),
('Task for Event 3', 'Description of Task for Event 3', '2024-03-03', 2),
('Task for Event 4', 'Description of Task for Event 4', '2024-03-04', 3),
('Task for Event 5', 'Description of Task for Event 5', '2024-03-05', 4);
--create database diploma;
--create table dioploma(
--    id INT,
--    name
--);
----select * from roles;
--select * from user_roles;
--select * from users;
--insert into user_roles(user_id, role_id) values(1, 3);