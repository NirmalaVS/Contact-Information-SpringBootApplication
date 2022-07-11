Contact-Information is spring boot application which can create,delete,update,partially update 
and get all the contact information.

This application can be run by using maven build.

Prerequisite to run the project is JDK, Eclipse and chrome to view the output.

SOFTWARE VERSIONS - Java 11, Spring boot version - 2.7.1 

Spring boot application runs in default port:8080

PROCEDURE TO RUN THE APPLICATION
	1.Once the application is imported in eclipse
	2.Build the application by right clicking the pom.xml file 
	3.Click on Run as, in Run as give the maven build option
	4.Maven build pop up opens and provide "install" in goals option
	5.If you are building it for first time the packages will take time to download and test cases will also be executed.
	6.If you like to skip test suite, in the maven build pop up you will have check box to skip the test case.
	7.Once the build is success, open the ContactInformationApplication.java file.
	8.Right click on the page once the file is open you will be provided with Run as option click spring boot application(This option will
	be provided if you have spring suite installed in eclipse) else you can click run as java application.
	9.Once the application is started.
	10.Use the below URL to check the health status of contact-information application 
				 http://localhost:8080/actuator/health 
				 
	11.Use the below URL to check the Request and Response format 
			http://localhost:8080/swagger-ui/index.html
			
	12.The h2 database used can be viewed with below url
			http://localhost:8080/h2-console
			
	13. Screenshots of the Test case executed are attached in the git hub link.
		https://github.com/NirmalaVS/Contact-Information-SpringBootApplication
	

FEATURES OF THE APPLICATION
	All the CURD operations are performed.
	

DEVELOPER 
	Nirmala Sivakumar - nirmalasivakumar5@gmail.com
	