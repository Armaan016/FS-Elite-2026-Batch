/*
Write a MongoDB query to identify the student with the highest average score 
specifically for the "Python" subject.

Collection: 'stucollection'

Reference Document:
----------------------
{
    "student_id": "S1001",
    "name": "Alice Johnson",
    "age": 18,
    "contact": {
      "email": "alice@example.com",
      "phone": "123-456-7890",
      "address": {
        "street": "123 Maple St",
        "city": "New York",
        "state": "NY",
        "zip": "10001"
      }
    },
    "enrolled_courses": ["Java", "Python"],
    "exams": [
      {
        "subject": "Java",
        "scores": [
          { "type": "quiz", "score": 80 },
          { "type": "midterm", "score": 75 },
          { "type": "final", "score": 90 }
        ],
        "passed": true
      },
      {
        "subject": "Python",
        "scores": [
          { "type": "quiz", "score": 70 },
          { "type": "midterm", "score": 65 },
          { "type": "final", "score": 85 }
        ],
        "passed": true
      }
    ],
    "skills": ["Java", "Spring Boot"],
    "guardian": {
      "name": "Robert Johnson",
      "relation": "Father",
      "contact": "robert.j@example.com"
    }
}

Sample Output:
---------------
[                                                                               
  {                                                                             
    student_id: 'S1006',                                                        
    name: 'Sophia White',                                                       
    avgPythonScore: 87.67                                                       
  }                                                                             
] 
 
Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
In db.<collection>.find():
    db -> Refers to the database.
    <collection> -> Your collection name
    find() -> Method to write the selection and projection part of the query.

*/

printjson(
    db.stucollection.aggregate([
        { $unwind: "$exams" },
        { $unwind: "$exams.scores" },
        { $match: { "exams.subject": "Python" } },
        { $group: { _id: "$name", student_id: { $first: "$student_id" }, avgPythonScore: { $avg: "$exams.scores.score" } } },
        { $project: { _id: 0, name: "$_id", student_id: 1, avgPythonScore: { $round: ["$avgPythonScore", 2] } } },
        { $sort: { avgPythonScore: -1 } },
        { $limit: 1 }
    ])
)
