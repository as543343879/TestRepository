package com.mycompany.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.Charset;

import de.uzl.itm.ncoap.application.client.ClientCallback;
import de.uzl.itm.ncoap.application.client.CoapClient;
import de.uzl.itm.ncoap.message.CoapRequest;
import de.uzl.itm.ncoap.message.CoapResponse;
import de.uzl.itm.ncoap.message.MessageCode;
import de.uzl.itm.ncoap.message.MessageType;
import de.uzl.itm.ncoap.message.options.Option;
import de.uzl.itm.ncoap.message.options.OptionValue;
import de.uzl.itm.ncoap.message.options.StringOptionValue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    public void test() {
        try {
            CoapClient coapClient = new CoapClient();
            CoapRequest coapRequest = new CoapRequest(
                    MessageType.NON,
                    MessageCode.POST,
                    new URI("coap://192.168.3.133:5683/resource01")
            );
            coapClient.sendCoapRequest(
                coapRequest,
                new InetSocketAddress("192.168.3.133", 5683),
                new ClientCallback() {
                    @Override
                    public void processCoapResponse(CoapResponse coapResponse) {
                        String message = coapResponse.getContent().toString(Charset.forName("UTF-8"));
                        Log.i("mylog", message);
                    }
                }
            );
        } catch(Exception e) {
            Log.i("mylog", e.toString());
        }
    }

}
