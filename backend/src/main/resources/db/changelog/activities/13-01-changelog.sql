--liquibase formatted sql

-- changeSet romzes:activity_data_01 contexts:romzes
insert into activity (name, lat, lon, address, image_url, label_url, building_id, duration, description, media_urls)
VALUES ('1', 12, 12, '1', '1', '1', 1, 123, '', '1');
-- changeSet romzes:activity_data_02 contexts:romzes
insert into activity_tag (activity_id, tag_id) VALUES (1, 1);

-- changeSet romzes:activity_data_03 contexts:romzes
insert into activity_scheduled (status, activity_id, date_time) VALUES ('STARTED', 1, '2021-11-11 11:40');
-- changeSet romzes:activity_data_04 contexts:romzes
insert into activity (name, lat, lon, address, image_url, label_url, building_id, duration, description, media_urls)
VALUES
       ('1', 12, 12, '1', '1', '1', 1, 123, '', '1'),
       ('2', 12, 12, '1', '1', '1', 1, 123, '', '1'),
       ('3', 12, 12, '1', '1', '1', 1, 123, '', '1');
-- changeSet romzes:activity_data_05 contexts:romzes
insert into activity_scheduled (status, activity_id, date_time) VALUES ('STARTED', 1, '2021-11-11 12:40');
-- changeSet romzes:activity_data_06 contexts:romzes
insert into activity_scheduled (status, activity_id, date_time) VALUES ('STARTED', 1, '2022-11-11 12:40');