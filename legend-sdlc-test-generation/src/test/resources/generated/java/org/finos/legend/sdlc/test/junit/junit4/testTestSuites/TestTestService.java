package org.finos.legend.sdlc.test.junit.junit4.testTestSuites;

import org.finos.legend.sdlc.test.junit.pure.v1.AbstractServiceTest;
import org.junit.jupiter.api.Test;

public class TestTestService extends AbstractServiceTest
{
    @Override
    protected String getEntityPath()
    {
        return "testTestSuites::TestService";
    }
}
