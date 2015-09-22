package com.hp.autonomy.idolutils.processors;

import com.autonomy.aci.client.services.Processor;
import com.autonomy.aci.client.services.ProcessorException;
import com.autonomy.aci.client.transport.AciResponseInputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;

/*
 * $Id$
 *
 * Copyright (c) 2013, Autonomy Systems Ltd.
 *
 * Last modified by $Author$ on $Date$
 */
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
        catch(IOException e) {
            throw new ProcessorException(e);
        }
    }
}
