package br.com.challenge.shoppingcart.rest;

import br.com.challenge.shoppingcart.dto.promocode.PromoCodeDTO;
import br.com.challenge.shoppingcart.service.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class PromoCodeRest {

    private PromoCodeService promoCodeService;

    @Autowired
    public PromoCodeRest(PromoCodeService promoCodeService){
        this.promoCodeService = promoCodeService;
    }

    @RequestMapping(value = "/promo-code",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPromoCode(@Valid @RequestBody PromoCodeDTO promoCodeDTO){
        return new ResponseEntity<>(promoCodeService.create(promoCodeDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/promo-code/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePromoCode(@Valid @RequestBody PromoCodeDTO promoCodeDTO, @PathVariable Long id){
        return new ResponseEntity<>(promoCodeService.update(id, promoCodeDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/promo-code/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePromoCode(@PathVariable Long id){
        promoCodeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/promo-code/{code}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByCode(@PathVariable String code){
        return new ResponseEntity<>(promoCodeService.getByCode(code), HttpStatus.OK);
    }
}
