# Project Front-end STARTUP.md
---
## Information
> These different installations will allow you to setup our project on your own machine.
> **NOTE**: We used VScode (Visual Studio Code) as our IDE for our Front-end portion of the project however, it is completely up to whichever IDE you prefer.
## Necessary Technology Installation

### **VScode**: 
	Visual Studio Code is the IDE we used to work on our Front-end. 
	VScode is wonderful for HTML and CSS implementation, which we used in our project.
### **React**:
	React Libraries are needed in order for our interactive UI to function. 
	In our /e-commerce-frontend-main/ we have a package.json that includes all the dependencies we used for our program.
### **Node.js**:
	Node.js is important for asynchronous programming be sure to install the 16.17.0-alpine version of node. 
	This will allow for the command 'npm start' within VScode's console in the /e-commerce-frontend-main/ folder.
### **Docker**:
	Docker allows the capture of an image and maintaining the development environment. 
	This helps solves the issue of different computers having different versions of programs such as node.js, react etc. 
	In order to correctly utilize docker with our application, please refer to the docker-compose.yml in our 
	/e-commerce-frontend-main/
	Along with the yaml file, there is a Dockerfile that sets up the container for our application.
