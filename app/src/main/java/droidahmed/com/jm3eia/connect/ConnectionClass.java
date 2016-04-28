package droidahmed.com.jm3eia.connect;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by ahmed on 3/1/2016.
 */
public class ConnectionClass {
    public static String makeRequest(String uRL) throws Exception {

        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(uRL);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            // urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            // urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }
            StringBuilder total = new StringBuilder();

            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());

            BufferedReader r = new BufferedReader(new InputStreamReader(in),
                    8000);
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            in.close();

            return total.toString();

        } catch (MalformedURLException e) {
            // URL is invalid
        } catch (SocketTimeoutException e) {
            // data retrieval or connection timed out
        } catch (IOException e) {
            // could not read response body
        }

        return null;
    }
}
