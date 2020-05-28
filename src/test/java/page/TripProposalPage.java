package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TripProposalPage {
    private SelenideElement buttonBuyByDebit = $(byText("Купить"));
    private SelenideElement buttonBuyCredit = $(byText("Купить в кредит"));

    public DebitCardPaymentForm selectBuyByDebitCard() {
        buttonBuyByDebit.click();
        return new DebitCardPaymentForm();
    }

    public CreditCardPaymentForm selectBuyByCreditCard() {
        buttonBuyCredit.click();
        return new CreditCardPaymentForm();
    }
}
