package com.app.service.impl;

import com.app.service.HtmlService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static j2html.TagCreator.*;
@Service
public class HtmlServiceImpl implements HtmlService {
    @Override
    public <T> String toHtml(String header, T t) {
        return div(
                h2(header),
                p(t.toString())
        ).render();
    }

    @Override
    public <T> String toHtmlMany(String header, List<T> items) {
        return div(
                h2(header),
                ol()
                        .with(each(
                                items,
                                item -> li(item.toString()))))
                        .render();
    }

    @Override
    public <K, V> String toHtmlPairs(String header, LinkedHashMap<K, V> pairs) {
        return div(
                h2(header),
                ol()
                        .with(each(
                                pairs.entrySet(),
                                item -> li(item.getKey().toString() + ", " + item.getValue().toString())
                        ))
        ).render();
    }
}
