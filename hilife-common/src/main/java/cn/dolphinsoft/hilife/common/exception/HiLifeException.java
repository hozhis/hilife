package cn.dolphinsoft.hilife.common.exception;

import cn.dolphinsoft.hilife.common.enumeration.EErrorCode;

/**
 * 
 * Class Name: HiLifeException Description: TODO
 * 
 * @author hozhis
 *
 */
public class HiLifeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected final EErrorCode code;
    protected final String[] params;

    public HiLifeException() {
        super();
        code = EErrorCode.COMM_INTERNAL_ERROR;
        params = null;
    }

    public HiLifeException(EErrorCode code) {
        super();
        this.code = code;
        params = null;
    }

    public HiLifeException(String message) {
        super(message);
        code = EErrorCode.COMM_INTERNAL_ERROR;
        params = null;
    }

    public HiLifeException(String message, EErrorCode code) {
        super(message);
        this.code = code;
        params = null;
    }

    public HiLifeException(String message, EErrorCode code, String... params) {
        super(message);
        this.code = code;
        this.params = params;
    }

    public HiLifeException(String message, Throwable cause, EErrorCode code) {
        super(message, cause);
        this.code = code;
        params = null;
    }

    public HiLifeException(Throwable cause, EErrorCode code) {
        super(cause);
        this.code = code;
        params = null;
    }

    public EErrorCode getCode() {
        return code;
    }

    /**
     * @return the params
     */
    public String[] getParams() {
        return params;
    }

    public HiLifeException(Throwable cause) {
        super(cause);
        code = EErrorCode.COMM_INTERNAL_ERROR;
        params = null;
    }

}
