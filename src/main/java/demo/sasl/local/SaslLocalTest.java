package demo.sasl.local;

import demo.sasl.client.SaslClientTest;
import demo.sasl.server.SaslServerTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;

public class SaslLocalTest {

    private static final Logger log = LogManager.getLogger(SaslLocalTest.class);

    public static void main(String[] args) throws SaslException {
        //SaslServerTest.logSaslServerFactoriesAndMechanisms();
        //SaslClientTest.logSaslClientFactoriesAndMechanisms();

        SaslServer saslServer = SaslServerTest.createSaslServer();
        SaslClient saslClient = SaslClientTest.createSaslClient();

        int i = 0;
        byte[] challenge = new byte[0];
        byte[] response = new byte[0];

        while (! saslServer.isComplete() || ! saslClient.isComplete()) {
            i++;
            if (! saslServer.isComplete()) {
                challenge = saslServer.evaluateResponse(response);
                log.info("server complete: {}", saslServer.isComplete());
                log.debug("server challenge {}: {}", i, challenge);
            }
            if (! saslClient.isComplete()) {
                response = saslClient.evaluateChallenge(challenge);
                log.info("client complete: {}", saslClient.isComplete());
                log.debug("client response {}: {}", i, response);
            }
        }
    }
}