select a.Name as Employee from Employee a left join Employee b on a.ManagerId=b.Id where a.Salary>b.Salary;
--> my solution official solution2 显式内连接

SELECT
    a.Name AS 'Employee'
FROM
    Employee AS a,
    Employee AS b
WHERE
    a.ManagerId = b.Id
        AND a.Salary > b.Salary
;
--> official solution1 显隐式内连接