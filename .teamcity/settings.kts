import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2020.2"

project {
    buildType(Step1_ID)
    buildType(Step2_ID)
	sequential{
	   buildType(Step1_ID)
       buildType(Step2_ID) 
	   /*  parallel{
			buildType(Step2_ID)
			buildType(Step3_ID)
	   }
	   buildType(Step4_ID)*/
	}
}

object Step1_ID : BuildType({
    name = "SampleBuild_Step1"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        powerShell {
            name = "Run payload"
            scriptMode = script {
                content = """Write-Host "Payload info:" %env.TEAMCITY_PROJECT_NAME%.%system.teamcity.buildType.id% %env.BUILD_NUMBER%"""
            }
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        pullRequests {
            vcsRootExtId = "SampleProjectName_VCSRootID"
            provider = github {
                authType = token {
                    token = "credentialsJSON:7a955ca5-1d1b-432f-9df4-e43064c895fb"
                }
                filterAuthorRole = PullRequests.GitHubRoleFilter.EVERYBODY
            }
        }
    }
})

object Step2_ID : BuildType({
    name = "SampleBuild_Step2"

    steps {
        powerShell {
            name = "Run payload"
            scriptMode = script {
                content = """Write-Host "Payload info:" %env.TEAMCITY_PROJECT_NAME%.%system.teamcity.buildType.id% %env.BUILD_NUMBER%"""
            }
        }
    }

})

object Step3_ID : BuildType({
    name = "SampleBuild_Step3"

    steps {
        powerShell {
            name = "Run payload"
            scriptMode = script {
                content = """Write-Host "Payload info:" %env.TEAMCITY_PROJECT_NAME%.%system.teamcity.buildType.id% %env.BUILD_NUMBER%"""
            }
        }
    }

})
object Step4_ID : BuildType({
    name = "SampleBuild_Step4"

    steps {
        powerShell {
            name = "Run payload"
            scriptMode = script {
                content = """Write-Host "Payload info:" %env.TEAMCITY_PROJECT_NAME%.%system.teamcity.buildType.id% %env.BUILD_NUMBER%"""
            }
        }
    }

})