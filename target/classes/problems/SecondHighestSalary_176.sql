SELECT
    (SELECT distinct
            Salary
        FROM
            Employee
        ORDER BY Salary DESC
        LIMIT 1 OFFSET 1) AS SecondHighestSalary;
-->直接select返回不存在null，distinct的聚合会返回一个带有null的集合