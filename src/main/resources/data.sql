Insert into authority(name) VALUES ('ROLE_USER');
Insert into authority(name) VALUES ('ROLE_ADMIN');

Insert into user(id, activated, activation_key, email, password_hash, username) values(1, true, null, 'admin@gmail.com', '$2a$06$VT8ENpzvHBKv0q9.AC37OejTN6t5sgxmAhIRufjSNTsf772r1xnei', 'admin');
Insert into user(id, activated, activation_key, email, password_hash, username) values(2, true, null, 'user@gmail.com', '$2a$06$OymKcdX1XvDYk3nVxHOeOuFMlDwBrSD7StcKY/K3qex/QXGJAATjy', 'user');

Insert INTO user_site_authority(user_id, authority_name) VALUES (1, 'ROLE_USER');
Insert INTO user_site_authority(user_id, authority_name) VALUES (1, 'ROLE_ADMIN');

Insert INTO user_site_authority(user_id, authority_name) VALUES (2, 'ROLE_USER')
