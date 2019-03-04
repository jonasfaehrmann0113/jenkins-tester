@Library("gatekeeper-library")
import de.otto.plm.Gatekeeper
import hudson.model.ParametersAction
import hudson.model.StringParameterValue

def NAME = 'dev-jfaehrma-test'

try {
    node {
        timestamps {
			setBuildVersion()
			echo "test"
			echo BUILD_VERSION
		}
	}
if (Gatekeeper.isGateOpen()) {
        println "Gate is open. Starting production deployment."
	build job: "${NAME}-prod", parameters: [string(name: "BUILD_VERSION", value: BUILD_VERSION)], wait: false
    } else {
        println "Gate is closed."
}
} catch (error) {
	println error
}

@NonCPS
def setBuildVersion(){
	def buildVersionParam = new StringParameterValue('BUILD_VERSION', "1.${currentBuild.startTimeInMillis}", "The build version.")
	def build = currentBuild.getRawBuild()
	build.actions.add(new ParametersAction(buildVersionParam))
}
