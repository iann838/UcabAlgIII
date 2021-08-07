package edu.ucab.cryptomonitor.api;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.ucab.cryptomonitor.models.Currency;
import edu.ucab.cryptomonitor.serializers.CryptoInfo;
import edu.ucab.cryptomonitor.serializers.CryptoMarket;

public class CryptoAPI {

    private static final String BASE_URI = "https://api.paaksing.com/";
    private static final String CRYPTO_INFO_URI = BASE_URI + "crypto/v1/info";
    private static final String CRYPTO_MARKET_URI = BASE_URI + "crypto/v1/market/%d";
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

    public static List<CryptoInfo> getInfo() throws RequestFailed {
        try {
            URIBuilder query = new URIBuilder(CRYPTO_INFO_URI);    
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(query.build());
            request.setHeader(HttpHeaders.ACCEPT, "application/json");
            
            CloseableHttpResponse response = client.execute(request);
            try {
                int code = response.getStatusLine().getStatusCode();
                if (code != 200)
                    throw new RequestFailed(String.format("Request fallido con codigo %d", code));
                HttpEntity entity = response.getEntity();
                String response_text = EntityUtils.toString(entity);
                EntityUtils.consume(entity);

                Type cryptoInfoList = new TypeToken<List<CryptoInfo>>(){}.getType();
                return gson.fromJson(response_text, cryptoInfoList);
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
    
    public static CryptoMarket getMarket(long id) throws RequestFailed {
        try {
            URIBuilder query = new URIBuilder(String.format(CRYPTO_MARKET_URI, id));    
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(query.build());
            request.setHeader(HttpHeaders.ACCEPT, "application/json");
            
            CloseableHttpResponse response = client.execute(request);
            try {
                int code = response.getStatusLine().getStatusCode();
                if (code != 200)
                    throw new RequestFailed(String.format("Request fallido con codigo %d", code));
                HttpEntity entity = response.getEntity();
                String response_text = EntityUtils.toString(entity);
                EntityUtils.consume(entity);

                return gson.fromJson(response_text, CryptoMarket.class);
            } finally {
                response.close();
            }
        } catch (URISyntaxException e) {
            throw new RequestFailed("Ensamblamiento de URI fallido");
        } catch (ClientProtocolException e) {
            throw new RequestFailed("Protocolo de cliente invalido");
        } catch (IOException e) {
            throw new RequestFailed("Never");
        }
    }

    public static void updateLatest() throws RequestFailed {
        List<CryptoInfo> cryptoInfos = getInfo();
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(100);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(0, 10, 0, TimeUnit.SECONDS, queue);
        for (CryptoInfo cryptoInfo: cryptoInfos) {
            if (cryptoInfo.rank > 60)
                continue;
            Currency currency = new Currency();
            currency.setId(cryptoInfo.id);
            currency.setRank(cryptoInfo.rank);
            currency.setTag(cryptoInfo.symbol);
            currency.setName(cryptoInfo.name);
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        CryptoMarket cryptoMarket = getMarket(currency.getId());
                        currency.setPrice(cryptoMarket.price);
                        currency.setPrice1hpc(cryptoMarket.price1hpc);
                        currency.setPrice24hpc(cryptoMarket.price24hpc);
                        currency.setPrice7dpc(cryptoMarket.price7dpc);
                        currency.setPrice30dpc(cryptoMarket.price30dpc);
                        currency.setMarketCap(cryptoMarket.marketCap);
                        currency.setMarketCap24hpc(cryptoMarket.marketCap24hpc);
                        currency.setVolume(cryptoMarket.volume);
                        currency.setVolume24hpc(cryptoMarket.volume24hpc);
                        currency.setCirculatingSupply(cryptoMarket.circulatingSupply);
                        currency.setFullyDilutedMarketCap(cryptoMarket.fullyDilutedMarketCap);
                        currency.setFullyDilutedMarketCap24hpc(cryptoMarket.fullyDilutedMarketCap24hpc);
                        currency.setTotalSupply(cryptoMarket.totalSupply);
                        currency.setLastUpdated(new Date());
                        currency.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        pool.shutdown();
    }

}
