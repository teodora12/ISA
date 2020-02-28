package com.isa.reservation.service.impl;

import com.isa.reservation.dto.CreateAndUpdateAffiliateDto;
import com.isa.reservation.model.Affiliate;
import com.isa.reservation.repository.AffiliateRepository;
import com.isa.reservation.service.AddressService;
import com.isa.reservation.service.AffiliateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffiliateServiceImpl implements AffiliateService {


    @Autowired
    private AffiliateRepository affiliateRepository;

    @Autowired
    private AddressService addressService;


    @Override
    public List<Affiliate> getAllAffiliates() {

        return this.affiliateRepository.findAll();
    }

    @Override
    public Affiliate getAffiliateById(Long id) {
        return this.affiliateRepository.findAffiliateById(id);
    }

    @Override
    public Affiliate addAffiliate(CreateAndUpdateAffiliateDto affiliateDto) {
        affiliateDto.setAddress(this.addressService.addAddress(affiliateDto.getAddress()));
        Affiliate affiliate = new Affiliate(affiliateDto);
        return this.affiliateRepository.save(affiliate);
    }

    @Override
    public Affiliate updateAffliate(CreateAndUpdateAffiliateDto affiliateDto) {
        Affiliate affiliate =  this.affiliateRepository.findAffiliateById(affiliateDto.getId());
        affiliate.setName(affiliateDto.getName());
        affiliate.setAddress(affiliateDto.getAddress());
        this.affiliateRepository.save(affiliate);
        return affiliate;
    }

    @Override
    public Affiliate deleteAffialiate(Long id) {
        Affiliate affiliate = this.getAffiliateById(id);
        if(affiliate == null){
            return null;
        }
        preRemove(affiliate);
        this.affiliateRepository.delete(affiliate);
        return affiliate;
    }

    public void preRemove(Affiliate affiliate){
        affiliate.setCarService(null);
        affiliateRepository.save(affiliate);
    }



}
