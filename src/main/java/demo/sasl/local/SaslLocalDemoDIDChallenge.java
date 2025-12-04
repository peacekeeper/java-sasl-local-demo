package demo.sasl.local;

import demo.sasl.client.SaslClientDemoDIDChallenge;
import demo.sasl.client.integration.UserIntegrationDemoDID;
import demo.sasl.server.SaslServerDemoDIDChallenge;
import demo.sasl.server.integration.BackendIntegrationDemoDID;
import sasl.mechanism.did.DIDChallengeSaslProvider;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import java.security.Security;

public class SaslLocalDemoDIDChallenge extends SaslLocalDemo{

    static {
        Security.addProvider(new DIDChallengeSaslProvider());
    }

    @Override
    protected SaslServer createSaslServer() throws SaslException {
        return new SaslServerDemoDIDChallenge().createSaslServer(new BackendIntegrationDemoDID());
    }

    @Override
    protected SaslClient createSaslClient() throws SaslException {
        return new SaslClientDemoDIDChallenge().createSaslClient(new UserIntegrationDemoDID());
    }

    public static void main(String[] args) throws SaslException {
        new SaslLocalDemoDIDChallenge().run();
    }
}