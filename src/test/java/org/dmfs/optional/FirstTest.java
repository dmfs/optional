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

import java.util.Arrays;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.*;


/**
 * @author Marten Gajda
 */
public class FirstTest
{
    @Test
    public void testIsPresent() throws Exception
    {
        assertFalse(new First<>(Arrays.asList()).isPresent());
        assertTrue(new First<>(Arrays.asList("test")).isPresent());
        assertTrue(new First<>(Arrays.asList("test", "test123")).isPresent());
    }


    @Test(expected = NoSuchElementException.class)
    public void testAbsentValue() throws Exception
    {
        new First<>(Arrays.asList()).value();
    }


    @Test
    public void testValue() throws Exception
    {
        assertEquals("test", new First<>(Arrays.asList("test")).value());
        assertEquals("test", new First<>(Arrays.asList("test", "test123")).value());
    }


    @Test
    public void testValue1() throws Exception
    {
        assertEquals("xyz", new First<>(Arrays.asList()).value("xyz"));
        assertEquals("test", new First<>(Arrays.asList("test")).value("xyz"));
        assertEquals("test", new First<>(Arrays.asList("test", "test123")).value("xyz"));
    }
}