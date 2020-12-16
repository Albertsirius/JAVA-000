package huangzihao.homework.rpcfx.api;

/**
 * <p>统一的Exception
 *
 * @author huangzihao
 * @since 2020/12/16
 */
public class RpxfxException extends Exception{

    public static final String PRC_INVOKE_ERROR = "RPC invoked ERROR!";

    public RpxfxException() {
        super();
    }

    public RpxfxException(Throwable cause) {
        super(cause);
    }

    public RpxfxException(String message) {
        super(message);
    }

    public RpxfxException(String message, Throwable cause) {
        super(message, cause);
    }

}
