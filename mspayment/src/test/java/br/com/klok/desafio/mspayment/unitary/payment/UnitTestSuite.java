package br.com.klok.desafio.mspayment.unitary.payment;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({PaymentModelUnitaryTest.class
             , PaymentDtoUnitaryTest.class
             , PaymentControllerUnitaryTest.class
             , PaymentServiceUnitaryTest.class
})
public class UnitTestSuite {
}
