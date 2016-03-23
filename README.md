# Hello My Name Is ________
A talk given at MelbJVM November 2015 about producing our nametags using 
 [Docmosis Cloud](https://www.docmosis.com/try/cloud.html).

- [Daniel Fullarton](https://github.com/linead)
- [Kon Soulianidis](https://github.com/neversleepz)

## Why Nametags
Story here [WHY_NAMETAGS.md](WHY_NAMETAGS.md)

## Requirements
- [Docmosis Cloud (free) account](https://www.docmosis.com/try/cloud.html)
- [Meetup api key](https://secure.meetup.com/meetup_api/key/)

Put these in __src/main/resources/application.properties__

- Avery white address labels, 14 per page. For software code L7163 / reorder code 959089.
-- Feel free to change `template5.docx` referenced in `src/main/java/io/github/linead/nametags/Docmosis.java` 
   to a different file of your own template choosing.

Architectures 
--------------
- The app is built on Spring Boot
- Maven
- Java 8

Setup
------
1. Update keys in application properties as described in **Requirements**
2. Upload template to [Docmosis Cloud](https://accounts.docmosis.com/accounts).  See __My Templates__ tab.  
   Use name `template5.docx` or change code in the following step.
3. Update `template5.docx` referenced in `src/main/java/io/github/linead/nametags/Docmosis.java` 
   to a different file of your own template choosing to match what you uploaded.
4. `mvn clean install` or `mvn package` if you wish to produce an executable jar
5. Otherwise in your IDE, run src/java/io/github/linead/nametags/Nametags.java

Endpoints
----------
### Events `/events`
Lists upcoming MelbJVM events and event IDs required for following endpoints

### Attendees `/attendees?eventId=XXXXXX`
Returns a list of attendees who have RSVPd YES to the meetup

### Nametags `/nametags?eventId=XXXXXX`
Generates the pdf using the template  


