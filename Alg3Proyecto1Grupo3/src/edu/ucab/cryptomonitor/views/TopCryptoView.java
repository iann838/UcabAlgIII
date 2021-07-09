package edu.ucab.cryptomonitor.views;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.paaksing.jjango.Validator.ValidationError;

import edu.ucab.cryptomonitor.api.CmcAPI;
import edu.ucab.cryptomonitor.api.CmcAPI.RequestFailed;
import edu.ucab.cryptomonitor.app.Request;
import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.Currency;

public class TopCryptoView extends View {
    
    class CurrencyAscendingRank implements Comparator<Currency> {
        public int compare(Currency a, Currency b) {
            return a.getRank() - b.getRank();
        }
    }

    @Override
    public Response dispatch(Request request) throws IOException {
        Scanner scan = request.getScanner();
        Date minuteAgo = new Date(System.currentTimeMillis() - 300 * 1000);;
        List<Currency> currencies = Currency.objects.all();

        if (currencies.isEmpty() || currencies.get(0).getLastUpdated().before(minuteAgo)) {
            try {
                CmcAPI.updateLatestCurrencies();
            } catch (RequestFailed | ValidationError e) {
                System.out.println(e.getMessage());
                System.in.read();
                scan.nextLine();
                return new Response(new MainView(), Status.REDIRECT);
            }
        }

        currencies = Currency.objects.all();
        currencies.sort(new CurrencyAscendingRank());

        System.out.println(String.format("*** Monitor de Bitcoin | Top 10 Criptomonedas (@%s) ***", request.getUser().getUsername()));
        System.out.println(String.format(
            "%5s  %5s  %15s  %10s  %7s  %7s  %7s  %15s",
            "Rank",
            "Etiq",
            "Nombre",
            "Precio",
            "1h %",
            "24h %",
            "7d %",
            "Market Cap"
        ));
        for (Currency currency: currencies) {
            System.out.println(String.format(
                "%5d  %5s  %15s  %10.2f  %7.2f  %7.2f  %7.2f  %15.0f",
                currency.getRank(),
                currency.getTag(),
                currency.getName(),
                currency.getPrice(),
                currency.getPercentChange1h(),
                currency.getPercentChange24h(),
                currency.getPercentChange7d(),
                currency.getMarketCap()
            ));
        }

        System.in.read();
        scan.nextLine();
        return new Response(new MainView(), Status.REDIRECT);
    }

}
