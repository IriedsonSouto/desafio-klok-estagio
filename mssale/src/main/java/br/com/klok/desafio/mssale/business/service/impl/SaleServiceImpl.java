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
import br.com.klok.desafio.mssale.model.repository.SaleProductRepository;
import br.com.klok.desafio.mssale.model.repository.SaleRepository;
import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
import br.com.klok.desafio.mssale.presetation.dto.SaleWithProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleProductRepository saleProductRepository;
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
    public SaleWithProductDto getSaleProductById(String uuid) {

        var optionalSale = saleRepository.findById(uuid);
        var saleModel = optionalSale.orElseThrow( () -> new EntityNotFoundException("Sale: " + uuid + " not found"));
        var saleProductList = saleProductRepository.getSaleProductsBySaleId(uuid);

        var saleWithProductDto = new SaleWithProductDto(saleModel);

        saleWithProductDto.setProducts(saleProductList);

        return saleWithProductDto;
    }

    @Override
    public List<SaleModel> getSaleByClientId(String id) {
        return saleRepository.findByClientId(id);
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
    public SaleModel updateSaleById(String id, SaleDto sale) {
        return null;
    }

    @Override
    public SaleModel consultProductToUpdateList(String id, ProductDataDto productDataDto) {

        var saleModel = this.getSaleById(id);

        var sendProductDataDto = new ProductDataDto(saleModel.getUuid()
                                                  , null
                                                  , productDataDto.productUuid()
                                                  , null
                                                  , productDataDto.quantity());
        try {
            postRabbitClient.postSaleToProduct(sendProductDataDto);
        } catch (Exception e) {}
        return saleModel;
    }


    @Override
    public SaleWithProductDto updateSaleProductList(ProductDataDto productDataDto) {

        var saleModel = this.getSaleById(productDataDto.saleUuid());
        var saleProductList = saleProductRepository.getSaleProductsBySaleId(saleModel.getUuid());

        var saleProductModel = new SaleProductModel();
        saleProductModel.setName(productDataDto.productName());
        saleProductModel.setQuantity(productDataDto.quantity());
        saleProductModel.setPrice(productDataDto.price());
        saleProductModel.setSaleModel(saleModel);

        BigDecimal currentPrice = saleModel.getPrice() == null ? new BigDecimal(0) : saleModel.getPrice();

        if(saleProductList.contains(saleProductModel)){
            saleModel.setPrice(currentPrice
                    .subtract(saleProductModel.getPrice()
                            .multiply(new BigDecimal(saleProductModel.getQuantity()))));

            saleProductRepository.delete(saleProductList.remove(saleProductList.indexOf(saleProductModel)));
        } else {
            saleModel.setPrice(currentPrice
                    .add(saleProductModel.getPrice()
                            .multiply(new BigDecimal(saleProductModel.getQuantity()))));

            saleProductRepository.save(saleProductModel);
            saleProductList.add(saleProductModel);
        }

        saleRepository.save(saleModel);

        var saleWithProductDto = new SaleWithProductDto(saleModel);
        saleWithProductDto.setProducts(saleProductList);

        return saleWithProductDto;
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
