select c.Name as Department,a.Name as Employee,Salary
from Employee a
right join (select max(Salary) as max,DepartmentId from Employee group by DepartmentId) b on a.Salary = b.max and a.DepartmentId = b.DepartmentId
join Department c on a.DepartmentId = c.Id
--> my solution


SELECT
    Department.name AS 'Department',
    Employee.name AS 'Employee',
    Salary
FROM
    Employee
        JOIN
    Department ON Employee.DepartmentId = Department.Id
WHERE
    (Employee.DepartmentId , Salary) IN
    (   SELECT
            DepartmentId, MAX(Salary)
        FROM
            Employee
        GROUP BY DepartmentId
	)
;
--> official solution