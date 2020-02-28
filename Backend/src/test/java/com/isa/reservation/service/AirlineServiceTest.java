package com.isa.reservation.service;

import com.isa.reservation.model.Address;
import com.isa.reservation.model.Airline;
import com.isa.reservation.model.Destination;
import com.isa.reservation.model.Flight;
import com.isa.reservation.repository.AirlineRepository;
import com.isa.reservation.service.impl.AirlineServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.HashSet;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirlineServiceTest {

    @Mock
    private AirlineRepository airlineRepositoryMock;

    @Mock
    private UserService userServiceMock;

    @Mock
    private FlightService flightServiceMock;

    @Mock
    private DestinationService destinationServiceMock;

    @Mock
    private Airline airlineMock;

    @InjectMocks
    private AirlineServiceImpl airlineServiceMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test for service that gets Airline by id
     * */
    @Test
    public void testGetAirlineByIdOk() {

        when(airlineRepositoryMock.findAirlineById(1L)).thenReturn(new Airline(1L, "Sky", new Address(), "Description", new HashSet<Destination>(), new HashSet<Flight>(), 3, 4,12));

        airlineMock = airlineServiceMock.getAirlineById(1L);
        assertEquals("Sky", airlineMock.getName());
    }


    /**
     * Airline with this id doesn't exist
     * */
//    @Test(expected = NullPointerException.class)
//    public void testGetAirlineByIdNotFound() {
//
//        when(airlineRepositoryMock.findAirlineById(1L)).thenReturn(new Airline(1L, "Sky", new Address(), "Description", new HashSet<Destination>(), new HashSet<Flight>(), 3, 4,12));
//
//        Airline airline = airlineServiceMock.getAirlineById(23L);
//        assertEquals(null, airline);
//    }

    /**
    * Test add airline OK
    * */
    @Test
    public void testAddAirline() {
        when(airlineRepositoryMock.save(new Airline(null, "Sky", new Address(), "Description", new HashSet<Destination>(), new HashSet<Flight>(), 3, 4,12))).thenReturn(new Airline(null, "Sky", new Address(), "Description", new HashSet<Destination>(), new HashSet<Flight>(), 3, 4,12));


        Airline airline = airlineServiceMock.addAirline(new Airline(null, "Sky", new Address(), "Description", new HashSet<Destination>(), new HashSet<Flight>(), 3, 4,12));
        assertThat(airline).isNotNull();
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteAirline() {
        Airline airline = new Airline();
        airline.setId(22L);
        airlineServiceMock.deleteAirline(airline);
    }
}
