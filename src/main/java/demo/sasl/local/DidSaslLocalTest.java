package demo.sasl.local;

import demo.sasl.client.debug.SaslClientDebug;
import demo.sasl.client.did.DidSaslClientDemo;
import demo.sasl.server.debug.SaslServerDebug;
import demo.sasl.server.did.DidSaslServerDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sasl.did.mechanism.DidSaslProvider;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import java.security.Security;

public class DidSaslLocalTest {

    private static final Logger log = LogManager.getLogger(DidSaslLocalTest.class);

    static {
        Security.addProvider(new DidSaslProvider());
        SaslServerDebug.logSaslServerFactoriesAndMechanisms();
        SaslClientDebug.logSaslClientFactoriesAndMechanisms();
    }

    public static void main(String[] args) throws SaslException {
        SaslServer saslServer = DidSaslServerDemo.createSaslServer();
        SaslClient saslClient = DidSaslClientDemo.createSaslClient();

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
        log.info("server authorizationId: {}", saslServer.getAuthorizationID());
    }
}