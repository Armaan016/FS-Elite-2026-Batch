/*
Write a MongoDB aggregation query to find the average delivery time(in minutes) 
for each restaurant.
- Display restaurant names and their average delivery time.
- Round the average delivery time to two decimal places.
- Sort the data by Restaurant ID.

Sample output:
--------------
[  
  {                                                                             
    _id: 'R102',                                                                
    restaurant_name: 'Chutneys',                                                
    avgDeliveryTime: 32345.43                                                   
  },
  {                                                                             
    _id: 'R105',                                                                
    restaurant_name: 'MTR',                                                     
    avgDeliveryTime: 18141.18                                                   
  },                                                                            
  {                                                                             
    _id: 'R106',                                                                
    restaurant_name: 'Empire Restaurant',                                       
    avgDeliveryTime: 17065.45                                                   
  }
]


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
        db -> Refers to the database.
        <collection> -> Your collection name
	
*/

printjson(
    
)