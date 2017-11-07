/**
 * 用户信息
*/
interface User {
    id?: number;
    /**
     * 名称允许重复
     */
    name?: string;
    /**
     * 年龄
     */
    age?: number;
}
export default User;
