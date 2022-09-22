package com.vehicle.intrface;

import com.vehicle.model.Client;
import com.vehicle.model.Reservation;
import com.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;

public interface ReservationInterface extends CrudRepository<Reservation,Integer> {
    List<Reservation> findAllByStatus (String status);

    List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);

    List<Reservation> findAllByClientAndVehicle(Client idClient, Vehicle idVehicle);

    //select idClient, count(*) as "total" from reservation group by idClient order by total desc;
    @Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c group by c.client order by COUNT(c.client)DESC")
    List<Object[]> countTotalReservationByClient();
}
