package br.com.challenge.shoppingcart.service;

import br.com.challenge.shoppingcart.domain.PromoCode;
import br.com.challenge.shoppingcart.dto.PromoCodeDTO;
import br.com.challenge.shoppingcart.exceptions.PromoCodeNotFoundException;
import br.com.challenge.shoppingcart.exceptions.UserNotFoundException;
import br.com.challenge.shoppingcart.repository.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PromoCodeService extends BaseService {

    private PromoCodeRepository promoCodeRepository;

    @Autowired
    public PromoCodeService(PromoCodeRepository promoCodeRepository){
        this.promoCodeRepository = promoCodeRepository;
    }

    @Transactional
    public PromoCodeDTO create(PromoCodeDTO promoCodeDTO){
        PromoCode promoCode = modelMapper.map(promoCodeDTO,PromoCode.class);
        promoCode.setId(null);
        return modelMapper.map(save(promoCode),PromoCodeDTO.class);
    }

    @Transactional
    public PromoCodeDTO update(Long id, PromoCodeDTO promoCodeDTO) {
        PromoCode promoCode = findById(id);
        modelMapper.map(promoCodeDTO, promoCode);
        promoCode.setId(id);
        return modelMapper.map(save(promoCode),PromoCodeDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        PromoCode promoCode = findById(id);
        promoCode.setDeleted(true);
        save(promoCode);
    }

    public PromoCodeDTO getByCode(String code) {
        PromoCode promoCode = promoCodeRepository.findFirstByCodeAndDeletedFalse(code)
                .orElseThrow(PromoCodeNotFoundException::new);
        return modelMapper.map(promoCode,PromoCodeDTO.class);
    }

    public PromoCodeDTO getById(Long id) {
        PromoCode promoCode = findById(id);
        return modelMapper.map(promoCode,PromoCodeDTO.class);
    }

    private PromoCode save(PromoCode promoCode){
        return promoCodeRepository.save(promoCode);
    }

    private PromoCode findById(Long id){
        return promoCodeRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(PromoCodeNotFoundException::new);
    }
}
