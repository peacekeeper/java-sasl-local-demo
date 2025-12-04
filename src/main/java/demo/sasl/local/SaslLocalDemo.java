package demo.sasl.local;

import demo.sasl.client.debug.SaslClientDebug;
import demo.sasl.server.debug.SaslServerDebug;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;

public abstract class SaslLocalDemo {

    private static final Logger log = LogManager.getLogger(SaslLocalDemo.class);

    private final boolean interactive;

    static {
        SaslServerDebug.logSaslServerFactoriesAndMechanisms();
        SaslClientDebug.logSaslClientFactoriesAndMechanisms();
    }

    protected abstract SaslServer createSaslServer() throws SaslException;
    protected abstract SaslClient createSaslClient() throws SaslException;

    protected SaslLocalDemo(boolean interactive) {
        this.interactive = interactive;
    }

    protected void run() throws Exception {

        SaslServer saslServer = this.createSaslServer();
        SaslClient saslClient = this.createSaslClient();

        int i = 0;
        byte[] challenge = new byte[0];
        byte[] response = new byte[0];

        while (! saslServer.isComplete() || ! saslClient.isComplete()) {
            i++;
            if (! saslServer.isComplete()) {
                log.info("SERVER /------------------- complete: {}", saslServer.isComplete());
                challenge = saslServer.evaluateResponse(response);
                log.debug("SERVER challenge {}: {}", i, challenge);
                log.info("SERVER \\------------------- complete: {}", saslServer.isComplete());
            }
            if (! saslClient.isComplete()) {
                log.info("CLIENT /-------------------- complete: {}", saslServer.isComplete());
                response = saslClient.evaluateChallenge(challenge);
                log.debug("CLIENT response {}: {}", i, response);
                log.info("CLIENT \\-------------------- complete: {}", saslClient.isComplete());
            }
        }
        log.info("SERVER authorizationId: {}", saslServer.getAuthorizationID());
    }

    public boolean isInteractive() {
        return this.interactive;
    }
}