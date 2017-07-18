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

package org.dmfs.optional.iterator;

import org.dmfs.iterators.AbstractBaseIterator;
import org.dmfs.iterators.Filter;
import org.dmfs.iterators.Function;
import org.dmfs.iterators.decorators.Fluent;
import org.dmfs.optional.Optional;

import java.util.Iterator;


/**
 * {@link Iterator<E>} that iterates over the present values from the input {@link Iterator<Optional<E>>}.
 *
 * @author Gabor Keszthelyi
 */
public final class PresentValues<E> extends AbstractBaseIterator<E>
{
    private final Iterator<E> mDelegate;


    public PresentValues(Iterator<Optional<E>> optionals)
    {
        mDelegate = new Fluent<>(optionals)
                .filtered(new Filter<Optional<E>>()
                {
                    @Override
                    public boolean iterate(Optional<E> optional)
                    {
                        return optional.isPresent();
                    }
                })
                .mapped(new Function<Optional<E>, E>()
                {
                    @Override
                    public E apply(Optional<E> optional)
                    {
                        return optional.value();
                    }
                });
    }


    @Override
    public boolean hasNext()
    {
        return mDelegate.hasNext();
    }


    @Override
    public E next()
    {
        return mDelegate.next();
    }
}
