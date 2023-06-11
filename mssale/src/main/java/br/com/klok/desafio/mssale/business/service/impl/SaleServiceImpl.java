package br.com.klok.desafio.mssale.business.service.impl;

import br.com.klok.desafio.mssale.business.SaleService;
import br.com.klok.desafio.mssale.exception.EntityNotFoundException;
import br.com.klok.desafio.mssale.model.entity.SaleModel;
import br.com.klok.desafio.mssale.model.enums.SaleStatusEnum;
import br.com.klok.desafio.mssale.model.repository.SaleRepository;
import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    @Override
    public SaleModel saveSale(SaleDto saleDto) {
        var saleModel = SaleDto.convertToModel(saleDto);
        saleModel.setStatus(SaleStatusEnum.CREATED);
        return saleRepository.save(saleModel);
    }

    @Override
    public List<SaleModel> getAllSale() {
        return saleRepository.findAll();
    }

    @Override
    public SaleModel getSaleById(String uuid) {
        var optionalSale = saleRepository.findById(uuid);

        if (optionalSale.isEmpty()) {
            throw new EntityNotFoundException("Sale: " + uuid + " not found");
        }
        return optionalSale.get();
    }

    @Override
    public void deleteSaleById(String id) {
        var sale = this.getSaleById(id);
        saleRepository.delete(sale);
    }

}
