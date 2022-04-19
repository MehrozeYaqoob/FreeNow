#Task 1: Loading data and displaying it
Write an app containing a list. The list should show all the vehicle data in the bounds of Hamburg (53.694865,
9.757589 & 53.394655, 10.099891).
Use this endpoint to get the vehicles: https://fake-poi-api.mytaxi.com/?p1Lat={Latitude1}&p1Lon={Longitude1}&p2Lat={Latitude2}&p2Lon={Longit
ude2}

#Task 2: Google Maps showing the vehicles
Implement a map Activity/Fragment with Google Maps. Show all available vehicles on the map. Use the bounds of the map to request the
vehicles.
The map should zoom and center on a specific vehicle when it is selected in the previously implemented list.


### Engineering Comments

- Recyclerview for showing list of vehicles
- Coroutines for thread management
- MVVM architecture pattern
- View Data binding
- Roboto-bold for font
- Koin Dependency Injection (Dagger was unable to create component on my machine, Investigated for 3 hrs and then applied koin di)
- Mappers for converting API model to UI model
- SOLID principles applied
- Retrofit as networking library with GSON converter
- Unit cases for Task 1 added

### Decision Points

- Could have used a single model for API and UI but learned about having separate models for better scalability
- Assumed all the attributes of json object are not null, which is why all elements in Model classes are not null
- Used DiffUtil rather than notifying data set changes, for better performance
- Tried to make complete project as modular as possible.
- Created separate library for utils so it can be reused
- Used ViewHolder pattern with recycler adapter for increased scalability and to comply with Google standards

### Improvements for Future

- For multiple brands from single code base, we would need multiple flavors, that would be first thing to do
- Add pipeline files (bitbucker-pipeline.yml | bitrise) to trigger CI process
- Write more Unit cases for presentation and business logic
- Setting up feature flags to do A/B test (fun with flag)
- Implement caching strategy
- Make UI a bit more nicer than it is now
- Add Timber for log management of crashes and ANR's
- Organize gradle dependencies
- Segregate task1 into more modules to increase or speed up build process
- Adding proguard or R8 to reduce for optimized executable 
