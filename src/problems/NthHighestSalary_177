CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  SET N = N - 1;
  RETURN (
      # Write your MySQL query statement below.
      SELECT DISTINCT Salary FROM Employee
      ORDER BY Salary DESC LIMIT 1 OFFSET N
  );
END
-->return起到了176中select的作用，会生成null，distinct的聚合会返回一个带有null的集合