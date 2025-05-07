// Copyright 2021 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.sdlc.test.junit;

import junit.framework.Test;
import junit.framework.TestResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * This class is maintained for backward compatibility with JUnit 4.
 * New code should use JUnit 5 directly.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class LegendSDLCTestCase implements Test
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LegendSDLCTestCase.class);

    protected final String entityPath;

    private String name;
    
    protected LegendSDLCTestCase(String entityPath)
    {
        this.entityPath = entityPath;
        this.name = entityPath;
    }
    
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    @BeforeEach
    protected final void setUp() throws Exception
    {
        long start = System.nanoTime();
        LOGGER.info("[{}] Setting up", getName());
        try
        {
            doSetUp();
            long end = System.nanoTime();
            LOGGER.info("[{}] Finished setting up ({}s)", getName(), formatNanosDuration(end - start));
        }
        catch (Throwable t)
        {
            try
            {
                doTearDown();
            }
            catch (Throwable suppress)
            {
                LOGGER.error("[{}] Error attempting to clean up (suppressing)", getName(), suppress);
                t.addSuppressed(suppress);
            }
            long end = System.nanoTime();
            LOGGER.error("[{}] Error setting up ({}s)", getName(), formatNanosDuration(end - start), t);
            throw t;
        }
    }

    @org.junit.jupiter.api.Test
    protected final void doJUnit5Test() throws Exception
    {
        long start = System.nanoTime();
        LOGGER.info("[{}] Starting test", getName());
        try
        {
            doRunTest();
            long end = System.nanoTime();
            LOGGER.info("[{}] SUCCESS ({}s)", getName(), formatNanosDuration(end - start));
        }
        catch (AssertionError e)
        {
            long end = System.nanoTime();
            LOGGER.info("[{}] FAILURE ({}s)", getName(), formatNanosDuration(end - start));
            throw e;
        }
        catch (Throwable t)
        {
            long end = System.nanoTime();
            LOGGER.info("[{}] ERROR ({}s)", getName(), formatNanosDuration(end - start));
            throw t;
        }
    }
    
    @Override
    public int countTestCases()
    {
        return 1;
    }

    @Override
    public void run(TestResult result)
    {
        result.startTest(this);
        try
        {
            setUp();
            try
            {
                doRunTest();
            }
            finally
            {
                tearDown();
            }
        }
        catch (AssertionError e)
        {
            result.addFailure(this, e);
        }
        catch (Throwable t)
        {
            result.addError(this, t);
        }
        finally
        {
            result.endTest(this);
        }
    }

    @AfterEach
    protected final void tearDown() throws Exception
    {
        long start = System.nanoTime();
        LOGGER.info("[{}] Tearing down", getName());
        try
        {
            doTearDown();
            long end = System.nanoTime();
            LOGGER.info("[{}] Finished tearing down ({}s)", getName(), formatNanosDuration(end - start));
        }
        catch (Throwable t)
        {
            long end = System.nanoTime();
            LOGGER.error("[{}] Error tearing down ({}s)", getName(), formatNanosDuration(end - start), t);
            throw t;
        }
    }

    protected void doSetUp() throws Exception
    {
        // Nothing by default
    }

    protected abstract void doRunTest() throws Exception;
    
    protected final void runTest() throws Exception
    {
        doJUnit5Test();
    }

    protected void doTearDown() throws Exception
    {
        // Nothing by default
    }

    private static String formatNanosDuration(long durationNanos)
    {
        if (durationNanos == 0)
        {
            return "0.000000000";
        }

        StringBuilder builder = new StringBuilder(20);
        builder.append(durationNanos);
        int decimalIndex = builder.length() - 9;
        int startIndex = (durationNanos < 0) ? 1 : 0;
        if (decimalIndex > startIndex)
        {
            builder.insert(decimalIndex, '.');
        }
        else if (decimalIndex < startIndex)
        {
            char[] chars = new char[-decimalIndex + 2];
            Arrays.fill(chars, '0');
            chars[1] = '.';
            builder.insert(startIndex, chars);
        }
        else
        {
            // decimalIndex == 0
            builder.insert(startIndex, "0.");
        }
        return builder.toString();
    }
}
