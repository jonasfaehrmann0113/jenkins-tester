import hudson.model.ParametersAction
import hudson.model.ParameterValue
import hudson.model.StringParameterValue

def buildVersionParam = new StringParameterValue('BUILD_VERSION', "${currentBuild.startTimeInMillis}")
def build = currentBuild.getRawBuild();
build.addOrReplaceAction(new ParametersAction(buildVersionParam))

try {
    node {
        timestamps {
			echo "test"
		}
	}
} catch (error) {
 echo error
}
