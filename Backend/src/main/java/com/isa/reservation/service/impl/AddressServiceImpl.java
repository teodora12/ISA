package com.isa.reservation.service.impl;

import com.isa.reservation.model.Address;
import com.isa.reservation.model.Destination;
import com.isa.reservation.repository.AddressRepository;
import com.isa.reservation.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAllAddresses() {
        return this.addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        return this.addressRepository.findAddressById(id);
    }

    @Override
    public Address addAddress(Address address) {
        return this.addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Address address) {             //????
        Address savedAddress = this.addressRepository.findAddressById(address.getId());
        savedAddress.setCountry(address.getCountry());
        savedAddress.setCity(address.getCity());
        savedAddress.setStreet(address.getStreet());
        savedAddress.setAddressNumber(address.getAddressNumber());

        return this.addressRepository.save(savedAddress);
    }

    @Override
    public void deleteAddress(Address address) {
        this.addressRepository.delete(address);
    }
}
