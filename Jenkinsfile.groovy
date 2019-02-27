def BUILD_VERSION = "1.${TIMESTAMP}"

try {
    node {
        timestamps {
			echo "TimeStamp: ${currentBuild.startTimeInMillis}"
		}
	}
}
