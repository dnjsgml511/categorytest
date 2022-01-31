delete from category;

ALTER SEQUENCE HIBERNATE_SEQUENCE RESTART WITH 10;

insert into category (id, category, parent_id, rownums) values (1, 'data1', null, 1);

insert into category (id, category, parent_id, rownums) values (2, 'data2', 1, 1);
insert into category (id, category, parent_id, rownums) values (3, 'data3', 1, 2);

insert into category (id, category, parent_id, rownums) values (4, 'data4', 2, 1);
insert into category (id, category, parent_id, rownums) values (5, 'data5', 2, 2);
insert into category (id, category, parent_id, rownums) values (6, 'data6', 2, 3);

insert into category (id, category, parent_id, rownums) values (7, 'data7', 3, 1);
insert into category (id, category, parent_id, rownums) values (8, 'data8', 3, 2);
insert into category (id, category, parent_id, rownums) values (9, 'data9', 3, 3);
