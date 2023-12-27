/*
 * 서버 정보로 부터 remote 정보 생성
 *
 * @param serverInfo (서버 정보 : xxx.xxx.xxx.xxx(hostname) 형태)
 * @param serverUser (서버의 사용자명)
 * @param serverPasswd (서버의 사용자 비밀번호)
 * @author bruce_oh
 * @date 2020. 4. 9.
 */

def call(String serverInfo, String serverUser, String serverPasswd) {
    getRemoteFromServerInfo(serverInfo, serverUser, serverPasswd)
}

def getRemoteFromServerInfo(serverInfo, serverUser, serverPasswd) {
    def hostIp
    def hostname

    if (serverInfo.indexOf('(') > -1 && serverInfo.lastIndexOf(')') > serverInfo.indexOf('(') && serverInfo.lastIndexOf(')') <= serverInfo.size()) {
        hostIp = serverInfo.substring(0, serverInfo.indexOf('(')).trim()
        hostname = serverInfo.substring(serverInfo.indexOf('(') + 1, serverInfo.lastIndexOf(')')).trim()
    } else {
        hostIp = serverInfo.trim()
        hostname = 'server-name'
    }

    def remote = [: ]
    remote.name = hostname
    remote.host = hostIp
    remote.user = serverUser
    remote.password = serverPasswd
    remote.allowAnyHosts = true

    return remote
}