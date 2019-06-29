insert into teams(team_id, team_name) values(10, 'Give');
insert into teams(team_id, team_name) values(20, 'Never');
insert into teams(team_id, team_name) values(30, 'You');
insert into teams(team_id, team_name) values(40, 'Up');
insert into teams(team_id, team_name) values(50, 'Gonna');

insert into matches (match_id, host_team, guest_team, host_goals, guest_goals)
values (1,30,20,1,0);
insert into matches (match_id, host_team, guest_team, host_goals, guest_goals)
values (2,10,20,1,2);
insert into matches (match_id, host_team, guest_team, host_goals, guest_goals)
values (3,20,50,2,2);
insert into matches (match_id, host_team, guest_team, host_goals, guest_goals)
values (4,10,30,1,0);
insert into matches (match_id, host_team, guest_team, host_goals, guest_goals)
values (5,30,50,0,1);

commit;
