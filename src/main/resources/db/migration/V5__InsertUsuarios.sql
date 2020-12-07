create table usuario (
id bigint not null auto_increment, 
username varchar(255), 
password varchar(255), 
authoriries varchar(255),
primary key (id)) engine=InnoDB;

INSERT INTO produtos.usuario (username, password, admin ) VALUES ('Admin', '$2a$10$5l7VBtTLjOYFjUCOnGCH5uny81vZWiD4DHAqdvIawT7jYjUt2bYBK', true);

INSERT INTO produtos.usuario (username, password, admin ) VALUES ('User', '$2a$10$ggZCLzTRQ8zAKslJkIpELeVtq4LXAk3z6sl0BoLS9yExyD1.bTFIO', false);