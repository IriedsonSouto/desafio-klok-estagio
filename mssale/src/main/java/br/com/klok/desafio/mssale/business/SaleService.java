package br.com.klok.desafio.mssale.business;

import br.com.klok.desafio.mssale.infra.data.ClientDataDto;
import br.com.klok.desafio.mssale.infra.data.PaymentDataDto;
import br.com.klok.desafio.mssale.infra.data.ProductDataDto;
import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
import br.com.klok.desafio.mssale.model.entity.SaleModel;

import java.util.List;

public interface SaleService {


    public void createSale(SaleDto sale);

    public SaleModel saveSale(ClientDataDto clientDataDto);

    public List<SaleModel> getAllSale();

    public SaleModel getSaleById(String id);

    public void responseSaleToPayment(PaymentDataDto paymentDataDto);

    public SaleModel getSaleByClientId(String id);

    public SaleModel updateSaleById(String id, SaleDto sale);

    public SaleModel updateSaleProductList(ProductDataDto productDataDto);

    public SaleModel updateSaleStatus(SaleModel saleModel);

    public void deleteSaleById(String id);


}
