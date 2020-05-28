package test;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.TripProposalPage;
import restApiHelper.RestApiHelper;
import sqlUtils.SqlUtils;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ApplicationTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test// 1 UI
    public void shouldCheckIfSuccessWithValidCardInformationPaymentByDebitCard() throws SQLException {
        val TripProposalPage = new TripProposalPage();
        val debitCardPaymentForm = TripProposalPage.selectBuyByDebitCard();
        val validCardInformation = DataHelper.getValidCardInformation();
        debitCardPaymentForm.fillCardInformation(validCardInformation);
        debitCardPaymentForm.checkIfPaymentSuccessful();
        final String statusForPaymentByDebitCard = SqlUtils.getStatusForPaymentByDebitCard();
        final String paymentAmount = SqlUtils.getPaymentAmount();
        assertEquals("APPROVED", statusForPaymentByDebitCard);
        assertEquals("4500000", paymentAmount);

    }

    @Test//2 UI
    public void shouldCheckIfSuccessWithValidCardInformationPaymentByCreditCard() throws SQLException {
        val TripProposalPage = new TripProposalPage();
        val creditCardPaymentForm = TripProposalPage.selectBuyByCreditCard();
        val validCardInformation = DataHelper.getValidCardInformation();
        creditCardPaymentForm.fillCardInformation(validCardInformation);
        creditCardPaymentForm.checkIfPaymentSuccessful();
        final String statusForPaymentByCreditCard = SqlUtils.getStatusForPaymentByCreditCard();
        assertEquals("APPROVED", statusForPaymentByCreditCard);
    }

    @Test// 3 UI должен падать
    public void shouldCheckIfNotSuccessWithInvalidCardInformationPaymentByDebitCard() throws SQLException {
        val TripProposalPage = new TripProposalPage();
        val debitCardPaymentForm = TripProposalPage.selectBuyByDebitCard();
        val validCardInformation = DataHelper.getInvalidCardInformation();
        debitCardPaymentForm.fillCardInformation(validCardInformation);
//        debitCardPaymentForm.checkIfPaymentNotSuccessful();  баг, показывает в веб-приложении успех
        final String statusForPaymentByDebitCard = SqlUtils.getStatusForPaymentByDebitCard();
        assertEquals("DECLINED", statusForPaymentByDebitCard);
    }

    @Test//4 UI
    public void shouldCheckIfNotSuccessWithInvalidCardInformationPaymentByCreditCard() throws SQLException {
        val TripProposalPage = new TripProposalPage();
        val creditCardPaymentForm = TripProposalPage.selectBuyByCreditCard();
        val validCardInformation = DataHelper.getInvalidCardInformation();
        creditCardPaymentForm.fillCardInformation(validCardInformation);
//        creditCardPaymentForm.checkIfPaymentNotSuccessful();  баг, показывает в вебе успех
        final String statusForPaymentByCreditCard = SqlUtils.getStatusForPaymentByCreditCard();
        assertEquals("DECLINED", statusForPaymentByCreditCard);
    }

    @Test//5 UI неправильный номер карты
    public void shouldCheckIfNotSuccessWithWrongCardNumber() {
        val TripProposalPage = new TripProposalPage();
        val creditCardPaymentForm = TripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithWrongNumber();
        creditCardPaymentForm.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm.checkIfWrongFormatOfField();
        val creditCardPaymentForm2 = TripProposalPage.selectBuyByDebitCard();
        creditCardPaymentForm2.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm2.checkIfWrongFormatOfField();
    }

    @Test//6 UI неправильный месяц (из одной цифры)
    public void shouldCheckIfNotSuccessWithWrongMonth() {
        val TripProposalPage = new TripProposalPage();
        val creditCardPaymentForm = TripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithWrongMonth();
        creditCardPaymentForm.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm.checkIfWrongFormatOfField();
        val creditCardPaymentForm2 = TripProposalPage.selectBuyByDebitCard();
        creditCardPaymentForm2.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm2.checkIfWrongFormatOfField();
    }

    @Test//7 UI неправильный год (больше 29 )
    public void shouldCheckIfNotSuccessWithWrongYearMoreThan30() {
        val TripProposalPage = new TripProposalPage();
        val creditCardPaymentForm = TripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithWrongMonth();
        creditCardPaymentForm.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm.checkIfWrongFormatOfField();
        val creditCardPaymentForm2 = TripProposalPage.selectBuyByDebitCard();
        creditCardPaymentForm2.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm2.checkIfWrongFormatOfField();
    }

    @Test//8 UI неправильный год (из одной цифры )
    public void shouldCheckIfNotSuccessWithWrongYearFromOneNumber() {
        val TripProposalPage = new TripProposalPage();
        val creditCardPaymentForm = TripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithWrongMonth();
        creditCardPaymentForm.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm.checkIfWrongFormatOfField();
        val creditCardPaymentForm2 = TripProposalPage.selectBuyByDebitCard();
        creditCardPaymentForm2.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm2.checkIfWrongFormatOfField();
    }

    @Test//9 UI неправильный код CVC
    public void shouldCheckIfNotSuccessWithWrongCVC() {
        val TripProposalPage = new TripProposalPage();
        val creditCardPaymentForm = TripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithWrongCvc();
        creditCardPaymentForm.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm.checkIfWrongFormatOfField();
        val creditCardPaymentForm2 = TripProposalPage.selectBuyByDebitCard();
        creditCardPaymentForm2.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm2.checkIfWrongFormatOfField();
    }

    @Test//10 UI неправильное имя (цифры вместо букв), должен падать
    public void shouldCheckIfNotSuccessWithWrongName() {
        val TripProposalPage = new TripProposalPage();
        val creditCardPaymentForm = TripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithWrongName();
        creditCardPaymentForm.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm.checkIfWrongFormatOfField();
        val creditCardPaymentForm2 = TripProposalPage.selectBuyByDebitCard();
        creditCardPaymentForm2.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm2.checkIfWrongFormatOfField();
    }

    @Test//11 UI  без имени
    public void shouldCheckIfNotSuccessWithoutName() {
        val TripProposalPage = new TripProposalPage();
        val creditCardPaymentForm = TripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithoutName();
        creditCardPaymentForm.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm.checkIfWrongFormatOfField();
        val creditCardPaymentForm2 = TripProposalPage.selectBuyByDebitCard();
        creditCardPaymentForm2.fillCardInformation(invalidCardInformation);
        creditCardPaymentForm2.checkIfWrongFormatOfField();
    }

    @Test
    public void shouldCheckStatusViaAPIByDebitCardWithValidData() {
        val validCardInformation = DataHelper.getValidCardInformation();
        final String response = RestApiHelper.fillPaymentFormByDebitCard(validCardInformation);
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    public void shouldCheckStatusViaAPIByDebitCardWithInvalidData() {
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        final String response = RestApiHelper.fillPaymentFormByDebitCard(invalidCardInformation);
        assertTrue(response.contains("DECLINED"));
    }

    @Test
    public void shouldCheckStatusViaAPIByCreditCardWithValidData() {
        val validCardInformation = DataHelper.getValidCardInformation();
        final String response = RestApiHelper.fillPaymentFormByCreditCard(validCardInformation);
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    public void shouldCheckStatusViaAPIByCreditCardWithInvalidData() {
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        final String response = RestApiHelper.fillPaymentFormByCreditCard(invalidCardInformation);
        assertTrue(response.contains("DECLINED"));
    }
}
