/*
 * jenkins stage group을 초기화
 *
 * @param args (Map)
 *      필수 매개변수 : serverGroupList (서버 그룹 목록(Map) : key-그룹이름, value-그룹의서버목록)
 *                  ,serverList (서버 목록(List) : 서버 그룹이 아닐 경우 일괄 처리 목적)
 *      선택 매개변수 : stageArgs (각 스테이지에서 필요한 매개변수(Map))
 * @param cl (Closure: Function doSequentialStageGroup (실행할 각 스테이지 선언한 function))
 * @author bruce_oh
 * @date 2020. 4. 9.
 */

def call(Map args, Closure cl) {
    groupStage(args, cl)
}

void groupStage(Map args, Closure doSequentialStageGroup) {
    // ----------------------------------------------------------------
    // 필수 : serverGroupList, serverList
    // 선택 : stageArgs (각 스테이지에서 필요한 매개변수)
    // ----------------------------------------------------------------
    def serverGroupList = args.serverGroupList?:[:]
    def serverList = args.serverList?:[]
    def stageArgs = args.stageArgs?:[:]

    if (serverGroupList.size() > 0) {

        def groupProcess = [:]
        def groupProcessSkip = [:]

        def selectGroupNames = serverGroupList.keySet() as List
        for (int i = 0; i < selectGroupNames.size() ; i++) {
            
            def groupName = selectGroupNames[i]
            println "groupName ------------------- $groupName"
            groupProcessSkip[groupName] = false
            groupProcess[groupName] = {
                groupProcessSkip[groupName] = doSequentialStageGroup(
                    serverList: serverGroupList["$groupName"], 
                    groupName: "$groupName",
                    stageArgs: stageArgs
                )   
            }
        }

        // Execute stage (parallel)
        parallel groupProcess
        
        def allSkip = true
        for(String groupName in groupProcessSkip.keySet()) {
            println "$groupName ::::: " + (groupProcessSkip[groupName] ? "skip": "proceed")
            allSkip = allSkip && groupProcessSkip[groupName]
        }
        if (allSkip) {
            abortJob()
        }

    } else {
        doSequentialStageGroup(serverList: serverList, stageArgs: stageArgs)
    }
}