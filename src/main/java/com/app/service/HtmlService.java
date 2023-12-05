package com.app.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface HtmlService {
    <T> String toHtml(String header, T t);
    <T> String manyToHtml(String header, List<T> items);
    <K,V> String pairsToHtml(String header, Map<K,V> pairs);
}
