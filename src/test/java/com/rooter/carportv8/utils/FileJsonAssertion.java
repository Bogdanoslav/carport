package com.rooter.carportv8.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.util.Objects;

public class FileJsonAssertion {
    public static void assertJsonEquals(String expected, String actual)  {
        try {
            JSONObject expectedJson = new JSONObject(expected);
            JSONObject actualJson = new JSONObject(actual);
            JSONAssert.assertEquals(expectedJson,actualJson,false);
        } catch (JSONException jsonException){
            Assertions.fail("Json Parsing exception");
        }
    }
}
