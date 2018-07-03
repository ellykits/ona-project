package com.coder.nerdsoft.projectx.presentation.common;

import com.coder.nerdsoft.projectx.domain.common.TransformerForSingle;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SingleAsyncTransformer<T> extends TransformerForSingle <T>{

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}

