create table cliente (id bigint not null auto_increment, data_cadastro date, documento varchar(255), email varchar(255), nome varchar(255) not null, senha varchar(255), primary key (id)) engine=InnoDB;
create table fornecedor (id bigint not null auto_increment, cnpj varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB;
create table produto (id bigint not null auto_increment, categoria varchar(255) not null, codigo_produto varchar(255), imagem varchar(255) not null, nome varchar(255) not null, promocao bit not null, quantidade bigint not null, valor decimal(19,2) not null, valor_promo decimal(19,2) not null, id_fornecedor bigint not null, primary key (id)) engine=InnoDB;
create table venda (id bigint not null auto_increment, data_compra datetime(6), total_compra decimal(19,2), cliente_id bigint not null, fornecedor_id bigint not null, primary key (id)) engine=InnoDB;
create table venda_produto (venda_id bigint not null, produto_id bigint not null) engine=InnoDB;
create table usuario (id bigint not null auto_increment, username varchar(255), password varchar(255), authorities varchar(255),primary key (id)) engine=InnoDB;
alter table cliente add constraint Cliente_CPF_Unico unique (documento);
alter table fornecedor add constraint Fornecedor_CNPJ_Unico unique (cnpj);
alter table produto add constraint FKg0kbs9pp5getbcfp892wf3y1c foreign key (id_fornecedor) references fornecedor (id) ON UPDATE CASCADE ON DELETE CASCADE;
alter table venda add constraint FK50murhuotq9h2dnxej317jjiy foreign key (cliente_id) references cliente (id) ON UPDATE CASCADE;
alter table venda add constraint FK29grb6hh30perue3v1btueq6d foreign key (fornecedor_id) references fornecedor (id) ON UPDATE CASCADE;
alter table venda_produto add constraint FK4go8k4gnl96q60tpreyxc1d88 foreign key (produto_id) references produto (id) ON UPDATE CASCADE;
alter table venda_produto add constraint FKonh0ak31073oc57xyeu2etdx4 foreign key (venda_id) references venda (id) ON UPDATE CASCADE ON DELETE CASCADE;