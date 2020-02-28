package com.isa.reservation.controller;

import com.isa.reservation.dto.AffiliateDto;
import com.isa.reservation.dto.CreateAndUpdateAffiliateDto;
import com.isa.reservation.model.Affiliate;
import com.isa.reservation.service.AffiliateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/affiliates")
public class AffiliateContoller {

    @Autowired
    private AffiliateService affiliateService;

    @GetMapping
    public ResponseEntity<List<AffiliateDto>> getAffiliates(){
        List<Affiliate> affiliates = this.affiliateService.getAllAffiliates();
        List<AffiliateDto> affiliateDtos = new ArrayList<>();
        for(Affiliate affiliate :affiliates) {
            affiliateDtos.add(new AffiliateDto(affiliate));
        }
        if(affiliates == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(affiliateDtos, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<AffiliateDto> getAffiliateById(@PathVariable Long id) {
        Affiliate affiliate = affiliateService.getAffiliateById(id);
        AffiliateDto affiliateDto = new AffiliateDto(affiliate);
        if(affiliate == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(affiliateDto, HttpStatus.OK);
    }


    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('ROLE_ADMIN_SERVICE')")
    public ResponseEntity<CreateAndUpdateAffiliateDto> addAffilate(@RequestBody CreateAndUpdateAffiliateDto affiliateDto){
        Affiliate affiliate = this.affiliateService.addAffiliate(affiliateDto);
        if(affiliate == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CreateAndUpdateAffiliateDto(affiliate));
    }


    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('ROLE_ADMIN_SERVICE')")
    public ResponseEntity<CreateAndUpdateAffiliateDto> updateAffiliate(@RequestBody CreateAndUpdateAffiliateDto affiliateDto) {
        Affiliate affiliate = this.affiliateService.getAffiliateById(affiliateDto.getId());
        if(affiliate == null){
            return ResponseEntity.notFound().build();
        }

        affiliate = this.affiliateService.updateAffliate(affiliateDto);
        return ResponseEntity.ok(new CreateAndUpdateAffiliateDto(affiliate));
    }


    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN_SERVICE')")
    public ResponseEntity deleteAffiliate(@PathVariable Long id){
        Affiliate affiliate = this.affiliateService.deleteAffialiate(id);
        if(affiliate == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }


}
