package com.carro.intrface;

import com.carro.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;

public interface ReservationInterface extends CrudRepository<Reservation,Integer> {
    List<Reservation> findAllByStatus (String status);

    List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);

    //select idClient, count(*) as "total" from reservation group by idClient order by total desc;
    @Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c group by c.client order by COUNT(c.client)DESC")
    List<Object[]> countTotalReservationByClient();
}
