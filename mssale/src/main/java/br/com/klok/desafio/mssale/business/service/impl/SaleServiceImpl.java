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

        return optionalSale.orElseThrow( () -> new EntityNotFoundException("Sale: " + uuid + " not found"));
    }

    @Override
    public SaleModel getSaleByClientId(String uuid) {

        var optionalSale = saleRepository.findByClientId(uuid);

        return optionalSale.orElseThrow( () -> new EntityNotFoundException("Sale by Client ID: " + uuid + " not found"));
    }

    @Override
    public SaleModel updateSaleById(String id, SaleDto sale) {
        return null;
    }

    @Override
    public SaleModel updateSaleProductList(String saleId, String productId) {
        return null;
    }

    @Override
    public SaleModel updateSaleStatus(String id) {
        return null;
    }

    @Override
    public void deleteSaleById(String uuid) {
        var sale = this.getSaleById(uuid);
        saleRepository.delete(sale);
    }

}
