create table PARENT_ENTITIES (id bigint generated by default as identity, aSimpleCharValue varchar(255), aEnumValue bigint(8),primary key (id))
create table CHILD_ENTITIES (id bigint generated by default as identity, aSimpleCharValue varchar(255), parentid bigint(8), primary key (id))
