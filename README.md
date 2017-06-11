# WAA_JSF_PROJECT DOCUMENTATION
BaseUrl : http://localhost:8080/airlinesWebApp/rs/

#AirLineService:
1.To get list of Airline
    Method: GET
    URL : http://localhost:8080/airlinesWebApp/rs/airline/
    Response : [{"id":2,"name":"SkyTeam"},{"id":10,"name":"oneworld"},{"id":17,"name":"North Star"},
                {"id":1,"name":"Raju"},{"id":5,"name":"sonam"}]

2.To get Airline by Id
    Method: GET
    URL : http://localhost:8080/airlinesWebApp/rs/airline/{id}
    Response : {"id":1,"name":"Raju"}
    
3 To Create Airline
    Method: POST
    URL : http://localhost:8080/airlinesWebApp/rs/airline/create
    Parameter : {"name": "sita"}
    Response :  {"id": 1251,"name": "sita"}

4 To Update Airline
    Method: PUT
    URL : http://localhost:8080/airlinesWebApp/rs/airline/update
    Parameter : {"id": 1251,"name": "Gita"}
    Response :  {"id": 1251,"name": "Gita"}
    
4 To Delete Airline
    Method: POST
    URL : http://localhost:8080/airlinesWebApp/rs/airline/delete
    Parameter : {"id": 1251,"name": "Gita"}
    Response :  { "status": "Sucess","message": "SucessFully deleted"}

5 To get List of  by Airline name
    Method : GET
    URL : http://localhost:8080/airlinesWebApp/rs/airline/flight/oneworld
    Response : [{"id":10,"name":"oneworld"},{"id":10,"name":"oneworld"},{"id":10,"name":"oneworld"},{"id":10,"name":"oneworld"}]
    
#AirPlaneService:
#AirPortService:
#FlightService:
These all are similar to airport service

Screenshot for JSF portion : https://drive.google.com/drive/folders/0B30dqiiblMLwQ2RBSjhMMTV0OEU?usp=sharing
