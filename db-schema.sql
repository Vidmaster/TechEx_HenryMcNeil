create table users (
  id int not null auto_increment,
  name varchar(60) not null,
  description varchar (4000),
  unique (name),
  primary key (id)
);

create table posts (
  id int not null auto_increment,
  user_id int not null references users(id),
  subject varchar(240) not null,
  body text not null,
  posted timestamp not null,
  updated timestamp,
  primary key (id)
);

create table comments (
  id int not null auto_increment,
  post_id int not null references posts(id),
  author_name varchar(60) not null,
  subject varchar (240) not null,
  text varchar (500) not null,
  posted timestamp not null,
  primary key (id)
);