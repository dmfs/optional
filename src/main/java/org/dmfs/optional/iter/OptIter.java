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

package org.dmfs.optional.iter;

import org.dmfs.iterators.EmptyIterator;
import org.dmfs.iterators.SingletonIterator;
import org.dmfs.optional.Optional;

import java.util.Iterator;


/**
 * An {@link Iterable} to iterate an {@link Optional} value. If the value is present this will iterate exactly one value, otherwise it will iterate no values.
 *
 * @author Marten Gajda
 */
public final class OptIter<T> implements Iterable<T>
{
    private final Optional<T> mOptional;


    /**
     * Create an {@link Iterable} from the given {@link Optional}.
     *
     * @param optional
     *         The {@link Optional} to iterate.
     */
    public OptIter(Optional<T> optional)
    {
        mOptional = optional;
    }


    @Override
    public Iterator<T> iterator()
    {
        return mOptional.isPresent() ? new SingletonIterator<>(mOptional.value()) : EmptyIterator.<T>instance();
    }


    // TODO inherit these from an abstract  NoObject class
    @Override
    public int hashCode()
    {
        throw new UnsupportedOperationException("This Object is not hashable");
    }


    @Override
    public boolean equals(Object obj)
    {
        throw new UnsupportedOperationException("This Object is not comparable");
    }
}
