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

import org.dmfs.optional.Absent;
import org.dmfs.optional.Present;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author Marten Gajda
 */
public class ObligatoryTest
{
    @Test
    public void testIsPresent() throws Exception
    {
        assertTrue(new Obligatory<>(Absent.<String>absent(), "fallback").isPresent());
        assertTrue(new Obligatory<>(new Present<String>("value"), "fallback").isPresent());
    }


    @Test
    public void testValue() throws Exception
    {
        assertEquals("fallback", new Obligatory<>(Absent.<String>absent(), "fallback").value());
        assertEquals("value", new Obligatory<>(new Present<String>("value"), "fallback").value());
    }


    @Test
    public void testValue1() throws Exception
    {
        assertEquals("fallback", new Obligatory<>(Absent.<String>absent(), "fallback").value("default"));
        assertEquals("value", new Obligatory<>(new Present<String>("value"), "fallback").value("default"));
    }

}