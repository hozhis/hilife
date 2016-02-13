package cn.dolphinsoft.hilife.common.authority;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Class Name: BodyReaderHttpServletRequestWrapper Description: servletRequest包装类
 * 
 * @author hozhis
 *
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public final static String BODY_NONEMPTY = "request.body.nonempty";

    private final byte[] body;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        boolean nonempty = false;
        InputStream in = request.getInputStream();
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100]; // buff用于存放循环读取的临时数据
        int rc = 0;
        while ((rc = in.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        body = swapStream.toByteArray();
        if (body.length != 0) {
            nonempty = true;
        }
        request.setAttribute(BODY_NONEMPTY, nonempty);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

}
