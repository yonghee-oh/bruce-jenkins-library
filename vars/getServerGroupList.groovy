/*
 * 환경 변수에 저장된 서버 그룹 목록 조회
 *
 * @param selectGroup (선택한 그룹)
 * @param groupNames (그룹 목록)
 * 선택한 그룹(selectGroup)은 LIST, ALL 외에 사용자가 지정한 그룹
 * @author bruce_oh
 * @date 2020. 4. 9.
 */

def call(String selectGroup, List groupNames) {
    getServerGroupList(selectGroup, groupNames)
}

def getServerGroupList(selectGroup, groupNames) {
    def groupServers = [:]

    withFolderProperties {
        for (String groupName in groupNames) {
            if (selectGroup.toUpperCase().equals("ALL") || selectGroup == groupName) {

                if (env["$groupName"] == null ) {
                    println "Error $groupName"
                    throw new MissingPropertyException("$groupName", getClass())
                }
                def groupServerList = env["$groupName"].tokenize('\\,')
                for (int i = 0; i < groupServerList.size(); i++) {
                    // println "Server $i : $groupServerList[i]"
                    groupServerList[i] = groupServerList[i].trim()
                }
                groupServers["$groupName"] = groupServerList
            }
        }

        // println "groupNames : $groupNames"
        // println "groupServers : $groupServers"
    }

    return groupServers
}