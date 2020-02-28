package com.isa.reservation.service;

import com.isa.reservation.model.Address;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface AddressService {

     List<Address> getAllAddresses();
     Address getAddressById(Long id);
     Address addAddress(Address address);
     Address updateAddress(Address address);
     void deleteAddress(Address address);

}
