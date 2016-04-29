/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.idolutils.processors;

import com.hp.autonomy.idolutils.IdolXmlMarshaller;
import com.hp.autonomy.types.idol.GetStatusResponseData;
import com.hp.autonomy.types.idol.QueryResponseData;
import com.hp.autonomy.types.idol.content.Blacklist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class AciResponseProcessorFactoryTest {
    @Mock
    private IdolXmlMarshaller idolXmlMarshaller;

    private AciResponseJaxbProcessorFactory aciResponseProcessorFactory;

    @Before
    public void setUp() {
        aciResponseProcessorFactory = new AciResponseJaxbProcessorFactory();
    }

    @Test
    public void createAciResponseProcessor() {
        assertNotNull(aciResponseProcessorFactory.createAciResponseProcessor(GetStatusResponseData.class));
    }

    @Test
    public void createQueryAciResponseProcessor() {
        assertNotNull(aciResponseProcessorFactory.createQueryAciResponseProcessor(QueryResponseData.class, Blacklist.class));
    }

    @Test
    public void createEmptyAciResponseProcessor() {
        assertNotNull(aciResponseProcessorFactory.createEmptyAciResponseProcessor());
    }

    @Test
    public void processorException() {
        assertNotNull(aciResponseProcessorFactory.createAciResponseProcessor(GetStatusResponseData.class));
    }

    @Test
    public void testName() {
        assertNotNull(aciResponseProcessorFactory.getMarshaller());
    }
}
