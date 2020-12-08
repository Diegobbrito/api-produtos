create table usuario (
id bigint not null auto_increment, 
username varchar(255), 
password varchar(255), 
authorities varchar(255),
primary key (id)) engine=InnoDB;

INSERT INTO produtos.usuario (username, password, authorities) VALUES ('Admin', '$2a$10$5l7VBtTLjOYFjUCOnGCH5uny81vZWiD4DHAqdvIawT7jYjUt2bYBK', 'ROLE_USER,ROLE_ADMIN');

INSERT INTO produtos.usuario (username, password, authorities ) VALUES ('User', '$2a$10$ggZCLzTRQ8zAKslJkIpELeVtq4LXAk3z6sl0BoLS9yExyD1.bTFIO', 'ROLE_USER');