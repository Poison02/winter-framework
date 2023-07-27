package cdu.zch.winter.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Zch
 * @date 2023/7/26
 **/
@FunctionalInterface
public interface InputStreamCallback<T> {

    T doWithInputStream(InputStream stream) throws IOException;

}
