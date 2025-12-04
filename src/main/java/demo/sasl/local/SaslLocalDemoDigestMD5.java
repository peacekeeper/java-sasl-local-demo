package demo.sasl.local;

import demo.sasl.client.SaslClientDemoDigestMD5;
import demo.sasl.client.integration.UserIntegrationDemoUsername;
import demo.sasl.client.integration.UserIntegrationInteractive;
import demo.sasl.server.SaslServerDemoDigestMD5;
import demo.sasl.server.integration.BackendIntegrationDemoUsername;
import demo.sasl.server.integration.BackendIntegrationInteractive;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;

public class SaslLocalDemoDigestMD5 extends SaslLocalDemo {

    protected SaslLocalDemoDigestMD5(boolean interactive) {
        super(interactive);
    }

    @Override
    protected SaslServer createSaslServer() throws SaslException {
        return new SaslServerDemoDigestMD5().createSaslServer(this.isInteractive() ? new BackendIntegrationInteractive() : new BackendIntegrationDemoUsername());
    }

    @Override
    protected SaslClient createSaslClient() throws SaslException {
        return new SaslClientDemoDigestMD5().createSaslClient(this.isInteractive() ? new UserIntegrationInteractive() : new UserIntegrationDemoUsername());
    }

    public static void main(String[] args) throws SaslException {
        new SaslLocalDemoDigestMD5(false).run();
    }
}