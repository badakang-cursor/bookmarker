create sequence bm_id_seq start with 1 increment by 50;

create table bookmarks (
    id bigint default nextval('bm_id_seq') not null,
    title varchar(255) not null,
    url varchar(255) not null,
    create_at timestamp(6) with time zone,
    primary key (id)
);

alter table bookmarks add column delflag char(1) default 'N';