package com.coder.nerdsoft.projectx.data.util;

import com.coder.nerdsoft.projectx.data.remote.RetrofitBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class ErrorUtil {

    public static <T> T parseError(ResponseBody response, RetrofitBuilder retrofitBuilder,
                                   Class<T> errorModel){
        Converter<ResponseBody,T> converter = retrofitBuilder.getRetrofit()
                .responseBodyConverter(errorModel, new Annotation[0]);

        T error = null;

        try {

            error = converter.convert(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return error;
    }
}
