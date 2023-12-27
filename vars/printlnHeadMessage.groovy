/*
 * 로그에 각 섹션의 시작점을 출력하기 위한 message 출력
 *
 * @param msg (출력 메시지)
 * @author bruce_oh
 * @date 2020. 4. 9.
 */

def call(msg) {
    printlnHeadMessage(msg)
}

void printlnHeadMessage(msg) {
    def headLine = '◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼◼︎◼︎◼︎◼︎◼︎◼︎◼◼◼︎◼︎◼︎◼︎◼︎◼︎◼◼'
    println headLine + "\n" + "_______________      " + msg + "\n" + headLine
}