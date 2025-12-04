package demo.sasl.local;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("SASL mechanism? 1=DIGEST-MD5, 2=CRAM-MD5, 3=PLAIN, 4=DID-CHALLENGE");
        String lineSaslMechanism = bufferedReader.readLine();
        System.out.println("User integration? 1=Demo, 2=Interactive");
        String lineUserIntegration = bufferedReader.readLine();
        boolean interactive = switch(lineUserIntegration) {
            case "1" -> false;
            case "2" -> true;
            default -> { System.out.println("Invalid choice: " + lineUserIntegration); System.exit(1); yield false; }
        };
        SaslLocalDemo saslLocalDemo = switch (Integer.parseInt(lineSaslMechanism)) {
            case 1 -> new SaslLocalDemoDigestMD5(interactive);
            case 2 -> new SaslLocalDemoCramMD5(interactive);
            case 3 -> new SaslLocalDemoPlain(interactive);
            case 4 -> new SaslLocalDemoDIDChallenge(interactive);
            default -> { System.out.println("Invalid choice: " + lineSaslMechanism); System.exit(1); yield null; }
        };
        saslLocalDemo.run();
    }
}