# androidMovieViewerApp
Created movie viewer application based on the www.themoviedb.org

App has segment control on the very first screen (All/Favourites).

All list contains only top rated films - https://api.themoviedb.org/4/discover/movie?sort_by=popularity.desc

By switching between buttons list content are changed accordingly(all and favourites).

On list item click movie details are opened with some film details

There is a poster for the film and a button which indicates if this film is added to favourites list or not.

Favourites movies must be stored only locally, there is no need to perform any network requests for any favourites related operations.
