package com.coder.nerdsoft.projectx.presentation.common;

import com.coder.nerdsoft.projectx.domain.common.TransformerForCompletable;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CompletableAsyncTransformer extends TransformerForCompletable {
    @Override
    public CompletableSource apply(Completable upstream) {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
