# Vama Challenge
The purpose of this application is to show the top 100 ranked albums in US according to [RSS Feed Generator](https://rss.applemarketingtools.com/)

### 📱 App Information
App will show a two column grid with the top 100 ranked albums in the US. Each item will contain album's and artist's name. If they are tapped, a new Detail screen will appear showing previous information along with genre, release date, copyright and an "Open iTunes" button. This button will open device's browse and navigate iTunes web page.

### 👩‍💻 Developer Documentation
1. When opening the app, it will retrieve album list internally saved.
2. If there are no albums saved, they will be fetched performaning a network call.
3. If there are albums, they will be shown and then, will be updated via network connection.
4. If app has no elements to show (internal DB is empty and network call failed), a network error connection screen will be shown.
5. Besides that, if saved elements are shown and network connection failed, a `Toast` message will be shown to the user.
6. When selecting an element of the grid, a new screen will be opened and more information will be displayed.
7. On this Detail Screen, there's an "Open iTunes" button, which will redirect to browser and navigate to that album's iTunes web page.

#### 🔧 Implementation
Project is organized taking care of Clean Architecture organization through `Presentation`, `Domain` and `Data` layers:
- **Presentation**: This layer will respect MVI pattern, where each screen has an `Intent` and a `State`. In Home screen, `StateFlow` was implemented with the purpose that screen reacts when data inside ViewModel changes. The only purpose of `SharedFlow` is to show the `Toast` error message. Shimmer effect was implemented using an external library ([Shimmer for Jetpack Compose & Compose Multiplatform](https://github.com/valentinilk/compose-shimmer))
- **Domain**: The only purpose of this layer is to map network and DB models to `Album` and `Genre` ones, thus, they are consumed by the screen.
- **Data**: This layer is divided in `Local` and `Remote`. Inside `RemoteDataSource`, app will use a `Retrofit` instance to fetch album list. On the other hand, after retrieving that list, inside `LocalDataSource`, a `Realm` DB is implemented and will save internally every album that the `RemoteDataSource` has fetched. The `id` that was obtained through the API is the one that will be used as a primary key. If the element, already exists, it will be replaced. 

As a general consideration, `Hilt` was implemented as a dependency injection to facilitate the features implementation.

### 🎬 Recording

![video](https://github.com/user-attachments/assets/5e3c12da-e538-45a2-8b3d-139bcb0edc99)
