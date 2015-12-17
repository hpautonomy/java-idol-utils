/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.idolutils.processors;

import com.autonomy.aci.client.services.AciErrorException;
import com.autonomy.aci.client.services.ProcessorException;
import com.hp.autonomy.types.idol.GetStatusResponseData;
import com.hp.autonomy.types.idol.IdolResponseParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class AciResponseProcessorFactoryTest {
    @Mock
    private IdolResponseParser<AciErrorException, ProcessorException> idolResponseParser;

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
    public void processorException() {
        assertNotNull(aciResponseProcessorFactory.createAciResponseProcessor(GetStatusResponseData.class));
    }
}
