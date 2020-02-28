package com.isa.reservation.service;

import com.isa.reservation.dto.CreateAndUpdateAffiliateDto;
import com.isa.reservation.model.Affiliate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AffiliateService {
    List<Affiliate> getAllAffiliates();
    Affiliate getAffiliateById(Long id);
    Affiliate addAffiliate(CreateAndUpdateAffiliateDto affiliateDto);
    Affiliate updateAffliate(CreateAndUpdateAffiliateDto affiliateDto);
    Affiliate deleteAffialiate(Long id);
}
