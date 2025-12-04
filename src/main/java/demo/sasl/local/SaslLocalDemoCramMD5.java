package demo.sasl.local;

import demo.sasl.client.SaslClientDemoCramMD5;
import demo.sasl.client.integration.UserIntegrationDemoUsername;
import demo.sasl.server.SaslServerDemoCramMD5;
import demo.sasl.server.integration.BackendIntegrationDemoUsername;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;

public class SaslLocalDemoCramMD5 extends SaslLocalDemo {

    @Override
    protected SaslServer createSaslServer() throws SaslException {
        return new SaslServerDemoCramMD5().createSaslServer(new BackendIntegrationDemoUsername());
    }

    @Override
    protected SaslClient createSaslClient() throws SaslException {
        return new SaslClientDemoCramMD5().createSaslClient(new UserIntegrationDemoUsername());
    }

    public static void main(String[] args) throws SaslException {
        new SaslLocalDemoCramMD5().run();
    }
}