/*
 * 기본 문자열
 *
 * @author bruce_oh
 * @date 2020. 4. 9.
 */

def call(String str='', String defaultStr='') {
    return str?:defaultStr
}