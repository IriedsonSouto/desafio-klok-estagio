package br.com.klok.desafio.mssale.business.service.impl;

import br.com.klok.desafio.mssale.business.SaleService;
import br.com.klok.desafio.mssale.exception.EntityNotFoundException;
import br.com.klok.desafio.mssale.infra.PostRabbitClient;
import br.com.klok.desafio.mssale.infra.data.ClientDataDto;
import br.com.klok.desafio.mssale.infra.data.PaymentDataDto;
import br.com.klok.desafio.mssale.infra.data.ProductDataDto;
import br.com.klok.desafio.mssale.model.entity.SaleModel;
import br.com.klok.desafio.mssale.model.entity.SaleProductModel;
import br.com.klok.desafio.mssale.model.enums.SaleStatusEnum;
import br.com.klok.desafio.mssale.model.repository.SaleRepository;
import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final PostRabbitClient postRabbitClient;

    @Override
    public void createSale(SaleDto saleDto) {
        try {
            postRabbitClient.postSaleToClient(saleDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SaleModel saveSale(ClientDataDto clientDataDto){
        var saleModel = new SaleModel();

        saleModel.setStatus(SaleStatusEnum.CREATED);
        saleModel.setClientId(clientDataDto.uuid());

        return this.saleRepository.save(saleModel);
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
    public void responseSaleToPayment(PaymentDataDto paymentDataDto) {
        var saleModel = this.getSaleById(paymentDataDto.uuidSale());
        try {
            postRabbitClient.postSaleToPayment(paymentDataDto);
            this.updateSaleStatus(saleModel);
        } catch (Exception e) {}

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
    public SaleModel updateSaleProductList(ProductDataDto productDataDto) {

        var saleModel = this.getSaleById(productDataDto.saleUuid());
        var saleProductList = saleModel.getSaleProdutcList();

        var saleProductModel = new SaleProductModel();
        saleProductModel.setName(productDataDto.productName());
        saleProductModel.setQuantity(productDataDto.quantity());
        saleProductModel.setPrice(productDataDto.price());

        if(saleProductList.contains(saleProductModel)){
            saleModel.setPrice(saleModel.getPrice()
                    .subtract(saleProductModel.getPrice()
                            .multiply(new BigDecimal(saleProductModel.getQuantity()))));

            saleProductList.remove(saleProductModel);
        } else {
            saleModel.setPrice(saleModel.getPrice()
                    .add(saleProductModel.getPrice()
                            .multiply(new BigDecimal(saleProductModel.getQuantity()))));

            saleModel.getSaleProdutcList().add(saleProductModel);
        }

        return saleRepository.save(saleModel);
    }

    @Override
    public SaleModel updateSaleStatus(SaleModel saleModel) {
        saleModel.setStatus(SaleStatusEnum.PAID);
        saleModel.setPaidDate(new Date());
        return saleRepository.save(saleModel);
    }

    @Override
    public void deleteSaleById(String uuid) {
        var sale = this.getSaleById(uuid);
        saleRepository.delete(sale);
    }

}
