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
* This relation will always be @ManyToOne from multi->single.

* **BOOK TICKET USE**
* When trying to get a product from the website, it is recommended for the websites to first check if the product is
  in the inventory and only then accept the payment as this process involves commission from the payment gateways and
  in the case of non-availability, Amazon would have to refund the whole amount and lose out on that extra commission
  amount.
* In this scenario, Amazon would first make sure that it has the product in the inventory and only then take the user
  to the final payment page.
* At most of the websites, there is a final confirmation page before the payment page during which the user can confirm
  that it wants to place the order and also the Amazon can **take a lock** on the product.
* If multiple users are trying to get that product then only the ones for which the product is there in the inventory
  will be taken to the payments page and the other ones would be taken to some cancellation page.


* **SOLUTION**
* Suppose there is only one sear available and 2 users try to get it.
* There will be a difference in the timestamp(few milliseconds) between them.
* The first user will take a lock on the database level. This is done by starting a transaction.
* The transaction will be completed as soon as the payment is completed or the user doesn't make the payment or the
  payment link expires.
* The problem with this approach is that the lock will be there for higher duration of time and taking a lock on the
  row for extended duration might not be the optimal approach.
* **A better approach** would be to take a lock on the seat and change its attribute to "BLOCKED" and then release the lock.
* Then we will use the logic that in case the seat attribute is "BLOCKED", any user won't be able to select that seat.
* The lock can be achieved by 'SERIALIZABLE' in the database. Transaction will be ended and the user will be moved to the 
  payment page.
* This is called **Soft Lock**.
* To make this lock full-proof we will use **double locking**.
* This is to prevent the situation when multiple threads might want to take a lock and they both check if the seat is 
  available or not to decide that. For both of them the seat will be available and then one will take the lock and other
  will wait for the lock to get released. Some other thread might have taken the lock on the seat while these 2 were checking.
* This leads to higher performance as a large number of threads don't wait for the locks.
* If 1000 threads simultaneously check and find the instance to be null, they'll all race to enter the synchronized block.
* In that initial burst, one thread acquires the lock and initializes the instance while the other 999 wait, so there is 
  no performance gain during that initialization phase. However, the performance benefit comes into play for subsequent accesses.
* One more thing it achieves is the sanctity of the lock on the seats remains. 

* **BOOKING CONTROLLER**
* Controller should never return and accept a concrete object. They should always take and send DTOs.
* createBooking would have returned a Booking object, but we have returned a BookingRequestDto.
* For the creation of the dto think of the bare minimum requirements needed from the real object data.
* The BookingResponseDto will get the data from the request and return the status of the booking along with the bookingId 
  and the amount to be paid.

* The booking controller will take the requestDto and return the responseDto.
* This controller will pass the requestDto to the Service and then convert the returned actual booking object to response dto.


* Remember that we are trying to book a showSeat and not just a seat.
* **Getting the list of show seats: **
* We have the list of the IDs, and we want to extract everything from a table corresponding to those IDs.
* For this we will pass the List of the ids in the parameters and use findAllById to get the list of the objects related to
  that id in that table.

* There are 2 possibilities:
  1. Multiple threads(say 2) try to book the same set of seats at the same time and both find that the seats
  are available(first check). They both go to the confirmation page trying to take a lock and only one of
  them succeeds. Then the one that succeeds. changes the status of the seats to BLOCKED and moves to the
  payment page after releasing the lock. The second check is purely for performance reason elaborated later.
  2. Only one thread tries to check for the seats and then finds it available. Then it moves to take the lock
  and then the same process.

* The performance gain happens with those 2 checks is that the first check is for fast check on the instance
  in the case that its already initialized. If it's not then there could be a scenario when multiple threads
  sees it not initialized and then move to the next stage of trying to take a lock. Only one thread would be
  able to take it and then try to initialize the object and then release the lock. The other threads from the
  first batch then enter, and they MUST find the instance to be initialized. The second lock is for this.

* A subtle issue that could occur here is of the instruction reordering. To prevent a thread to falsely read
  a partially constructed object we have to use "volatile". This ensures visibility and ordering guarantees.
  volatile keyword in Java ensures visibility of changes to variables across threads by preventing cached reads
* Without volatile, each thread might read a variable from its local CPU cache, which could be outdated if another thread modified the variable in main memory. volatile ensures that:
  Every write to a volatile variable is immediately flushed to main memory.
  Every read of a volatile variable is fetched directly from main memory.
  Thus, if one thread updates a volatile variable, all other threads immediately see the latest value.
  (Read more about it in the probability distribution chat in gpt)

* @EnableJpaAuditing at the application level, @EntityListener(AuditingEntityListener.class) in the base model,
  and @CreatedDate and @Temporal(TemporalType) above the createdAt and the @LastCreatedAt above the lastModifiedAt.