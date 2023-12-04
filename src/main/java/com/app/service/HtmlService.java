package com.app.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface HtmlService {
    <T> String toHtml(String header, T t);
    <T> String toHtmlMany(String header, List<T> items);
    <K,V> String toHtmlPairs(String header, LinkedHashMap<K,V> pairs);
}
