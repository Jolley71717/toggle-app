# Toggle Spring Boot Application
> Modular Toggle Proof of Concept with a touch of OpenShift deployment methodologies.

The goal of the application is to show a way a developer can disable and enable features 
of a Spring Boot application using new methods to the organization.

It does contain a little bit of OpenShift deployment testing as well as some QR Code generation.

## Installing / Getting started

Required frameworks and recommended versions and on your PATH
* [Apache Maven 3.x+](https://maven.apache.org/)
* [Java JDK 8+](http://www.oracle.com/technetwork/java/)

Ensure each is the correct version and is on your PATH.

```shell
mvn -version
java -version
```

## Developing

Here's a brief intro about what a developer must do in order to start developing
the project further:

```shell
git clone https://github.com/bradvillamtgov/toggle-app.git
cd toggle-app
mvn package
java -jar target/toggle-<version>-exec.jar
```

* Above clones the repository down from the repo.
* Browser to the app directory.
* Run the maven package command to create the jar file to execute.
* Run the application using java, this will run an embedded Tomcat Server instance with the application.
* Open a browser and head to [http://localhost:28273/](http://localhost:28273/)

## Features

The main point of the application is to be able to enable and disabled modules within the application.

* Open the toggles page to turn off modules. This will disable the entire module from being viewable.
* The application also has the ability to generate QR codes in the software module.
* This application can also be deployed via OpenShift. Additional Information can be found [here](https://blog.openshift.com/using-spring-boot-on-openshift/).

## Contributing

When you publish something open source, one of the greatest motivations is that
anyone can just jump in and start contributing to your project.

These paragraphs are meant to welcome those kind souls to feel that they are
needed. You should state something like:

"If you'd like to contribute, please fork the repository and use a feature
branch. Pull requests are warmly welcome."

If there's anything else the developer needs to know (e.g. the code style
guide), you should link it here. If there's a lot of things to take into
consideration, it is common to separate this section to its own file called
`CONTRIBUTING.md` (or similar). If so, you should say that it exists here.

## Links

Even though this information can be found inside the project on machine-readable
format like in a .json file, it's good to include a summary of most useful
links to humans using your project. You can include links like:

- Repository: https://github.com/bradvillamtgov/toggle-app
- Issue tracker: https://github.com/bradvillamtgov/toggle-app/issues
  - In case of sensitive bugs like security vulnerabilities, this application is used for 
    learning purposes. If you run into any issues, there is a high likely hood that it will not be addressed.