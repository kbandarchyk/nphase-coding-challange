package com.nphase.exception;

import java.text.MessageFormat;

public class AppException extends RuntimeException {

    public AppException( final String message ) {
        super( message );
    }

    public AppException( final String templateMessage,
                         final Object... params ) {
        super( MessageFormat.format( templateMessage, params ) );
    }

    public AppException( final String templateMessage,
                         final Throwable cause,
                         final Object... params ) {
        super( MessageFormat.format( templateMessage, params ), cause );
    }
}
