package demo.sasl.local;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Select SASL mechanism: 1=DIGEST-MD5, 2=CRAM-MD5, 3=PLAIN, 4=DID-CHALLENGE");
        String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
        switch (Integer.parseInt(line)) {
            case 1: new SaslLocalDemoDigestMD5().run(); break;
            case 2: new SaslLocalDemoCramMD5().run(); break;
            case 3: new SaslLocalDemoPlain().run(); break;
            case 4: new SaslLocalDemoDIDChallenge().run(); break;
            default: System.out.println("Invalid choice: " + line); break;
        }
    }
}