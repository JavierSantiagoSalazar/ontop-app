# ontop-app

## Description

It is an application developed as a business case for the company OnTop .This app is developed in Kotlin for Android Native from Android 7.0 (Nougat).

- The project was developed in Android Native with Kotlin using.
- Modularization by layers and architecture with Clean Architecture.

The app has the following modules:

- app: The main module.
- data: Abstractions of DataSources and the Repositories.
- usecases: All the interactors/usecases in the whole app.
- domain: App business logic.
- testShared: Domain models for UnitTest.
- appTestShared: Fake modules and helpers for Instrumentation tests.
- buildSrc: Module for the organization of dependencies.

## Functionality:

OnTop has three main functionalities:

1 - Master view detail view application in which a list of movies and their details will be shown.

2 - A view where you can enter a phrase and a transformation will be done to UpperCamelCase

3 - A random password generator.

## Clean Arch Layers:

![Full CleanArchitecture](https://user-images.githubusercontent.com/100961042/236562750-18dc2622-89c4-439f-87f0-ccb7c65fe221.png)

But in the case of android development I use this more abstracted and suitable form for development

![Fluend Clean](https://user-images.githubusercontent.com/100961042/236562816-881dc805-a1ed-4253-83a3-b3a835810e82.png)

- [x] Presentation/Framework: A layer that interacts with the UI.
- [x] UseCases: All app interactors.
- [x] Data: Abstract definition of all the data sources and repositories
- [x] Domain: Contains the business logic of the app.

- Model-View-ViewModel as pattern for the presentation layer.
- Gradle dependencies are organized with buildSrc and KotlinDSL and updated manually
- Repository pattern for the data layer.
- Coroutines with Flow for the background tasks.
- Dagger Hilt for Dependency Injection.
- Retrofit and OkHttp to consume the API Rest.
- Room to persist data locally.
- Navigation is done with Navigation Component.


## Testing

- Unit tests were made with Mockito and Turbine.
- UI tests were made with Espresso and Hilt, the UI tests do not consume network services, all the services are mocked.
- Page Object Pattern was implemented to UI tests so, the UI test are easier to read, and the implementation is encapsulated in the Pages.

## CI/CD

The Github Repository has one pipeline with Github Actions that checks the unit test, is executed when a PR is raised
pointing to main branch and when a merge is done to main branch

## TO IMPROVE

- Use a default image when Glide don't charge the url
- Migrate to Jetpack Compose.
- improve the UI of the recycler items.
- Find a way to maintain one instance of the SnackBar to fix the error of having multiple SnackBars when there is connectivity error.
- More unit tests.
- More UI test
- Create the integration tests.

### Dependencies used

- [x] Kotlin v1.9.0
- [x] Dagger Hilt v2.48
- [x] Retrofit2 v2.9.0
- [x] OkHttp3 v4.11.0
- [x] Coroutines v1.6.4
- [x] Glide v4.16.0
- [x] Android Navigation v2.5.3
- [x] Safe Args v2.5.3
- [x] Mockito v4.1.0
- [x] Turbine v0.12.3
- [x] Espresso v3.5.1
- [x] Room v2.6.1
- [x] Arrow v1.1.5

## Requirements

- [x] Minimum version: Android 7 - API level 24
- [x] JAVA 17 in project structure

### Made by Javier Santiago Salazar - javier452011@hotmail.es - www.linkedin.com/in/javier-santiago-salazar/
