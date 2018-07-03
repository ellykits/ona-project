package com.coder.nerdsoft.projectx.presentation.common;

import com.coder.nerdsoft.projectx.domain.common.TransformerForObservable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ObservableAsyncTransformer<T> extends TransformerForObservable<T>{

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}