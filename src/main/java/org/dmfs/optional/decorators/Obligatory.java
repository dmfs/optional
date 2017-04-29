/*
 * Copyright 2017 dmfs GmbH
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dmfs.optional.decorators;

import org.dmfs.optional.Optional;

import java.util.NoSuchElementException;


/**
 * An {@link Optional} decorator that is guaranteed to be always present. If the decorated {@link Optional} is not present this will fall back to a given value.
 *
 * @author Marten Gajda
 */
public final class Obligatory<T> implements Optional<T>
{
    private final Optional<T> mDelegate;
    private final T mFallback;


    /**
     * Makes the given {@link Optional} obligatory by providing a fallback value if it's not present.
     *
     * @param delegate
     *         The original {@link Optional}.
     * @param fallback
     *         The fallback to return in case the delegate is not present.
     */
    public Obligatory(Optional<T> delegate, T fallback)
    {
        mDelegate = delegate;
        mFallback = fallback;
    }


    @Override
    public boolean isPresent()
    {
        return true;
    }


    @Override
    public T value(T defaultValue)
    {
        return value();
    }


    @Override
    public T value() throws NoSuchElementException
    {
        return mDelegate.value(mFallback);
    }
}
