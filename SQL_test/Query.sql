WITH team_points AS(
    SELECT  host_team team,
             CASE 
                WHEN host_goals > guest_goals THEN 3
                WHEN host_goals = guest_goals THEN 1
                ELSE 0 END points
    FROM matches
    UNION ALL
    SELECT  guest_team team,
             CASE 
                WHEN guest_goals > host_goals THEN 3
                WHEN guest_goals = host_goals THEN 1
                ELSE 0 END points
    FROM matches
),
aggregated_points AS
(
    SELECT  team, sum(points) num_points
    FROM    team_points
    group   by team
)
SELECT  team_id, team_name, 
        coalesce(num_points, 0) num_points
FROM    aggregated_points, teams
WHERE   team(+) = team_id
ORDER BY coalesce(num_points, 0) DESC, team_id ASC
