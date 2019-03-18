@Library("gatekeeper-library")
import de.otto.plm.Gatekeeper
import hudson.model.ParametersAction
import hudson.model.StringParameterValue
import java.text.SimpleDateFormat

def NAME = 'dev-jfaehrma-test'

try {
    node {
        timestamps {
			setBuildVersion()
			echo "test"
			echo BUILD_VERSION
		}
	}
	mail(
       		body: "Hallo,\n\nin der plm-mail-configuration-int Pipeline ist ein Fehler aufgetreten",
       		subject: "Fehler in der plm-mail-configuration-int Pipeline",
       		to: "8ecd9a24.og2gether.onmicrosoft.com@emea.teams.ms"
	)
if (Gatekeeper.isGateOpen()) {
        println "Gate is open. Starting production deployment."
	build job: "${NAME}-prod", parameters: [string(name: "BUILD_VERSION", value: BUILD_VERSION)], wait: false
    } else {
        println "Gate is closed."
}
} catch (error) {
	mail(
       		body: "Hallo,\n\nin der plm-mail-configuration-int Pipeline ist ein Fehler aufgetreten",
       		subject: "Fehler in der plm-mail-configuration-int Pipeline",
       		to: "8ecd9a24.og2gether.onmicrosoft.com@emea.teams.ms"
	)
}

@NonCPS
def setBuildVersion(){
	def buildVersionParam = new StringParameterValue('BUILD_VERSION', "1.${new SimpleDateFormat("yyyyMMddHHmmss").format(currentBuild.startTimeInMillis)}", "The build version.")
	def build = currentBuild.getRawBuild()
	build.actions.add(new ParametersAction(buildVersionParam))
}
