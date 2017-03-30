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

import org.dmfs.optional.Absent;
import org.dmfs.optional.Present;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * @author Marten Gajda
 */
public class OptIterTest
{
    @Test(expected = NoSuchElementException.class)
    public void testAbsentIterator() throws Exception
    {
        new OptIter<>(new Absent<String>()).iterator().next();
    }


    @Test
    public void testAbsentIteratorHasNext() throws Exception
    {
        assertFalse(new OptIter<>(new Absent<String>()).iterator().hasNext());
    }


    @Test
    public void testPresentIterator() throws Exception
    {
        Iterator<String> iterator = new OptIter<>(new Present<>("Test")).iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }


    @Test(expected = NoSuchElementException.class)
    public void testPresentIterator2() throws Exception
    {
        Iterator<String> iterator = new OptIter<>(new Present<>("Test")).iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
        iterator.next();
    }


    @Test(expected = UnsupportedOperationException.class)
    public void testHashCode() throws Exception
    {
        new OptIter<>(new Present<>("")).hashCode();
    }


    @Test(expected = UnsupportedOperationException.class)
    public void testEquals() throws Exception
    {
        new OptIter<>(new Present<>("")).equals("");
    }

}