/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.idolutils.processors;

import com.autonomy.aci.client.transport.AciResponseInputStream;
import com.hp.autonomy.idolutils.IdolXmlMarshaller;
import com.hp.autonomy.types.idol.QueryResponseData;
import com.hp.autonomy.types.idol.content.Blacklist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.omg.CORBA.portable.InputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class QueryAciResponseProcessorTest {
    @Mock
    private IdolXmlMarshaller idolXmlMarshaller;
    @Mock
    private AciResponseInputStream inputStream;

    private QueryAciResponseJaxbProcessor<QueryResponseData> aciResponseProcessor;

    @Before
    public void setUp() {
        aciResponseProcessor = new QueryAciResponseJaxbProcessor<>(idolXmlMarshaller, QueryResponseData.class, Blacklist.class);
    }

    @Test
    public void process() {
        aciResponseProcessor.process(inputStream);
        verify(idolXmlMarshaller).parseIdolQueryResponseData(any(InputStream.class), any(Class.class), any(Class.class));
    }
}