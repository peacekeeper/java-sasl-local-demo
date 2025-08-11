package demo.sasl.local;

import demo.sasl.client.SaslClientDemoDIDChallenge;
import demo.sasl.client.integration.UserIntegrationWithDID;
import demo.sasl.client.integration.UserIntegrationWithPassword;
import demo.sasl.server.SaslServerDemoDIDChallenge;
import demo.sasl.server.integration.BackendIntegrationSimple;
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
        return new SaslServerDemoDIDChallenge().createSaslServer(new BackendIntegrationSimple());
    }

    @Override
    protected SaslClient createSaslClient() throws SaslException {
        return new SaslClientDemoDIDChallenge().createSaslClient(new UserIntegrationWithDID());
    }

    public static void main(String[] args) throws SaslException {
        new SaslLocalDemoDIDChallenge().run();
    }
}