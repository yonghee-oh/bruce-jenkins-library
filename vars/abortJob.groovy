/*
 * jenkins BUILD JOB 중지
 * @author bruce_oh
 * @date 2020. 4. 9.
 */

def call() {
    abortJob()
}

void abortJob() {
    def jobname = "${JOB_NAME}"
    def buildnum = "${BUILD_NUMBER}".toInteger()

    def job = Jenkins.instance.getItemByFullName(jobname)
    for (build in job.builds) {
        if (buildnum == build.getNumber().toInteger()) {
            build.doStop()
        } 
    }
}