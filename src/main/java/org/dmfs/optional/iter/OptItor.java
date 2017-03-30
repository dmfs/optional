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

import org.dmfs.iterators.AbstractBaseIterator;
import org.dmfs.iterators.SingletonIterator;
import org.dmfs.optional.Optional;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * An {@link Iterator} that iterates an {@link Optional} value, if present. This is similar to {@link SingletonIterator} except that it can be empty.
 *
 * @author Marten Gajda
 */
public final class OptItor<T> extends AbstractBaseIterator<T>
{
    private final Optional<T> mOptional;
    private boolean mIterated;


    /**
     * Create an {@link Iterator} from an {@link Optional} value.
     *
     * @param optional
     *         The {@link Optional} value to iterate.
     */
    public OptItor(Optional<T> optional)
    {
        mOptional = optional;
    }


    @Override
    public boolean hasNext()
    {
        return !mIterated && mOptional.isPresent();
    }


    @Override
    public T next()
    {
        if (mIterated)
        {
            throw new NoSuchElementException("No element already iterated.");
        }
        if (!mOptional.isPresent())
        {
            throw new NoSuchElementException("No element to iterate.");
        }
        mIterated = true;
        return mOptional.value();
    }


    // TODO add these to AbstractBaseIterator
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
