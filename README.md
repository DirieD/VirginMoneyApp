# RedditReaderApp
RedditReaderApp is an Android application that retrieves a list of articles from Reddit. It uses pagination to automatically propogate new stories to the users whenever they read the bottom of the current recycler list.

![Alt text](app/docs/images/articles_list_screenshot.png?raw=true "Articles List Screenshot") ![Alt text](app/docs/images/custom_tabs_screenshot.png?raw=true "Custom Tabs Screenshot")

# Software Development Approach
This software was developed using a Kanban approach

# Architecture Design
The Project follows a MVVM with Repository pattern architecture. This architecture was chosen for:
- Separation of Concerns that provides a way to testing the architecture components in isolation and allows for the View classes to be updated without modifying the ViewModel classes.
- Resilience to configuration changes allows the ViewModel classes to store UI data that would otherwise be lost on screen rotation or activity lifecycle changes.
- Communication between fragments using a ViewModel class removes the need for fragments to communicate via an Activity using callbacks.

The View classes use data binding to communicate updates to their respective ViewModel classes. The ViewModel classes communicate with a Repository class using LiveData. This is then passed back to the View classes observing this LiveData. The Repository class communicates with a RESTful API using Retrofit and caches the response to a local Room database.

![Alt text](app/docs/images/mvvm_architecture.png?raw=true "MVVM Architecture")

# Libraries Used
- Koin to provide constructor dependency injection to classes in the application
- Coroutines to leverage Flow observer pattern
- Room to store the article responses from Retrofit.
- Data binding to bind the inflated layout files to instances running in the application code.
- Retrofit to provide access to the backend API endpoint

