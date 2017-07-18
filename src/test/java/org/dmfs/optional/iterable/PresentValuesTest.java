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

package org.dmfs.optional.iterable;

import org.dmfs.iterables.ArrayIterable;
import org.dmfs.optional.Absent;
import org.dmfs.optional.Optional;
import org.dmfs.optional.Present;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Unit test for {@link PresentValues}.
 *
 * @author Gabor Keszthelyi
 */
public final class PresentValuesTest
{

    private static final Absent<String> ABSENT = Absent.absent();


    @Test
    public void test_various()
    {
        Iterable<Optional<String>> optionals = new ArrayIterable<>(
                new Present<>("1"),
                ABSENT,
                ABSENT,
                new Present<>("2"),
                new Present<>("3"),
                ABSENT
        );

        PresentValues<String> result = new PresentValues<>(optionals);
        Iterator<String> resultIterator = result.iterator();

        assertTrue(resultIterator.hasNext());
        assertEquals("1", resultIterator.next());
        assertTrue(resultIterator.hasNext());
        assertEquals("2", resultIterator.next());
        assertTrue(resultIterator.hasNext());
        assertEquals("3", resultIterator.next());

        assertFalse(resultIterator.hasNext());
    }

}