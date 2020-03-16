SELECT score,(SELECT COUNT(DISTINCT score) FROM Scores where score > a.score) + 1 rank
from Scores a
ORDER by score DESC;
-->每个都去数比当前大的有多少个

SELECT
  Score,
  @rank := @rank + (@prev <> (@prev := Score)) Rank
FROM
  Scores,
  (SELECT @rank := 0, @prev := -1) init
ORDER BY Score desc
-->变量记录排名

SELECT
  Score,
  (SELECT count(*) FROM (SELECT distinct Score s FROM Scores) tmp WHERE s >= Score) Rank
FROM Scores
ORDER BY Score desc
-->变量记录排名  子查询的结果缓存 所以快