# Code Challenge: SecureNote

In this technical test you can create your notes and store them locally. It is super safe as it uses biometric authentication to access the app.

![alt text]()
![alt text]()

### Libraries used

- Navigation (Fragment transitions)
- View Binding (Bind views)
- ViewModel (Store and manage UI-related data)
- LiveData (Observable data)
- Kotlin Coroutine (Light-weight threads)
- Hilt (Dependency Injection for Android)
- Retrofit (HTTP client)
- Multi Module App
- Jetpack Compose
- Room Database

## Modules

The project contains the following modules:

- App ( Initial module with the main activity of the project. Because single activity was used on the project )
- Base ( Contains the navigation between the UI modules and generic classes )
- Data ( Used for making request to the local BD)
- Model ( Declares all the entities used in the project )
- OnBoarding( Onboarding for new users )
- Home ( Home screen to see items )
- Detail ( Detail screen of the note. Allows to edit and delete )

## Architecture

This app was build using MVVM and following the guidelines explain [here](https://developer.android.com/jetpack/docs/guide). This app also makes use of the pattern single activity.

## Import data

You can import data from a JSON file. It needs to have the following format:

```json
[
    {
        "title":"Lorem ipsum",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "image": "image_url",
        "creation_date": "2023-07-31T23:17:14.225+00:00"
    }
]
```