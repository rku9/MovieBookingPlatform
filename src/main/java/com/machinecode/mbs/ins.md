 * We have a City/Region.
* This will have the list of theatres.
* **Theatres** will have the list of screens.
* **Screen** can have a list of shows.
* **Show** will have the all the details of the movie, time, theatre etc. But a complication may arise wrt seats.
* Show won't have the list of seats. It will access those from the Screen.

* **Seat** will have the identifier like number, row, column etc. It will **NOT** have the status as the status is
  not just a property of a seat but also a show. A seat is booked doesn't mean the seat is booked for all the show.


* **IMPORTANT**
* Superficially we can decide on having a list of seats for the shows.
* For every show we will need to store the status of the seats.
* But we need some kind of mapping table to know if the seat is available for the show or not.
* For this, we create ShowSeat class. It will have attributes Show, Seat and the Status.

* **Price** will be specific for a show and a set. This can be stored in the ShowSeat table but that table will have
  multiple entries of the shows and the seats and for every row we would be storing the same value. This is redundant
  and not optimal.
* For this we create a new table/class ShowSeatType and this will have the columns of show_id, seat_type_id and the price.