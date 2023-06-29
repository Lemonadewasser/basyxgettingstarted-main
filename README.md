# Getting Started with BaSyx AAS Java-SDK and AAS Components

## Installation

To use the BaSyx AAS Java Software development kit (SDK), you need to install the following software:
- A Java sdk
- Maven
- Git
- An IDE (e.g. Visual Studio Code)

If you want to use the BaSyx AAS Components or build your own containers, you need to install the following software:
- Docker

In case, you want to build docker containers from written code with the Java-SDK, an installation of Docker is also required. 

The following instructions present one possibility to use the BaSyx AAS Java-SDK and AAS Components, but there are also many other possibilities. The decision for the following tools is based on the fact that they are free and easy to use.

### Installations for the java-sdk

As mentioned earlier, a valid installation of a Java SDK newer than version 11 is required. A working open-source version can be downloaded here as .zip: https://jdk.java.net/19/ 

For the Java SDK is no installation needed. Just unzip the downloaded files and move the uncompressed folder to a desired location. If, e.g. the the downloaded and unzipped folder is named ***jdk-19***, the folder can be moved to `C:\Program Files\Java\` to have the following path `C:\Program Files\Java\jdk-19`.

Maven is a build automation tool used primarily for Java projects. It is responsible to manage the BaSyx dependencies and build our project. It is also available as open-source and can be downloaded here as bin.zip version: https://maven.apache.org/download.cgi

Similar to the Java SDK, Maven does not need an installation. Just unzip the downloaded files and move the uncompressed folder to a desired location. If, e.g. the the downloaded and unzipped folder is named `apache-maven-3.6.8`, the folder can be moved to `C:\Program Files\Maven\` to have the following path `C:\Program Files\Apache\apache-maven-3.6.8`.

Now, the system variables need to be adjusted in order to use java and maven. Follow this instruction to add java to the path: https://mkyong.com/java/how-to-set-java_home-on-windows-10/. Follow these instructions starting from step 2 to add maven to the path: https://phoenixnap.com/kb/install-maven-windows.

Make sure that the system variables are adjusted correctly by opening a command prompt and typing `java -version` and `mvn -version`. The output should look like this:

Java:

    C:\Users\user>java -version
    java version "19.0.1" 2022-10-18
    Java(TM) SE Runtime Environment (build 19.0.1+10-21)
    Java HotSpot(TM) 64-Bit Server VM (build 19.0.1+10-21, mixed mode, sharing)

Maven:
    
    Apache Maven 3.8.6 (84538c9988a25aec085021c365c560670ad80f63)
    Maven home: C:\Program Files\Maven\apache-maven-3.8.6
    Java version: 19.0.1, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-19
    Default locale: de_DE, platform encoding: UTF-8
    OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"

Lastly, download and install git from here: https://git-scm.com/downloads. You can use the default settings in the installation wizard, but you can select git bash if you prefer command line git commands.

As an IDE, I recommend Visual Studio Code (VS Code). It is free and easy to use. You can download it here: https://code.visualstudio.com/download. If you do so, you need to install the following extensions in VS Code:

- Required: Extension Pack for Java (Maven extension is automatically installed with it)
- Optional but recommended: Docker (for your own docker builds), GitLens (for advanced git integration in VS Code), GitHub CoPilot (for AI powered code suggestions, free student license available here: https://education.github.com/).

### Installations for the AAS Components or own docker builds

Here, just install Docker Desktop from here: https://www.docker.com/products/docker-desktop. 

## Useage of the BasyxGettingStarted

Just clone the repository from here with: 

    git clone git@git.scc.kit.edu:zh9173/basyxgettingstarted.git

Then open it in VisualStudioCode and start exploring. 

## Folder Structure for the Java SDK with a Maven build

The workspace contains two folders by default, where:

- `src`: the folder to contain written source code
- `target`: the folder to contain compiled code

In order to have a working maven project, you need a `pom.xml` file in the root folder of your project. This file contains all the information about the project and is responsible to manage the dependencies. A working pom.xml for the Java-SDK can be found already in this folder. Additionally, Maven requires that the source code has to be in defined folders in the src folder. For now, just put all the written code in `src/main/java`. 

## Use of the Java SDK and make a Maven build

in the folder `src/main/java/AASExamples`, you can find the file `ProductAAS.java`. This file contains a simple example of the use of the Basyx Java SDK. The example creates a simple AAS of a Product with submodels for geometry, processes and cost. 

To run the example, you need to get the BaSyx dependencies at first. This can be done by opening a command line in the root folder of the project and running the following command: 

    mvn install

If you are using Visual Studio Code, you can open the integrated terminal and run the command there or use the maven extension and select `install` under Lifecycle.

After the dependencies are installed, you can run the example by opening it in Visual Studio Code, right-clicking on the file and selecting 'Run Java'.

Your output should look like this:

    20:14:20.954 [main] WARN org.eclipse.basyx.submodel.metamodel.map.qualifier.Referable - The passed idShort Processs 0 is not valid! It has to satisfy the RegEx [a-zA-Z][a-zA-Z0-9_]+
    20:14:20.961 [main] WARN org.eclipse.basyx.submodel.metamodel.map.qualifier.Referable - The passed idShort Processs 1 is not valid! It has to satisfy the RegEx [a-zA-Z][a-zA-Z0-9_]+
    20:14:20.961 [main] WARN org.eclipse.basyx.submodel.metamodel.map.qualifier.Referable - The passed idShort Processs 2 is not valid! It has to satisfy the RegEx [a-zA-Z][a-zA-Z0-9_]+
    20:14:21.016 [main] INFO AASExamples.ProductAAS - Created a servlet for the model
    20:14:21.024 [main] INFO AASExamples.ProductAAS - Created a registry servlet for the model
    20:14:21.024 [main] DEBUG org.eclipse.basyx.aas.registration.memory.AASRegistry - Registered TestProductAAS
    Nov. 27, 2022 8:14:21 PM org.apache.coyote.AbstractProtocol init
    INFORMATION: Initializing ProtocolHandler ["http-nio-4001"]
    Nov. 27, 2022 8:14:21 PM org.apache.catalina.core.StandardService startInternal
    INFORMATION: Starting service [Tomcat]
    Nov. 27, 2022 8:14:21 PM org.apache.catalina.core.StandardEngine startInternal
    INFORMATION: Starting Servlet engine: [Apache Tomcat/9.0.64]
    Nov. 27, 2022 8:14:22 PM org.apache.coyote.AbstractProtocol start
    INFORMATION: Starting ProtocolHandler ["http-nio-4001"]
    20:14:22.194 [main] INFO AASExamples.ProductAAS - HTTP server started

Especially the last line `HTTP server started` is important.

In this example, you can see, how to create assets, asset administration sheels, submodels and submodelElements. All of them are connected to a data structure, describing the product virtually. Lastly a local webserver is started, where you can view the data structure of the aas in the browser by accessing: http://localhost:4001/aasserver/shells/TestProductAAS/aas. 

By accessing this URL, a REST-API is called by a GET request, which returns the data structure of the AAS in JSON format. 

## Use the AAS Components

For now, we started the AAS server and the AAS registry server programatically. However, Basyx comes with handy, preconfigured docker containers, that can be used to run standard AAS servers and AAS registry servers. The only requirement to run them is, that you have docker installed. This makes it especially easy to run the AAS components on a remote server, like the bw cloud (see here for more information: https://www.bw-cloud.org/de/erste_schritte).

To start an AAS server component, start docker desktop at first. Next, pull the AAS server docker image with the following command:

    docker pull eclipsebasyx/aas-server:1.2.0

After the image is pulled, you can start the AAS server with the following command:

    docker run --name=aas -p 8081:4001 eclipsebasyx/aas-server:1.2.0

To see if the server is running, you can open the browser and access the URL: http://localhost:8081/aasServer/shells/. If you see an empty list `[]` the server is running. The specified port 8081 is the port of the docker container. The port 4001 is the port of the AAS server inside the container.

With configuration, an aas server can be started on a defined host, so to be accessible from outside your machine. For more details to do this, see: https://wiki.eclipse.org/BaSyx_/_Documentation_/_Components_/_Context.

Next, we pull the image of the standard AAS registry by running the command: 

    docker pull eclipsebasyx/aas-registry:1.2.0

To start an AAS registry server, you can use the following command:

    docker run --name=aas-registry -p 8082:4000 eclipsebasyx/aas-registry:1.2.0

To check, if it is running properly, test http://localhost:8082/registry/api/v1/registry in the browser. As before, an empty list `[]` is the expected output. Similar to the server, context configuration can be used to make registries accessible from outside. 

## Register an AAS in the Standard Component Registry

Go to the file `ProductAAS.java` and uncomment the following line (line 227):

	// startServerAndConnectToExternalRegistry(fullProvider, productShell);

Then run the script, as before. The uncommented function registers the Productsheel in the previously started AAS registry from the standard component. You can see this by accessing again the URL: http://localhost:8082/registry/api/v1/registry. 

## Push AAS to remote server and registry

Similarly to the previous step, uncomment the folling line (line 229) in the file `ProductAAS.java` and run it again:

	// UseExternalServerAndRegistry(productShell, submodels);

This starts the AAS server on the remote server and registers the AAS in the remote registry. This is for static AAS, that do not contain any dynamic data the recommened procedure. Again, you can viist the AAS in the browser by accessing: http://localhost:8081/aasServer/shells/ on the server and http://localhost:8082/registry/api/v1/registry on the registry. 

## Referencing other AAS

Now, we create a second AAS of a machine that specifies the processes it offers in a submodel and references the product AAS of the currently processed product. The machine AAS is created in the file `MachineAAS.java`. There, a local http server is started with an inMemory registry. 
Run the example and access the AAS in the browser by accessing: http://localhost:4009/aasserver/shells/TestMachineAAS/aas. 

To have a closer look at the submodels and the associated values, check the submodels get request: http://localhost:4009/aasserver/shells/TestMachineAAS/aas/submodels

There, you can see that in the CurrentProductReferenceSubmodel a reference to the URI of the product AAS is stored. With references, AAS on different servers can be connected, to store data decentrally. 

## Build a docker container from the Java SDK

Next, we want to build a docker container of a small service that we created in the `SubmodelService.java` file that looks the demonstrates how remote services can be added to submodels and how set and get operations work. 

You can test it by running the file and accessing:
http://localhost:4002/submodelserver/ScanForProcesses/submodel

At first, have a look at the "Examplevalue" Property of the Service by accessing:
http://localhost:4002/submodelserver/ScanForProcesses/submodel/submodelElements/Exampleproperty

You can see that the value is set to "Initial". To write a new value, we can use the set operation, that is implemented in the `SubmodelSerice.java` file in the function `setExamplePropertyValue`.

We can have a look at the operation by using a GET-request in the browser under the following adress:
http://localhost:4002/submodelserver/ScanForProcesses/submodel/submodelElements/setExamplePropertyValue

There, you can see the operation description and the input and output parameters (One Input Paramter: "newPropertyValue", no output). 

We can use this operation by using a POST-request. This is not possible in the browser and other tools are needed. A recommended option is the VSCode extension "Thunder" which allows to send REST-Requests directly from VSCode. 

We send the POST-request to the following adress: http://localhost:4002/submodelserver/ScanForProcesses/submodel/submodelElements/setExamplePropertyValue/invoke
and specifiy the following body: `"Hello world"`

The request should return `null`, if everything works fine. After that, we can check the value of the property again by accessing: http://localhost:4002/submodelserver/ScanForProcesses/submodel/submodelElements/Exampleproperty

Now, the value of `Exampleproperty` should be "Hello world".

Since such a service is more easily deployable in a docker container, we want to build this container. 

To do this, just run in the root folder of the project the following command:

    docker build -t NAME_OF_IMAGE .
    
The container can then be started with 
    
    docker run -p 4003:4002 NAME_OF_IMAGE
    
 The build container contains a webserver with a submodel, that can be accessed via http://localhost:4003/submodelserver/ScanForProcesses/submodel. The specifications for the docker build can be found in the Dockerfile in the root folder of the project. The maven dependencies are taken care off in the docker container build itself, by using maven inside the docker container and execute the maven build process there. The associated specifications can be found in the pom_docker.xml file in the root folder of the project. 

 We registered this service at in the registry that we started earlier on port 8082, where we added this service to our product AAS as submodel. However, this submodel is only added to the AAS on the registry, and submodel and AAS run on independant severs.

 If you want to easily transfer the docker container with docker hub to a cloud server, register at https://hub.docker.com/. Make sure, that you're logged in to docker hub in the terminal of your local machine and the bw cloud server. Then proceed with the following steps:

Build container:

    docker build -t DOCKERHUBNAME/NAME_OF_IMAGE . 

Push container to DockerHub:

    docker push DOCKERHUBNAME/NAME_OF_IMAGE 

Pull docker container on remote host:

    docker pull DOCKERHUBNAME/NAME_OF_IMAGE

run the service:

    docker run  -p 4003:4002 DOCKERHUBNAME/NAME_OF_IMAGE

Note, that the expose flag could be required. If the command above does not work, explicitely expose the port 4002 of the container to the outside:

        docker run  --expose 4003 -p 4003:4002 DOCKERHUBNAME/registry_service








