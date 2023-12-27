import org.jenkinsci.plugins.pipeline.modeldefinition.Utils

/*
 * jenkins pipeline when 조건문 처리
 *
 * @param condition (비교문: true or false)
 * @author bruce_oh
 * @date 2020. 4. 7.
 */

def call(boolean condition, body) {
    def config = [:]
    body.resolveStrategy = Closure.OWNER_FIRST
    body.delegate = config

    if (condition) {
        body()
    } else {
        Utils.markStageSkippedForConditional(STAGE_NAME)
    }
}