package demo.sasl.local;

import demo.sasl.client.SaslClientDemoPlain;
import demo.sasl.client.integration.UserIntegrationDemoUsername;
import demo.sasl.server.SaslServerDemoPlain;
import demo.sasl.server.integration.BackendIntegrationDemoUsername;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;

public class SaslLocalDemoPlain extends SaslLocalDemo {

    protected SaslLocalDemoPlain(boolean interactive) {
        super(interactive);
    }

    @Override
    protected SaslServer createSaslServer() throws SaslException {
        return new SaslServerDemoPlain().createSaslServer(new BackendIntegrationDemoUsername());
    }

    @Override
    protected SaslClient createSaslClient() throws SaslException {
        return new SaslClientDemoPlain().createSaslClient(new UserIntegrationDemoUsername());
    }

    public static void main(String[] args) throws SaslException {
        new SaslLocalDemoPlain(false).run();
    }
}