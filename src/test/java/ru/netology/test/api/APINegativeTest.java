package ru.netology.test.api;

import ru.netology.data.DBHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.netology.data.APIHelper.payFromCard;
import static ru.netology.data.DBHelper.deleteAllDB;
import static ru.netology.data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.*;

public class APINegativeTest {

    private static final int amount = 45_000_00;
    private final String debitPay = "/pay";
    private final String creditPay = "/credit";

    @AfterEach
    void setDownDB() {
        deleteAllDB();
    }

    @Test
    @DisplayName("Должно отсутствовать поле CreditId при оплате отклоненной дебетовой картой")
    void shouldNotHaveCreditIdWithDebitDeclinedCard() {
        payFromCard(getDeclinedCard(), debitPay, 200);
        assertNull(DBHelper.getCreditId());
    }

    @Test
    @DisplayName("Должно отсутствовать поле Amount при оплате отклоненной дебетовой картой")
    void shouldNotHaveAmountWithDebitDeclinedCard() {
        payFromCard(getDeclinedCard(), debitPay, 200);
        assertNull(DBHelper.getAmountDebitCard());
    }

    @Test
    @DisplayName("Должно отсутствовать поле PaymentId при оплате отклоненной дебетовой картой")
    void shouldNotHavePaymentIdWithDebitDeclinedCard() {
        payFromCard(getDeclinedCard(), debitPay, 200);
        assertNull(DBHelper.getPaymentId());
    }

    @Test
    @DisplayName("Должно отсутствовать поле TransactionId при оплате отклоненной дебетовой картой")
    void shouldNotHaveTransactionIdWithDebitDeclinedCard() {
        payFromCard(getDeclinedCard(), debitPay, 200);
        assertNull(DBHelper.getTransactionIdDebitCard());
    }


    @Test
    @DisplayName("Должен быть статус DECLINED при оплате отклоненной кредитной картой")
    void shouldHaveStatusWithCreditDeclinedCard() {
        payFromCard(getDeclinedCard(), creditPay, 200);
        assertEquals("DECLINED", DBHelper.getStatusCreditCard());
    }

    @Test
    @DisplayName("Должно отсутствовать поле BankId при оплате отклоненной кредитной картой")
    void shouldNotHaveBankIdWithCreditDeclinedCard() {
        payFromCard(getDeclinedCard(), creditPay, 200);
        assertNull(DBHelper.getBankIdCreditCard());
    }

    @Test
    @DisplayName("Должно отсутствовать поле CreditId при оплате отклоненной кредитной картой")
    void shouldNotHaveCreditIdWithCreditApprovedCard() {
        payFromCard(getDeclinedCard(), creditPay, 200);
        assertNull(DBHelper.getCreditId());
    }

    @Test
    @DisplayName("Должно отсутствовать поле PaymentId при оплате отклоненной кредитной картой")
    void shouldNotHavePaymentIdWithCreditDeclinedCard() {
        payFromCard(getDeclinedCard(), creditPay, 200);
        assertNull(DBHelper.getPaymentId());
    }

}