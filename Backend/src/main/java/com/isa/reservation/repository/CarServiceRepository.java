package com.isa.reservation.repository;

import com.isa.reservation.model.CarService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarServiceRepository extends JpaRepository<CarService, Long> {

    CarService findCarServiceById(Long id);

 //   @Query("Select new CarService c from CarService c where c.name like:name")
  //  List<CarService> findByNameContaining(@Param("name")String name);

    List<CarService>  findCarServicesByNameLikeOrNameContains (String name, String name1);
}
