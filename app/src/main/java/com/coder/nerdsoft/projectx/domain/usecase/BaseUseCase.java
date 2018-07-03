package com.coder.nerdsoft.projectx.domain.usecase;

import com.coder.nerdsoft.projectx.domain.common.TransformerForSingle;
import com.fernandocejas.arrow.optional.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import io.reactivex.Single;

/**
 * BaseUseCase class extended by all use cases
 * This class accepts a TransformerForObservable in its constructor that is used
 * to manage thread execution and also returns the observable to be used in upper layers
 *
 * @param <T> type of the returned value
 */
public abstract class BaseUseCase<T> {
    private final TransformerForSingle<T> transformer;

    BaseUseCase(@NotNull TransformerForSingle<T> transformer) {
        this.transformer = transformer;
    }

    @NotNull
    protected abstract Single<T> createObservable(
            @Nullable Optional<Map<String, ? extends Object>> optionalData);

    public final Single<T> executor(@Nullable Optional<Map<String, ?>> withData) {

        return createObservable(withData).compose(transformer);

    }


}
