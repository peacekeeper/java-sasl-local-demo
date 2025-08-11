package demo.sasl.local;

import demo.sasl.client.CramMD5SaslClientDemo;
import demo.sasl.client.debug.SaslClientDebug;
import demo.sasl.client.integration.UserIntegrationWithPassword;
import demo.sasl.server.CramMD5SaslServerDemo;
import demo.sasl.server.debug.SaslServerDebug;
import demo.sasl.server.integration.BackendIntegrationSimple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sasl.mechanism.did.DIDChallengeSaslProvider;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import java.security.Security;

public class CramMD5SaslLocalDemo {

    private static final Logger log = LogManager.getLogger(CramMD5SaslLocalDemo.class);

    static {
        Security.addProvider(new DIDChallengeSaslProvider());
        SaslServerDebug.logSaslServerFactoriesAndMechanisms();
        SaslClientDebug.logSaslClientFactoriesAndMechanisms();
    }

    public static void main(String[] args) throws SaslException {

        SaslServer saslServer = new CramMD5SaslServerDemo().createSaslServer(new BackendIntegrationSimple());
        SaslClient saslClient = new CramMD5SaslClientDemo().createSaslClient(new UserIntegrationWithPassword());

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