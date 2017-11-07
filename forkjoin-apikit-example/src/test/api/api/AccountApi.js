"use strict";

Object.defineProperty(exports, "__esModule", {
    value: true
});
exports.AccountApi = undefined;

var _classCallCheck2 = require("babel-runtime/helpers/classCallCheck");

var _classCallCheck3 = _interopRequireDefault(_classCallCheck2);

var _possibleConstructorReturn2 = require("babel-runtime/helpers/possibleConstructorReturn");

var _possibleConstructorReturn3 = _interopRequireDefault(_possibleConstructorReturn2);

var _inherits2 = require("babel-runtime/helpers/inherits");

var _inherits3 = _interopRequireDefault(_inherits2);

var _get2 = require("babel-runtime/helpers/get");

var _get3 = _interopRequireDefault(_get2);

var _AbstractApi2 = require("./../AbstractApi");

var _AbstractApi3 = _interopRequireDefault(_AbstractApi2);

var _HttpGroupImpi = require("../HttpGroupImpi");

var _HttpGroupImpi2 = _interopRequireDefault(_HttpGroupImpi);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

/**
 * 账户api
 * @author  zuoge85 on 15/6/11.
 */
var AccountApi = function (_AbstractApi) {
    (0, _inherits3["default"])(AccountApi, _AbstractApi);

    function AccountApi() {
        (0, _classCallCheck3["default"])(this, AccountApi);

        /**
         *  返回授权token
         *
         * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/login</b>
         * <ul>
         * <li><b>Model:</b> string</li>
         * </ul>
         * </div>
         * @see string
                */
        var _this = (0, _possibleConstructorReturn3["default"])(this, (AccountApi.__proto__ || Object.getPrototypeOf(AccountApi)).apply(this, arguments));

        _this.login = function () {
            var _path = null;
            return (0, _get3["default"])(AccountApi.prototype.__proto__ || Object.getPrototypeOf(AccountApi.prototype), "_request", _this).call(_this, "accountApi", "POST", "account/login", _path, null);
        };
        /**
         * 测试需要登录5
         *
         * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/testLogin</b>
         * <ul>
         * <li><b>Model:</b> void</li>
         * <li>需要登录</li>
         * </ul>
         * </div>
                */
        _this.testLogin = function () {
            var _path = null;
            return (0, _get3["default"])(AccountApi.prototype.__proto__ || Object.getPrototypeOf(AccountApi.prototype), "_request", _this).call(_this, "accountApi", "GET", "account/testLogin", _path, null);
        };
        /**
         * 测试不需要登录
         *
         * <div class='http-info'>http 说明：<b>Api Url:</b> <b>baseUrl/testNotLogin</b>
         * <ul>
         * <li><b>Model:</b> User</li>
         * </ul>
         * </div>
         * @see User
                */
        _this.testNotLogin = function () {
            var _path = null;
            return (0, _get3["default"])(AccountApi.prototype.__proto__ || Object.getPrototypeOf(AccountApi.prototype), "_request", _this).call(_this, "accountApi", "GET", "baseUrl/testNotLogin", _path, null);
        };
        return _this;
    }

    return AccountApi;
}(_AbstractApi3["default"]);

exports.AccountApi = AccountApi;

var accountApi = new AccountApi();
accountApi._init(_HttpGroupImpi2["default"]);
exports["default"] = accountApi;