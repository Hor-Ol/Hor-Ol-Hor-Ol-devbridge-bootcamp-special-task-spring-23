# Devbridge Sourcery Academy Bootcamp Spring 23 - Special Task

## Task:

**The task was to create Web Application that should be able to upload csv file with the employees data and then display it on the screen.**

In addition to the initial requirements the following was implemented:

1. Basic unit and integration tests were implemented.
2. OpenAPI documentation describing the REST API controllers is available at: _http://localhost:8080/swagger-ui/index.html_
3. H2 is available at _http://localhost:8080/h2-employee-csv-uploader/_

**Note**: page was designed to take csv data with the employee data in the colums: first_name, last_name, email, department, area. File with the dummy data ("got_employees_data.csv") is attached to the project.

![Format of the csv file with empoloyees data](csv_file_data.png)

## Technologies that should be used:

1. React
2. Spring Boot
3. H2

## Result:

### Page without data:

![EmploTable main page without consumer data](page_without_data.png)

### Page with uploaded data:

![EmploTable with consumer data](page_with_data.png)

## Things to improve:

1. Adding exception handlers for different scenarios.
2. Adding responsiveness for the use with different screen sizes and on different devices.
