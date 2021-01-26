//my solution  sql去重保留最小Id  如果保留最大id 换成max就行
delete Person from Person, (select min(Id) as Id,Email from Person group by Email having count(*)>1) t2
where Person.Id > t2.Id and Person.Email = t2.Email;
select min(Id) as Id,Email from Person group by Email having count(*)>1

//official solution 更简单
DELETE p1 FROM Person p1,
    Person p2
WHERE
    p1.Email = p2.Email AND p1.Id > p2.Id