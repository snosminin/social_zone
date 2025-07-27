create table if not exists "user"
(
    id serial primary key,
    first_name varchar(256) null default null,
    middle_name varchar(256) null default null,
    last_name varchar(256) null default null,
    username varchar(256) null default null unique,
    mobile varchar(256) null unique,
    email varchar(256) null unique,
    "role" varchar(256) not null,
    password_hash varchar(256) not null
);

create table if not exists post
(
    id serial primary key,
    user_id integer not null constraint fk_user_post_user references "user",
    "text" text null default null
);

create table if not exists comment
(
    id serial primary key,
    user_id integer not null constraint fk_comment_user references "user",
    post_id integer not null constraint fk_comment_post references post,
    "text" text null default null
);

create table if not exists follow
(
    id serial primary key,
    follower_id integer not null constraint fk_follower_follow_user references "user",
    followee_id integer not null constraint fk_followee_follow_user references "user"
);

create table if not exists "like"
(
    id serial primary key,
    user_id integer not null constraint fk_like_user references "user",
    post_id integer not null constraint fk_like_post references post
);