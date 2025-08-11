package demo.sasl.local;

import demo.sasl.client.DIDChallengeSaslClientDemo;
import demo.sasl.client.debug.SaslClientDebug;
import demo.sasl.client.integration.UserIntegrationWithDID;
import demo.sasl.server.DIDChallengeSaslServerDemo;
import demo.sasl.server.debug.SaslServerDebug;
import demo.sasl.server.integration.BackendIntegrationSimple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sasl.mechanism.did.DIDChallengeSaslProvider;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import java.security.Security;

public class DIDChallengeSaslLocalTest {

    private static final Logger log = LogManager.getLogger(DIDChallengeSaslLocalTest.class);

    static {
        Security.addProvider(new DIDChallengeSaslProvider());
        SaslServerDebug.logSaslServerFactoriesAndMechanisms();
        SaslClientDebug.logSaslClientFactoriesAndMechanisms();
    }

    public static void main(String[] args) throws SaslException {

        SaslServer saslServer = new DIDChallengeSaslServerDemo().createSaslServer(new BackendIntegrationSimple());
        SaslClient saslClient = new DIDChallengeSaslClientDemo().createSaslClient(new UserIntegrationWithDID());

        int i = 0;
        byte[] challenge = new byte[0];
        byte[] response = new byte[0];

        while (! saslServer.isComplete() || ! saslClient.isComplete()) {
            i++;
            if (! saslServer.isComplete()) {
                challenge = saslServer.evaluateResponse(response);
                log.info("SERVER complete: {}", saslServer.isComplete());
                log.debug("SERVER challenge {}: {}", i, challenge);
            }
            if (! saslClient.isComplete()) {
                response = saslClient.evaluateChallenge(challenge);
                log.info("CLIENT complete: {}", saslClient.isComplete());
                log.debug("CLIENT response {}: {}", i, response);
            }
        }
        log.info("SERVER authorizationId: {}", saslServer.getAuthorizationID());
    }
}