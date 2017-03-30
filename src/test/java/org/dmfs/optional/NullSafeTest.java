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

package org.dmfs.optional;

import org.junit.Test;

import java.util.NoSuchElementException;

import static junit.framework.TestCase.*;


/**
 * @author Marten Gajda
 */
public class NullSafeTest
{
    @Test
    public void testIsPresent() throws Exception
    {
        assertFalse(new NullSafe<>(null).isPresent());
        assertTrue(new NullSafe<>("test").isPresent());
    }


    @Test(expected = NoSuchElementException.class)
    public void testAbsentValue() throws Exception
    {
        new NullSafe<>(null).value();
    }


    @Test
    public void testValue() throws Exception
    {
        assertEquals("test", new NullSafe<>("test").value());
    }


    @Test
    public void testValue1() throws Exception
    {
        assertEquals("xyz", new NullSafe<>(null).value("xyz"));
        assertEquals("test", new NullSafe<>("test").value("xyz"));
    }

}