package com.reference.project;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class HttpsClient {
    public static int responseCode;
    public static String content;

    public static void main(String[] args) {
        new HttpsClient().get("https://google.com");
        System.out.println(responseCode);
        System.out.println(content);
    }

    public void get(String url) {
        new HttpsClient().response(url);
    }

    private void response(String https_url) {
        URL url;
        try {

            url = new URL(https_url);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            if (con != null) {
                try {
                    responseCode = con.getResponseCode();
                } catch (SSLPeerUnverifiedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            print_content(con);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void print_content(HttpsURLConnection con) {
        if (con != null) {

            try {
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(con.getInputStream()));

                String input;
                while ((input = br.readLine()) != null) {
                    content = content + input + "\r\n";
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
