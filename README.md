# Yuxi technical test

## Part 1: Arrange photos

Current project reads a list of photos description. Those photos are grouped by location, then are sorted by the time they were taken and finally renamed using location followed by a consecutive number starting in 1 for each group.

Please set an environment variable named **YUXI_PHOTOS** which should point to the location of the file **conf.properties**.

To run the server execute the method main in **TechnicalTestApplication** class. Server will listen at port 8080 and provides /photos endpoint for the photos arrange service.

### conf.properties

This file is used to configure server at start up with two properties:

- **photos_file_uri**: Which is the location of the photos file.
- **date_format**: Format used to convert string representation of a date to Java equivalent date object.

## Part 2: Sort match result

Folder SQL_test contains the following files:

- schema.sql: Contains DDL to create the model tables.
- data.sql: Script that populates tables with example data.
- query.sql: Solves the requested query in point 2.
