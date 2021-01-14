package org.testcontainers.images.builder.dockerfile.traits;

import org.testcontainers.images.builder.dockerfile.statement.SingleArgumentStatement;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.ImageNameSubstitutor;

public interface FromStatementTrait<SELF extends FromStatementTrait<SELF> & DockerfileBuilderTrait<SELF>> {

    default SELF from(String dockerImageName) {
        DockerImageName parsedImageName = DockerImageName.parse(dockerImageName);
        parsedImageName.assertValid();

        String imageNameWithRegistry = ImageNameSubstitutor.instance()
            .apply(parsedImageName)
            .asCanonicalNameString();

        return ((SELF) this).withStatement(new SingleArgumentStatement("FROM", imageNameWithRegistry));
    }
}
