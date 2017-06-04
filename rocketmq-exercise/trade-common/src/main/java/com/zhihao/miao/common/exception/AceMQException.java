package com.zhihao.miao.common.exception;

public class AceMQException extends Exception {
    private static final long serialVersionUID = -8007998245336817227L;

    public AceMQException() {
        super();
    }

    public AceMQException(Throwable cause) {
        super(cause);
    }

    public AceMQException(String message) {
        super(message);
    }

    public AceMQException(String message, Throwable cause) {
        super(message, cause);
    }
}
