/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.idolutils.processors;

import com.autonomy.aci.client.services.Processor;
import com.autonomy.aci.client.services.ProcessorException;
import com.autonomy.aci.client.transport.AciResponseInputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;

public class CopyResponseProcessor implements Processor<Boolean> {

    private static final long serialVersionUID = 8869650394998971739L;

    private final transient OutputStream outputStream;

    public CopyResponseProcessor(final OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public Boolean process(final AciResponseInputStream aciResponse) {
        try {
            IOUtils.copy(aciResponse, outputStream);

            outputStream.flush();

            return true;
        }
        catch(final IOException e) {
            throw new ProcessorException(e);
        }
    }
}
