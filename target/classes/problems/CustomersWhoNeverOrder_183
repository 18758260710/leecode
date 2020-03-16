select Name as Customers from Customers a left join Orders b on a.Id = b.CustomerId where isnull(b.Id);
--> my solution

select customers.name as 'Customers'
from customers
where customers.id not in
(
    select customerid from orders
);
--> official solution