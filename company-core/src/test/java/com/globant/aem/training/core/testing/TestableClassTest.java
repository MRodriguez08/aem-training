package com.globant.aem.training.core.testing;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.junit.Test;

public class TestableClassTest {

    @Test
    public void testStripNonLettersOrNumbers() {
        TestableClass tc = new TestableClass();
        assertEquals("abc1", tc.stripNonLettersOrNumbers("a_b!c.1"));
    }

    @Test
    public void testGetContentPath() throws RepositoryException {
        // create a mock repository session and prepare the expected method calls
        final Session SESSION_MOCK = createMock(Session.class);
        final Node ROOT_NODE_MOCK = createMock(Node.class);
        expect(SESSION_MOCK.getRootNode()).andReturn(ROOT_NODE_MOCK);
        expect(ROOT_NODE_MOCK.hasNode(TestableClass.CONTENT_NODENAME)).andReturn(true);

        final Node CONTENT_NODE_MOCK = createMock(Node.class);
        expect(ROOT_NODE_MOCK.getNode(TestableClass.CONTENT_NODENAME)).andReturn(CONTENT_NODE_MOCK);
        expect(CONTENT_NODE_MOCK.getPath()).andReturn("/content");
        replayAll();

        TestableClass tc = new TestableClass();
        assertEquals("/content", tc.getContentPath(SESSION_MOCK));

        // verify that all expected methods calls have been executed
        verifyAll();

    }

}
