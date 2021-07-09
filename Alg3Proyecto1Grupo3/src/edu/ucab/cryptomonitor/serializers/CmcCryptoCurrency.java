package edu.ucab.cryptomonitor.serializers;

import java.util.List;
import java.util.Date;

public class CmcCryptoCurrency {

//    "id": 1,
//    "name": "Bitcoin",
//    "symbol": "BTC",
//    "slug": "bitcoin",
//    "num_market_pairs": 9162,
//    "date_added": "2013-04-28T00:00:00.000Z",
//    "tags": [""],
//    "max_supply": 21000000,
//    "circulating_supply": 18751281,
//    "total_supply": 18751281,
//    "platform": null,
//    "cmc_rank": 1,
//    "last_updated": "2021-07-08T22:25:02.000Z",
//    "quote": {...}
    
    public int id;
    public String name;
    public String symbol;
    public String slug;
    public long num_market_pairs;
    public String date_added;
    public List<String> tags;
    public double max_supply;
    public double circulating_supply;
    public double total_supply;
    public transient String platform;
    public int cmc_rank;
    public Date last_updated;
    public CmcCryptoCurrencyQuoteMap quote;

}
