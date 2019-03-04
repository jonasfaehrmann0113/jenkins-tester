import hudson.model.ParametersAction
import hudson.model.ParameterValue
import hudson.model.StringParameterValue

def buildVersionParam = new StringParameterValue('BUILD_VERSION', "${currentBuild.startTimeInMillis}", "The build version.")
def build = currentBuild.getRawBuild();
build.actions.add(new ParametersAction(buildVersionParam))

try {
    node {
        timestamps {
			echo "test"
		}
	}
} catch (error) {
	println error
}
