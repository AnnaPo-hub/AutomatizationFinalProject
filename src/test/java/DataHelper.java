import lombok.Value;

public class DataHelper {


    @Value
    public static class CardInformation {
        private String cardNumber, expirationMonth, expirationYear, cardHolder, cardCVC;

        public static CardInformation getCardInformation(String cardNumber, String expirationMonth,String expirationYear,String cardHolder, String cardCVC){
            return new CardInformation(cardNumber, expirationMonth, expirationYear, cardHolder, cardCVC);
        }

    }
}
