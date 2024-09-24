package com.penateam.dummyBank;

import com.penateam.dummyBank.BankAccount;
import com.penateam.dummyBank.BankService;

import com.webcohesion.ofx4j.generated.OFX;
import com.webcohesion.ofx4j.io.OFXHandler;
import com.webcohesion.ofx4j.io.OFXParseException;
import com.webcohesion.ofx4j.io.OFXReader;
import com.webcohesion.ofx4j.io.OFXV2ContentHandler;
import com.webcohesion.ofx4j.io.nanoxml.NanoXMLOFXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import com.penateam.dummyBank.CustomOFXHandler;

@RestController
@RequestMapping("/ofx")
public class OFXController {
    @Autowired
    private BankService bankService;

    @PostMapping
    public String handleOfxRequest(@RequestBody String ofxContent){
        StringBuilder responseBuilder = new StringBuilder();
        String sukses = "Sukses terus bang";
        try {
            // Convert the OFX Request content into InputStream
            ByteArrayInputStream inputStream = new ByteArrayInputStream(ofxContent.getBytes());

            //Create a new OFXReader and handler
            OFXReader ofxReader = new NanoXMLOFXReader();
            OFXReader ofxReaderNotUsed = new OFXReader() {
                private OFXHandler handler;

                @Override
                public void setContentHandler(OFXHandler handler) {
                    this.handler = handler; // Store the handler
                }

                @Override
                public void parse(InputStream stream) throws IOException, OFXParseException {

                }

                @Override
                public void parse(Reader reader) throws IOException, OFXParseException {

                }
            };
            CustomOFXHandler handler = new CustomOFXHandler(bankService, responseBuilder);
            ofxReader.setContentHandler(handler);

            ofxReader.parse(inputStream);

            // Log the response to the terminal (console)
            System.out.println(responseBuilder.toString()); // Print response to terminal


            return "OFX Request process successfully" + responseBuilder.toString();
        } catch (OFXParseException | IOException e){
            e.printStackTrace();
            return "Error Processing OFX Request: " + e.getMessage();
        }
    }
}
