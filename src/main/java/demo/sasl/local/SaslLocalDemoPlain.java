package demo.sasl.local;

import demo.sasl.client.SaslClientDemoPlain;
import demo.sasl.client.integration.UserIntegrationWithPassword;
import demo.sasl.server.SaslServerDemoPlain;
import demo.sasl.server.integration.BackendIntegrationWithPassword;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;

public class SaslLocalDemoPlain extends SaslLocalDemo {

    @Override
    protected SaslServer createSaslServer() throws SaslException {
        return new SaslServerDemoPlain().createSaslServer(new BackendIntegrationWithPassword());
    }

    @Override
    protected SaslClient createSaslClient() throws SaslException {
        return new SaslClientDemoPlain().createSaslClient(new UserIntegrationWithPassword());
    }

    public static void main(String[] args) throws SaslException {
        new SaslLocalDemoPlain().run();
    }
}