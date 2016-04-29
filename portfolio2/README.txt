Group 40 Portfolio 2 README
=============================================

Welcome to Group 40's Portfolio 2. Also known as "Disney Trivia So Hard You Will Cry" for use by the ISU CXC at Anime Detour 2016.
For a full working demonstration, please go to nopirateip.ddns.net/CXC/

The code
--------

The code is split up into a few folders for organization.  All the HTML pages can be found in CXC.  The other pages are labeled according to what type of files are contained within.
All folders are expected to be placed directly in the root directory of the Apache server.  Please make sure the server has the ability to connect to the internet as we are using a
CDN for the Bootstrap and jQuery libraries.  Note also that the PHP code attempts to make a connection to a local MySQL database using a set username and password.  The username and
password will need to be changed to the appropriate values for your machine before run locally.

Prereqs for set up
------------------

This project makes use of a basic AMP server model.  Required components include an Apache HTTP server, a MySQL server, and a PHP interpreter.  This project also makes use of a server
model named Memcached.  Please see memcached.org for instructions on installing Memcached on your machine.  The provided SQL file will create the necessary tables in the MySQL database.
To fill the Questions table in the database Java JDBC is used.  Please make sure before using the provided jar file that the Java 1.8 JRE is installed on your machine.
The provided text file named "questions.txt" should be in the same folder as the jar when executed. The jar file takes in parameters for the file name, username and password for the MySQL database,
and the server address for the MySQL database.  Syntax for use is:

	java -jar populateDatabase.jar <questions file> <database username> <database password> <database server>
	Note: Server location should be provided in the format //<server>/<database> i.e. //localhost/CXC

Running the game
----------------

This project is intended to have at least 2 users, a player and a game host.  To start the game open your web browser and navigate to the index file in the CXC folder.
On this page, enter a username and click "Submit".  This will bring you to the questions page.  Open the Projector.hmtl file in another tab of your browser.  Click "Next Question" to begin.
When the "Next Question" button is pressed, the database will be queried for the next question and the player and host pages will be updated.  Continue through the questions to test your Disney
knowledge and at the end the host page will allow you to review the the answers to the questions.  At the end of the review section, the first, second, and third place winners will be displayed.
