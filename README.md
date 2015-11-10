# Hello My Name Is ________
A talk given at MelbJVM November 2015 about producing our nametags using 
 [Docmosis Cloud](https://www.docmosis.com/try/cloud.html).

- [Daniel Fullarton](https://github.com/linead)
- [Kon Soulianidis](https://github.com/neversleepz)

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


Why Nametags
=============

## We are developers
This presentation is about software developers, about automating things and about document generation.

Developers have a stigma of being antisocial.  Java Developers in particular can remember really long class names like
MustacheResourceTemplateLoader, if I forget the name, I can use my IDE to help me remember based on the initials.

### IRL, there is no control space or command N
If I look at someone I'm having a direct conversation with, and whilst I am talking to them, I forget their name, I have
limited options

- put some syllabyls together from what bits of the name I remember and then muffle them in a low volume
- try not to use their name in gramer as much as possible hoping that awkward moment doesnt occur & I can get away with 'hey mate'
- admit it, I forgot, and ask

To help alieve this anxiety, MelbJVM have used nametags for a long time now.

Each month we get a list of attendees from Meetup.com to generate the nametags.  There is a bit of effort that goes into this

 i) Have to find someone to write out the nametags for all attendees
ii) Make sure this is done close enough to the meetup date as possbile to handle last minute changes.

### Not foolproof

There is a third party or two involved.  Maybe a sponsor gets their receptionist to type this out.

Sometimes we
- forget to pass the list on
- the person we coax, usually the receptionist of someone we know is off sick

### We are developers
We can automate this!!!
![This cant be hard]
(http://imgs.xkcd.com/comics/engineer_syllogism.png)
