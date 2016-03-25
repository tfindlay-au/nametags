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

How you supply these too your application is up to you, you could : 
- Put these in __src/main/resources/application.properties__
- Supply an application properties next to the war
- Put them in as environment variables

Here's what you want 
meetup_api.key=See above
docmosis_api.key=See above
security.username=a username of your choice
security.password=a password of your choice

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
4. Run it in one of 3 easy ways!
   - run the executable war with java -jar
   - stick the war in tomcat
   - run it from maven with mvn spring-boot:run

Endpoints
----------
### Events `/events`
Lists upcoming MelbJVM events and event IDs required for following endpoints

### Attendees `/attendees?eventId=XXXXXX`
Returns a list of attendees who have RSVPd YES to the meetup

### Nametags `/nametags?eventId=XXXXXX`
Generates the pdf using the template  


