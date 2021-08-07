package edu.ucab.cryptomonitor.models;

import java.util.Comparator;

public class CurrencyAscendingRank implements Comparator<Currency> {
    public int compare(Currency a, Currency b) {
        return a.getRank() - b.getRank();
    }
}
