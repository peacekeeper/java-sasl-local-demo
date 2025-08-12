package demo.sasl.local;

import demo.sasl.client.SaslClientDemoCramMD5;
import demo.sasl.client.integration.UserIntegrationWithPassword;
import demo.sasl.server.SaslServerDemoCramMD5;
import demo.sasl.server.integration.BackendIntegrationWithPassword;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;

public class SaslLocalDemoCramMD5 extends SaslLocalDemo {

    @Override
    protected SaslServer createSaslServer() throws SaslException {
        return new SaslServerDemoCramMD5().createSaslServer(new BackendIntegrationWithPassword());
    }

    @Override
    protected SaslClient createSaslClient() throws SaslException {
        return new SaslClientDemoCramMD5().createSaslClient(new UserIntegrationWithPassword());
    }

    public static void main(String[] args) throws SaslException {
        new SaslLocalDemoCramMD5().run();
    }
}