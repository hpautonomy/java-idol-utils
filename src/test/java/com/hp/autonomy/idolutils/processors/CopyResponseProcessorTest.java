/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.idolutils.processors;

import com.autonomy.aci.client.services.Processor;
import com.autonomy.aci.client.services.ProcessorException;
import com.autonomy.aci.client.transport.AciResponseInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CopyResponseProcessorTest {

    private InputStream inputStream;

    @Before
    public void setUp() throws UnsupportedEncodingException {
        inputStream = new ByteArrayInputStream("Hello world!".getBytes("UTF-8"));
    }

    @Test
    public void process() throws IOException {
        final AciResponseInputStream aciResponseInputStream = mock(AciResponseInputStream.class);
        when(aciResponseInputStream.read(any(byte[].class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(final InvocationOnMock invocationOnMock) throws IOException {
                return inputStream.read((byte[]) invocationOnMock.getArguments()[0]);
            }
        });

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final Processor<Boolean> processor = new CopyResponseProcessor(outputStream);
        processor.process(aciResponseInputStream);

        assertThat(outputStream.toString("UTF-8"), is("Hello world!"));
    }

    @Test(expected = ProcessorException.class)
    public void badInput() throws IOException {
        final AciResponseInputStream aciResponseInputStream = mock(AciResponseInputStream.class);
        //noinspection ProhibitedExceptionDeclared
        when(aciResponseInputStream.read(any(byte[].class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(final InvocationOnMock invocationOnMock) throws Throwable {
                throw new IOException();
            }
        });
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final Processor<Boolean> processor = new CopyResponseProcessor(outputStream);
        processor.process(aciResponseInputStream);
    }
}
