use wifipositioning;

create table fingerprints
(	
	id varchar(36) not null primary key,
	mac char(17) not null,
	rss tinyint not null,
	x_pos float not null,
	y_pos float not null
);

delimiter //
create trigger auto_set_id before insert on fingerprints 
for each row
begin
	if(new.id='' or new.id is null) then 
		set new.id=uuid();
	end if;
end;//

--insert into fingerprints (mac, rss, x_pos, y_pos) values('12:ws:2w:32:4r:fd', 65, 89.7, 29.52);
	