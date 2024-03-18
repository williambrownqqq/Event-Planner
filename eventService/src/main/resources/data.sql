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

INSERT INTO facility (facility_title, photo_url, description) VALUES ('Chernobyl Nuclear Power Plant', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Chernobyl_04710018_%288134364258%29.jpg/1200px-Chernobyl_04710018_%288134364258%29.jpg', 'Description of Facility 1');
INSERT INTO facility (facility_title, photo_url, description) VALUES ('Khmelnitsky Nuclear Power Plant', 'https://zn.ua/img/forall/u/14/8/967-2-1.jpg', 'Description of Facility 2');
INSERT INTO facility (facility_title, photo_url, description) VALUES ('Rivne Nuclear Power Plant', 'https://eimg.pravda.com/images/doc/7/5/751afda----.jpg', 'Description of Facility 3');
INSERT INTO facility (facility_title, photo_url, description) VALUES ('South Ukraine Nuclear Power Plant', 'https://storage1b.censor.net/images/7/4/5/b/745b36a8e257c4841b5400ec4e177b58/original.jpg', 'Description of Facility 4');
INSERT INTO facility (facility_title, photo_url, description) VALUES ('Zaporizhzhya Nuclear Power Plant', 'https://gmk.center/wp-content/uploads/2021/11/aes-1.png', 'Description of Facility 5');

INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Reactor Maintenance', 'https://media02.stockfood.com/largepreviews/MzYxMjU2MzYx/11653431-Chernobyl-reactor-3-control-room.jpg', 'Scheduled maintenance of reactor core components to ensure safe operation and regulatory compliance.', 'Nuclear Power Plant A', 1, '2024-02-21', 'HIGH', 'REACTOR_MAINTENANCE', 'SCHEDULED', '2024-02-21 10:00:00', '2024-02-21 12:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Cooling System Overhaul', 'https://www.researchgate.net/publication/305440380/figure/fig2/AS:613923069902848@1523382074031/Schematic-diagram-of-the-reactor-cooling-system-At-Daya-Bay-each-reactor-core-is.png', 'Comprehensive overhaul of the cooling system to prevent overheating and ensure efficient heat dissipation.', 'Nuclear Power Plant B', 2, '2024-02-22', 'MEDIUM', 'COOLING_SYSTEM_OVERHAUL', 'SCHEDULED', '2024-02-22 11:00:00', '2024-02-22 13:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Emergency Preparedness Drill', 'https://qph.cf2.quoracdn.net/main-qimg-816bc3523633a28597549fb2e6ab4b8c-lq', 'Simulation exercise to test emergency response procedures and evaluate readiness for various nuclear incidents.', 'Nuclear Power Plant C', 3, '2024-02-23', 'LOW', 'EMERGENCY_DRILL', 'EXERCISE', '2024-02-23 12:00:00', '2024-02-23 14:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Containment Structure Inspection', 'https://news.bbcimg.co.uk/media/images/51056000/jpg/_51056496_picture61.jpg', 'Inspection of the containment structure to ensure integrity and prevent radioactive leaks.', 'Nuclear Power Plant D', 1, '2024-02-24', 'HIGH', 'CONTAINMENT_INSPECTION', 'SCHEDULED', '2024-02-24 13:00:00', '2024-02-24 15:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Fuel Rod Replacement', 'https://i.redd.it/j9xucaxjm8k61.jpg', 'Replacement of spent fuel rods with fresh ones to maintain reactor efficiency and safety.', 'Nuclear Power Plant E', 2, '2024-02-25', 'MEDIUM', 'FUEL_ROD_REPLACEMENT', 'SCHEDULED', '2024-02-25 14:00:00', '2024-02-25 16:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Radiation Monitoring', 'radiation_monitoring.jpg', 'Routine monitoring of radiation levels in and around the nuclear facility to ensure safety and regulatory compliance.', 'Nuclear Power Plant F', 3, '2024-02-26', 'LOW', 'RADIATION_MONITORING', 'ROUTINE', '2024-02-26 15:00:00', '2024-02-26 17:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Security Training Exercise', 'security_training_exercise.jpg', 'Training exercise to enhance the security personnels response to potential security threats and breaches.', 'Nuclear Power Plant G', 1, '2024-02-27', 'MEDIUM', 'SECURITY_TRAINING', 'EXERCISE', '2024-02-27 09:00:00', '2024-02-27 11:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Emergency Generator Test', 'emergency_generator_test.jpg', 'Testing of emergency backup generators to ensure their functionality in case of power grid failures.', 'Nuclear Power Plant H', 2, '2024-02-28', 'HIGH', 'EMERGENCY_GENERATOR_TEST', 'SCHEDULED', '2024-02-28 10:00:00', '2024-02-28 12:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Control Room Upgrades', 'control_room_upgrades.jpg', 'Installation of new control room equipment and software upgrades to enhance monitoring and control capabilities.', 'Nuclear Power Plant I', 3, '2024-02-29', 'MEDIUM', 'CONTROL_ROOM_UPGRADES', 'PLANNED', '2024-02-29 11:00:00', '2024-02-29 13:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Spent Fuel Storage Inspection', 'spent_fuel_storage_inspection.jpg', 'Inspection of spent fuel storage facilities to ensure proper storage conditions and prevent environmental contamination.', 'Nuclear Power Plant J', 1, '2024-03-01', 'HIGH', 'FUEL_STORAGE_INSPECTION', 'SCHEDULED', '2024-03-01 12:00:00', '2024-03-01 14:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Emergency Cooling System Test', 'emergency_cooling_system_test.jpg', 'Testing of emergency cooling systems to verify their effectiveness in cooling reactor cores during unforeseen events.', 'Nuclear Power Plant K', 2, '2024-03-02', 'HIGH', 'EMERGENCY_COOLING_TEST', 'SCHEDULED', '2024-03-02 13:00:00', '2024-03-02 15:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Radiation Protection Equipment Maintenance', 'radiation_protection_maintenance.jpg', 'Maintenance and calibration of radiation protection equipment to ensure accurate measurement and protection of personnel.', 'Nuclear Power Plant L', 3, '2024-03-03', 'MEDIUM', 'RADIATION_PROTECTION_MAINTENANCE', 'SCHEDULED', '2024-03-03 14:00:00', '2024-03-03 16:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Fire Safety Drill', 'fire_safety_drill.jpg', 'Drill to test the response procedures for fires within the nuclear facility and ensure the safety of personnel and equipment.', 'Nuclear Power Plant M', 1, '2024-03-04', 'LOW', 'FIRE_SAFETY_DRILL', 'EXERCISE', '2024-03-04 15:00:00', '2024-03-04 17:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Emergency Evacuation Exercise', 'emergency_evacuation_exercise.jpg', 'Simulation of emergency evacuation procedures to assess readiness and response capabilities in case of a nuclear incident.', 'Nuclear Power Plant N', 2, '2024-03-05', 'MEDIUM', 'EVACUATION_EXERCISE', 'EXERCISE', '2024-03-05 16:00:00', '2024-03-05 18:00:00');
INSERT INTO event (event_title, photo_url, event_description, event_location, facility_id, event_date, urgency, event_type, action, open_event_date, closed_event_date) VALUES ('Emergency Response Training', 'emergency_response_training.jpg', 'Training session for emergency response teams to improve their skills in managing nuclear emergencies and protecting the public.', 'Nuclear Power Plant O', 3, '2024-03-06', 'HIGH', 'EMERGENCY_RESPONSE_TRAINING', 'TRAINING', '2024-03-06 17:00:00', '2024-03-06 19:00:00');


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