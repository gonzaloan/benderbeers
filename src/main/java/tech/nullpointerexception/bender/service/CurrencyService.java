package tech.nullpointerexception.bender.service;

public interface CurrencyService {

    Double convertBetweenCurrency(String from, String to, Double value);
}
