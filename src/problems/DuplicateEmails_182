select Email from Person group by Email having count(Id)>1;
-->my solution official solution2

select Email from
(
  select Email, count(Email) as num
  from Person
  group by Email
) as statistic
where num > 1
;
-->official solution1