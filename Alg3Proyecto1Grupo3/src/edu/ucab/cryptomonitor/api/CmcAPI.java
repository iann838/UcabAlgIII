package edu.ucab.cryptomonitor.api;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paaksing.jjango.Validator.ValidationError;

import edu.ucab.cryptomonitor.models.Currency;
import edu.ucab.cryptomonitor.serializers.CmcCryptoCurrency;
import edu.ucab.cryptomonitor.serializers.CmcCryptoListing;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class CmcAPI {

    private static final String API_KEY = "496dc4c2-ca7d-4a59-a5eb-27fe6f2d6fd7";
    private static final String BASE_URI = "https://pro-api.coinmarketcap.com/";
    private static final String CRYPTO_LISTING_URI = BASE_URI + "v1/cryptocurrency/listings/latest";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();

    @SuppressWarnings("serial")
    public static class RequestFailed extends Exception {
        public RequestFailed(String errorMessage) {
            super(errorMessage);
        }
    }

    public static CmcCryptoListing getCryptoListing() throws RequestFailed {

        ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("start", "1"));
        parameters.add(new BasicNameValuePair("limit", "10"));
        parameters.add(new BasicNameValuePair("convert","USD"));

        try {
            URIBuilder query = new URIBuilder(CRYPTO_LISTING_URI);
            query.addParameters(parameters);            
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(query.build());
            request.setHeader(HttpHeaders.ACCEPT, "application/json");
            request.addHeader("X-CMC_PRO_API_KEY", API_KEY);
            
            CloseableHttpResponse response = client.execute(request);
            try {
                int code = response.getStatusLine().getStatusCode();
                if (code != 200)
                    throw new RequestFailed(String.format("Request fallido con codigo %d", code));
                HttpEntity entity = response.getEntity();
                String response_text = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                return gson.fromJson(response_text, CmcCryptoListing.class);
            } finally {
                response.close();
            }
        } catch (URISyntaxException e) {
            throw new RequestFailed("Ensamblamiento de query fallido");
        } catch (ClientProtocolException e) {
            throw new RequestFailed("Protocolo de cliente invalido");
        } catch (IOException e) {
            throw new RequestFailed("Never");
        }

    }

    public static void updateLatestCurrencies() throws IOException, ValidationError, RequestFailed {
        Currency.objects.drop();
        CmcCryptoListing top10 = CmcAPI.getCryptoListing();
        for (CmcCryptoCurrency cmcCurrency: top10.data) {
            new Currency(
                cmcCurrency.cmc_rank,
                cmcCurrency.symbol,
                cmcCurrency.name,
                cmcCurrency.quote.USD.price,
                cmcCurrency.quote.USD.market_cap,
                cmcCurrency.quote.USD.volume_24h,
                cmcCurrency.quote.USD.percent_change_1h,
                cmcCurrency.quote.USD.percent_change_24h,
                cmcCurrency.quote.USD.percent_change_7d,
                cmcCurrency.quote.USD.last_updated
            ).save();
        }
    }

}
