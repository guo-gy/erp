// src/models/User.js
export default class User {
    constructor(username = '', userId = null, userPermissions = [], companyName = '') {
        this.username = username;
        this.userId = userId;
        this.userPermissions = userPermissions;
        this.companyName = companyName;
    }
}