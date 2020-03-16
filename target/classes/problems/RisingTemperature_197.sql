//Given a Weather table, write a SQL query to find all dates' Ids with higher temperature compared to its previous (yesterday's) dates.
//my solution 使用DATE_SUB
select t2.Id from Weather t1,Weather t2 where t1.RecordDate = DATE_SUB(t2.RecordDate,INTERVAL 1 DAY) and t1.Temperature<t2.Temperature

//official solution 使用DATEDIFF
SELECT
    weather.id AS 'Id'
FROM
    weather
        JOIN
    weather w ON DATEDIFF(weather.RecordDate, w.RecordDate) = 1
        AND weather.Temperature > w.Temperature
;