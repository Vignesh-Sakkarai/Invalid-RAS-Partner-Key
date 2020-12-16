import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.Key;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Enumeration;
public class KeyStoreReader
{
    public static void main(String[] args)
    {
        String password = "p12-given-password";
        try
        {
            FileInputStream p12data = new FileInputStream(new File("C:\\Users\\Test.p12"));
            KeyStore ks = KeyStore.getInstance("PKCS12");
			//Newly provided Instance of keystore is always take the value as defaultType, so convert to PKCS12
            //ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(new ByteArrayInputStream(readAllBytes(p12data)), password.toCharArray());
            System.out.println("PASSED");

        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Exception while loading p12 data", e);
        }
    }

    public static byte[] readAllBytes(InputStream inputStream) throws IOException {
        final int bufLen = 1024;
        byte[] buf = new byte[bufLen];
        int readLen;
        IOException exception = null;

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            while ((readLen = inputStream.read(buf, 0, bufLen)) != -1)
                outputStream.write(buf, 0, readLen);

            return outputStream.toByteArray();
        } catch (IOException e) {
            exception = e;
            throw e;
        } finally {
            if (exception == null) inputStream.close();
            else try {
                inputStream.close();
            } catch (IOException e) {
                exception.addSuppressed(e);
            }
        }
    }
}
