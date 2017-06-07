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

import org.dmfs.iterators.Function;
import org.dmfs.optional.Optional;

import java.util.NoSuchElementException;


/**
 * {@link Optional} that maps an {@link Optional} if it's present using the given {@link Function}.
 *
 * @author Gabor Keszthelyi
 */
public final class Mapped<FROM, TO> implements Optional<TO>
{
    private final Optional<FROM> mFromValue;
    private final Function<FROM, TO> mConversion;


    public Mapped(Function<FROM, TO> conversion, Optional<FROM> fromValue)
    {
        mConversion = conversion;
        mFromValue = fromValue;
    }


    @Override
    public boolean isPresent()
    {
        return mFromValue.isPresent();
    }


    @Override
    public TO value(TO defaultValue)
    {
        return mFromValue.isPresent() ? value() : defaultValue;
    }


    @Override
    public TO value() throws NoSuchElementException
    {
        return mConversion.apply(mFromValue.value());
    }
}