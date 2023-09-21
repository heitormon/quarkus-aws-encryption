package br.nozu.heitor.aws.encryption.deployment;

import br.nozu.heitor.aws.encryption.runtime.AwsEncryptionClientProducer;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.JniRuntimeAccessBuildItem;
import io.quarkus.deployment.builditem.nativeimage.JniRuntimeAccessFieldBuildItem;
import io.quarkus.deployment.builditem.nativeimage.JniRuntimeAccessMethodBuildItem;
import io.quarkus.deployment.pkg.steps.NativeOrNativeSourcesBuild;

class AwsEncryptionProcessor {

    private static final String FEATURE = "aws-encryption";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    AdditionalBeanBuildItem producer() {
        return new AdditionalBeanBuildItem(AwsEncryptionClientProducer.class);
    }

    @BuildStep(onlyIf = NativeOrNativeSourcesBuild.class)
    public void registerAwsCrtJniRuntimeAccessBuildItem(BuildProducer<JniRuntimeAccessBuildItem> jniRuntimeAccess,
                                                        BuildProducer<JniRuntimeAccessMethodBuildItem> jniRuntimeAccessMethod,
                                                        BuildProducer<JniRuntimeAccessFieldBuildItem> jniRuntimeAccessFiel) {
        jniRuntimeAccessMethod.produce(new JniRuntimeAccessMethodBuildItem("com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider$Builder",
                "buildStrict", "java" + ".lang.String[]"));
        jniRuntimeAccessMethod
                .produce(new JniRuntimeAccessMethodBuildItem("java.lang.String", "lastIndexOf", "int"));
    }
}
