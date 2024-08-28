# java-sasl-local-demo

This repository is one component of the project "Securing Internet protocols with DIDs, using SASL",
see https://github.com/peacekeeper/did-based-sasl for an overview and list of all components.

## Description

This is an executable SASL "Hello World" demonstration. It simulates a SASL client-side component and a server-side
component.

Two versions of the "Hello World" demonstration are provided, using two SASL authentication mechanisms:

- The more traditional `CRAM-MD5` mechanism, specified by [RFC2195](https://www.rfc-editor.org/rfc/rfc2195.html).
- The DID-based `DID-CHALLENGE` mechanism, described and implemented in https://github.com/peacekeeper/java-sasl-did-mechanism.

### Demonstration using the `CRAM-MD5` mechanism

A typical interaction using this mechanism looks like this:

```
INFO  [main] d.s.s.SimpleSaslServerDemo: SASL server created: com.sun.security.sasl.CramMD5Server@7c24b813
DEBUG [main] d.s.c.SimpleSaslClientCallbackHandler: Callback: javax.security.auth.callback.NameCallback@74751b3
INFO  [main] d.s.c.SimpleSaslClientCallbackHandler: prompt: CRAM-MD5 authentication id: , name: null, defaultName: null
DEBUG [main] d.s.c.SimpleSaslClientCallbackHandler: Callback: javax.security.auth.callback.PasswordCallback@741a8937
INFO  [main] d.s.c.SimpleSaslClientCallbackHandler: prompt: CRAM-MD5 password: , password: null
INFO  [main] d.s.c.SimpleSaslClientDemo: SASL client created: com.sun.security.sasl.CramMD5Client@4f2b503c
INFO  [main] d.s.l.SaslLocalTest: server complete: false
DEBUG [main] d.s.l.SaslLocalTest: server challenge 1: [60, 55, 53, 55, 52, 53, 54, 49, 51, 56, 54, 54, 51, 48, 52, 52, 52, 48, 54, 46, 49, 55, 50, 52, 56, 54, 56, 55, 55, 50, 49, 48, 52, 64, 108, 111, 99, 97, 108, 104, 111, 115, 116, 62]
INFO  [main] d.s.l.SaslLocalTest: client complete: true
DEBUG [main] d.s.l.SaslLocalTest: client response 1: [117, 115, 101, 114, 110, 97, 109, 101, 32, 97, 56, 53, 49, 97, 51, 50, 54, 54, 97, 55, 100, 97, 102, 49, 99, 99, 56, 99, 52, 52, 56, 48, 57, 102, 57, 98, 99, 51, 97, 101, 100]
DEBUG [main] d.s.s.SimpleSaslServerCallbackHandler: Callback: javax.security.auth.callback.NameCallback@e15b7e8
INFO  [main] d.s.s.SimpleSaslServerCallbackHandler: prompt: CRAM-MD5 authentication ID: , name: null, defaultName: username
DEBUG [main] d.s.s.SimpleSaslServerCallbackHandler: Callback: javax.security.auth.callback.PasswordCallback@1b2abca6
INFO  [main] d.s.s.SimpleSaslServerCallbackHandler: prompt: CRAM-MD5 password: , password: null
DEBUG [main] d.s.s.SimpleSaslServerCallbackHandler: Callback: javax.security.sasl.AuthorizeCallback@2ed2d9cb
INFO  [main] d.s.s.SimpleSaslServerCallbackHandler: authenticationID: username, authorizationID: username, authorizedID: null, isAuthorized: false
INFO  [main] d.s.l.SaslLocalTest: server complete: true
DEBUG [main] d.s.l.SaslLocalTest: server challenge 2: null
INFO  [main] d.s.l.SaslLocalTest: server authorizationId: username
```

### Demonstration using the `DID-CHALLENGE` mechanism

A typical interaction using this mechanism looks like this:

```
INFO  [main] d.s.s.d.DidSaslServerDemo: SASL server created: sasl.did.mechanism.server.DidSaslServer@5d7148e2
DEBUG [main] d.s.c.d.DidSaslClientCallbackHandler: Callback: javax.security.auth.callback.NameCallback@5ba3f27a
INFO  [main] d.s.c.d.DidSaslClientCallbackHandler: prompt: DID: , name: null, defaultName: null
DEBUG [main] d.s.c.d.DidSaslClientCallbackHandler: Callback: javax.security.auth.callback.TextInputCallback@58d75e99
INFO  [main] d.s.c.d.DidSaslClientCallbackHandler: prompt: Private key: , text: null, defaultText: (base58 encoded)
INFO  [main] d.s.c.d.DidSaslClientDemo: SASL client created: sasl.did.mechanism.client.DidSaslClient@2ed2d9cb
DEBUG [main] s.d.m.s.DidSaslServer: Generated challenge: <1809528678543235072.1724868615672@localhost>
INFO  [main] d.s.l.DidSaslLocalTest: server complete: false
DEBUG [main] d.s.l.DidSaslLocalTest: server challenge 1: [60, 49, 56, 48, 57, 53, 50, 56, 54, 55, 56, 53, 52, 51, 50, 51, 53, 48, 55, 50, 46, 49, 55, 50, 52, 56, 54, 56, 54, 49, 53, 54, 55, 50, 64, 108, 111, 99, 97, 108, 104, 111, 115, 116, 62]
DEBUG [main] s.d.m.c.DidSaslClient: Received challenge: <1809528678543235072.1724868615672@localhost>
DEBUG [main] s.d.m.c.DidSaslClient: Sending response: did:key:z6MkeretqUG21CE9bwUNE6vfpnxRZxALZP2qfsApZDdbjcAC 2mJ4tBo6HBg7p3kFgV9MNECcUCXrRCmcJcHJYo6xEDUZSBmf9VqFQgzCraQ4eHyPka6Hh13Yt4ng5udacPz3M9mB
INFO  [main] d.s.l.DidSaslLocalTest: client complete: true
DEBUG [main] d.s.l.DidSaslLocalTest: client response 1: [100, 105, 100, 58, 107, 101, 121, 58, 122, 54, 77, 107, 101, 114, 101, 116, 113, 85, 71, 50, 49, 67, 69, 57, 98, 119, 85, 78, 69, 54, 118, 102, 112, 110, 120, 82, 90, 120, 65, 76, 90, 80, 50, 113, 102, 115, 65, 112, 90, 68, 100, 98, 106, 99, 65, 67, 32, 50, 109, 74, 52, 116, 66, 111, 54, 72, 66, 103, 55, 112, 51, 107, 70, 103, 86, 57, 77, 78, 69, 67, 99, 85, 67, 88, 114, 82, 67, 109, 99, 74, 99, 72, 74, 89, 111, 54, 120, 69, 68, 85, 90, 83, 66, 109, 102, 57, 86, 113, 70, 81, 103, 122, 67, 114, 97, 81, 52, 101, 72, 121, 80, 107, 97, 54, 72, 104, 49, 51, 89, 116, 52, 110, 103, 53, 117, 100, 97, 99, 80, 122, 51, 77, 57, 109, 66]
DEBUG [main] s.d.m.s.DidSaslServer: Received response: did:key:z6MkeretqUG21CE9bwUNE6vfpnxRZxALZP2qfsApZDdbjcAC 2mJ4tBo6HBg7p3kFgV9MNECcUCXrRCmcJcHJYo6xEDUZSBmf9VqFQgzCraQ4eHyPka6Hh13Yt4ng5udacPz3M9mB
DEBUG [main] s.d.m.s.DidSaslServer: Extracted DID: did:key:z6MkeretqUG21CE9bwUNE6vfpnxRZxALZP2qfsApZDdbjcAC
DEBUG [main] d.s.s.d.DidSaslServerCallbackHandler: Callback: javax.security.auth.callback.NameCallback@52719fb6
INFO  [main] d.s.s.d.DidSaslServerCallbackHandler: prompt: SASL authentication ID: , name: null, defaultName: did:key:z6MkeretqUG21CE9bwUNE6vfpnxRZxALZP2qfsApZDdbjcAC
DEBUG [main] d.s.s.d.DidSaslServerCallbackHandler: Callback: javax.security.sasl.AuthorizeCallback@3b0f7d9d
INFO  [main] d.s.s.d.DidSaslServerCallbackHandler: authenticationID: did:key:z6MkeretqUG21CE9bwUNE6vfpnxRZxALZP2qfsApZDdbjcAC, authorizationID: did:key:z6MkeretqUG21CE9bwUNE6vfpnxRZxALZP2qfsApZDdbjcAC, authorizedID: null, isAuthorized: false
DEBUG [main] s.d.m.s.DidSaslServer: authorizationId: did:key:z6MkeretqUG21CE9bwUNE6vfpnxRZxALZP2qfsApZDdbjcAC
INFO  [main] d.s.l.DidSaslLocalTest: server complete: true
DEBUG [main] d.s.l.DidSaslLocalTest: server challenge 2: null
INFO  [main] d.s.l.DidSaslLocalTest: server authorizationId: did:key:z6MkeretqUG21CE9bwUNE6vfpnxRZxALZP2qfsApZDdbjcAC
```

## About

Markus Sabadello - https://github.com/peacekeeper/

<img align="left" height="40" src="https://github.com/peacekeeper/did-based-sasl/blob/main/docs/logo-ngi-assure.png?raw=true">

This project has received financial support from NLnet and the NGI Assure fund. NGI Assure was established with
financial support from the European Commission's Next Generation Internet programme, under the aegis of DG
Communications Networks, Content and Technology.
