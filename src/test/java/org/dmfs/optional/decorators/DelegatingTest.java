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
import org.dmfs.optional.Optional;
import org.dmfs.optional.Present;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


/**
 * @author Marten Gajda
 */
public class DelegatingTest
{
    @Test
    public void testIsPresent() throws Exception
    {
        assertThat(new TestOptional<>(new Absent<>()).isPresent(), is(false));
        assertThat(new TestOptional<>(new Present<>("test")).isPresent(), is(true));
    }


    @Test
    public void testValue() throws Exception
    {
        assertThat(new TestOptional<>(new Present<>("test")).value(), is("test"));
    }


    @Test(expected = NoSuchElementException.class)
    public void testAbsentValue() throws Exception
    {
        new TestOptional<>(new Absent<>()).value();
    }


    @Test
    public void testDefaultValue() throws Exception
    {
        assertThat(new TestOptional<>(new Present<>("test")).value("default"), is("test"));
        assertThat(new TestOptional<>(new Absent<String>()).value("default"), is("default"));
    }


    @Test
    public void testHashCode() throws Exception
    {
        assertThat(new TestOptional<>(new Optional<String>()
        {
            @Override
            public boolean isPresent()
            {
                fail("isPresent called");
                return false;
            }


            @Override
            public String value(String defaultValue)
            {
                fail("value called");
                return null;
            }


            @Override
            public String value() throws NoSuchElementException
            {
                fail("value called");
                return null;
            }


            @Override
            public int hashCode()
            {
                return 123456;
            }
        }).hashCode(), is(123456));
    }


    @Test
    public void testEquals() throws Exception
    {
        final Object testObject = new Object();
        assertThat(new TestOptional<>(new Optional<String>()
        {
            @Override
            public boolean isPresent()
            {
                fail("isPresent called");
                return false;
            }


            @Override
            public String value(String defaultValue)
            {
                fail("value called");
                return null;
            }


            @Override
            public String value() throws NoSuchElementException
            {
                fail("value called");
                return null;
            }


            @Override
            public boolean equals(Object obj)
            {
                assertThat(obj, sameInstance(testObject));
                return true;
            }
        }).equals(testObject), is(true));

        assertThat(new TestOptional<>(new Optional<String>()
        {
            @Override
            public boolean isPresent()
            {
                fail("isPresent called");
                return false;
            }


            @Override
            public String value(String defaultValue)
            {
                fail("value called");
                return null;
            }


            @Override
            public String value() throws NoSuchElementException
            {
                fail("value called");
                return null;
            }


            @Override
            public boolean equals(Object obj)
            {
                assertThat(obj, sameInstance(testObject));
                return false;
            }
        }).equals(testObject), is(false));
    }


    @Test
    public void testToString() throws Exception
    {
        final String testString = "testing";
        assertThat(new TestOptional<>(new Optional<String>()
        {
            @Override
            public boolean isPresent()
            {
                fail("isPresent called");
                return false;
            }


            @Override
            public String value(String defaultValue)
            {
                fail("value called");
                return null;
            }


            @Override
            public String value() throws NoSuchElementException
            {
                fail("value called");
                return null;
            }


            @Override
            public String toString()
            {
                return testString;
            }
        }).toString(), sameInstance(testString));

    }


    private final class TestOptional<T> extends Delegating<T>
    {
        public TestOptional(Optional<T> delegate)
        {
            super(delegate);
        }
    }
}