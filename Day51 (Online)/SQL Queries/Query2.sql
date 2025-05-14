/*

Find all products that were sold at a higher price than their base price listed 
in the Products table:

Sample Output:
==============

Name    OrderID TotalCost                                                                                               
Alice Johnson   1001    1250.00                                                                                         
Bob Smith       1002    850.00                                                                                          
Diana Williams  1005    450.00                                                                                          
Charlie Davis   1010    950.00                                                                                                


Customers:
==========
Field   Type    Null    Key     Default Extra                                                                           
CustomerID      int     NO      PRI     NULL                                                                            
Name    varchar(255)    YES             NULL                                                                            
Email   varchar(255)    YES             NULL                                                                            
Address varchar(255)    YES             NULL                                                                            
PhoneNumber     varchar(20)     YES             NULL                                                                    

Orders:
=======
Field   Type    Null    Key     Default Extra                                                                           
OrderID int     NO      PRI     NULL                                                                                    
CustomerID      int     YES     MUL     NULL                                                                            
OrderDate       date    YES             NULL                                                                            
TotalCost       decimal(10,2)   YES             NULL                                                                    
Status  varchar(20)     YES             NULL                                                                            

OrderItems:
============
Field   Type    Null    Key     Default Extra                                                                           
OrderItemID     int     NO      PRI     NULL                                                                            
OrderID int     YES     MUL     NULL                                                                                    
ProductID       int     YES     MUL     NULL                                                                            
Quantity        int     YES             NULL                                                                            
UnitPrice       decimal(10,2)   YES             NULL                                                                    

Products:
=========
Field   Type    Null    Key     Default Extra                                                                           
ProductID       int     NO      PRI     NULL                                                                            
Name    varchar(255)    YES             NULL                                                                            
Description     varchar(255)    YES             NULL                                                                    
Price   decimal(10,2)   YES             NULL  


*/

use fs;
-- Write your query below.
select distinct c.name
from Customers c
join Orders o on c.CustomerID = o.CustomerID
join OrderItems oi on o.OrderID = oi.OrderID
join Products p on oi.ProductID = p.ProductID
where p.price = (select max(price) from Products);