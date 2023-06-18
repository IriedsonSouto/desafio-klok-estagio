package br.com.klok.desafio.msclient.infra.data;

import java.math.BigDecimal;
import java.util.Date;

public record SalesChargeDataDto (String uuid
                                 ,Date createDate
                                 ,BigDecimal price
                                 ,Date paidDate
                                 ,String status
                                 ,String clientId){
}
