package br.nozu.heitor.aws.encryption.runtime;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import com.amazonaws.encryptionsdk.AwsCrypto;
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider;

import io.quarkus.arc.DefaultBean;

@ApplicationScoped
public class AwsEncryptionClientProducer {
    private final AwsCrypto awsCrypto;

    @Inject
    AwsEncryptionConfig awsEncryptionConfig;

    AwsEncryptionClientProducer(Instance<AwsCrypto.Builder> awsCrBuilderInstance, Instance<KmsMasterKeyProvider.Builder> keyProviderInstance) {
        this.awsCrypto = awsCrBuilderInstance.isResolvable() ? awsCrBuilderInstance.get().build() : null;
        keyProviderInstance.get().buildStrict(awsEncryptionConfig.keyArn());
    }

    @DefaultBean
    @Produces
    @ApplicationScoped
    public AwsCrypto awsCrypto() {
        if (awsCrypto == null) {
            throw new IllegalStateException("The AwsCryptoClient is required but has not been detected/configured.");
        }
        return awsCrypto;
    }
}
