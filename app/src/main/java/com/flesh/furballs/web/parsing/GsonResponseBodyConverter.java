package com.flesh.furballs.web.parsing;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by aaronfleshner on 11/5/17.
 */

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        String json = new XmlToJson.Builder(value.string()).build().toString();
        JsonReader jsonReader = gson.newJsonReader(new StringReader(json));
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
