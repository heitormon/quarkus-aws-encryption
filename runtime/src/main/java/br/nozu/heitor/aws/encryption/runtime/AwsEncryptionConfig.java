package br.nozu.heitor.aws.encryption.runtime;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "quarkus.aws-encryption")
@ConfigRoot(phase = ConfigPhase.RUN_TIME)
public interface AwsEncryptionConfig {

    /**
     * To identify an AWS KMS key, you can use the key ID or the Amazon Resource Name (key ARN)
     * <p></p>
     * Ex: 1234abcd-12ab-34cd-56ef-1234567890ab
     */
    String keyArn();
}
