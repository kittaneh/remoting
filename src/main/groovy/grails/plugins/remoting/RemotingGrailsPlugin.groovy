

package grails.plugins.remoting

import grails.plugins.*


class RemotingGrailsPlugin extends Plugin {


    def grailsVersion = "3.1 > *"
    def author = 'Yahya Dawoud'
    def authorEmail = 'yahya.dawoud@jsaproject.org'
    def title = 'Adds easy-to-use server-side and client-side RPC support.'
    def description = '''\
This plugin makes it easy to expose your Grails services to remote
clients via RMI, Hessian, Burlap and Spring's HttpInvoker protocol.
In addition, you can easily access remote services via the same set
of protocols.
'''
    def documentation = ''

    def artefacts = [grails.plugins.remoting.InterceptorArtefactHandler]

    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def observe = ['services']
    def loadAfter = ['services']
    def watchedResources = "file:./grails-app/remoting/*RemotingInterceptor.groovy"

    def profiles = ['web']

    // Extra (optional) plugin metadata


    // Details of company behind the plugin (if there is one)
    def organization = [  ]

    // Any additional developers beyond the author specified above.
    def developers = [ [ name: "yahya Dawoud",
                         email: "yahya.dawoud@jsaproject.org" ]
    ]

    // Location of the plugin's issue tracker.
    //def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
    //    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]


    Closure doWithSpring() {
        { ->
            def helper = new RemotingPluginHelper()
            helper.registerBeans.delegate = delegate
            helper.registerBeans application

        }
    }

    void doWithDynamicMethods() {

    }

    void doWithApplicationContext() {

    }

    void onChange(Map<String, Object> event) {

    }

    void onConfigChange(Map<String, Object> event) {
    }

    void onShutdown(Map<String, Object> event) {
    }

}
