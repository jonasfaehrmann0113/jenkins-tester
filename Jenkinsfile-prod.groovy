def NAME = "dev-jfaehrma-test-prod"

try {
    node {
        timestamps {
			echo "TimeStamp: ${BUILD_VERSION}"
		}
	}
} catch (error) {
 echo error
}
