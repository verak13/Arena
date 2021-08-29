INSERT INTO authorities (name) VALUES ('ROLE_ADMINISTRATOR');
INSERT INTO authorities (name) VALUES ('ROLE_USER');

insert into admins (id, first_name, last_name, email, password, verified) values (nextval('person_seq'), 'admin', 'admin', 'admin@gmail.com', '$2y$12$NFN7DJUX1lFfaDX1tc9/6uBtgls9SZOU9iwjhrlXJc0xr471vgKAK', true);
insert into users (id, first_name, last_name, email, password, verified) values (nextval('person_seq'), 'user', 'user', 'user@gmail.com', '$2y$12$NFN7DJUX1lFfaDX1tc9/6uBtgls9SZOU9iwjhrlXJc0xr471vgKAK', true);

insert into user_authority (user_id, authority_id) values (1,1);
insert into user_authority (user_id, authority_id) values (2,2);



