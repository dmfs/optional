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

import static org.junit.Assert.*;


/**
 * @author Marten Gajda
 */
public class OptItorTest
{
    @Test
    public void testAbsentHasNext() throws Exception
    {
        assertFalse(new OptItor<>(new Absent<String>()).hasNext());
    }


    @Test
    public void testPresentHasNext() throws Exception
    {
        assertTrue(new OptItor<>(new Present<>("")).hasNext());
    }


    @Test(expected = NoSuchElementException.class)
    public void testAbsentNext() throws Exception
    {
        new OptItor<>(new Absent<String>()).next();
    }


    @Test
    public void testPresentNext() throws Exception
    {
        assertEquals("testxyz", new OptItor<>(new Present<>("testxyz")).next());
    }


    @Test(expected = NoSuchElementException.class)
    public void testPresentNextNext() throws Exception
    {
        Iterator<String> it = new OptItor<>(new Present<>("testxyz"));
        it.next();
        it.next();
    }


    @Test(expected = UnsupportedOperationException.class)
    public void testHashCode() throws Exception
    {
        new OptItor<>(new Present<>("")).hashCode();
    }


    @Test(expected = UnsupportedOperationException.class)
    public void testEquals() throws Exception
    {
        new OptItor<>(new Present<>("")).equals("");
    }

}