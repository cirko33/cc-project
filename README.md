# Book Rental Application with Microservices Architecture

This project implements a book rental application with microservices architecture, consisting of four components: Belgrade Library, Novi Sad Library, Nis Library, and Central Library.

## Functionality Description:

### Member Registration:
- After entering necessary information in the registration form (name, surname, address, and ID), a request is sent to the Central Library application to check if the member with the given ID is already registered in the Central Library's database.
  - If the member does not exist in the Central Library's database, they are registered, and a successful registration response is sent to the requesting city library application.
  - If the member already exists in the Central Library's database, the registration request is declined, and an unsuccessful registration response is sent to the requesting city library application.

### Book Rental:
- After entering required information in the rental form (book title, author, ISBN, rental date, and member ID), a request is sent to the Central Library application to check if the member with the given ID has already borrowed the maximum of 3 books.
  - If the member has borrowed less than 3 books, information about the new rental is recorded in the Central Library's database, and a successful rental response is sent to the requesting city library application. The information about the rental is also stored in the city library's database.
  - If the member has already borrowed 3 books, an unsuccessful rental response is sent to the requesting city library application.

## Additional Explanation:
- Central Library's database stores information about registered members.
- City Library's database stores information about the rental of specific books by specific members.

## Requirements:

### Part A:
- Containerize the application using the Docker Compose client.
- Create a GitHub Action that builds the container image and pushes it to Docker Hub.
- In the Docker Compose file, configure the application to pull the image from the Docker Hub repository instead of using a local Dockerfile.

### Part B:
- Deploy the application on a Minikube local Kubernetes cluster.
- Place each application with its database in a separate Pod.
- Disable communication of individual Pods with the external world.
- Utilize Persistent Volumes for permanent storage of data from individual databases.
- Implement Ingress as the gateway for the application.

