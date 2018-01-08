"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.AccountApi = undefined;

var _getPrototypeOf = require("babel-runtime/core-js/object/get-prototype-of");

var _getPrototypeOf2 = _interopRequireDefault(_getPrototypeOf);

var _classCallCheck2 = require("babel-runtime/helpers/classCallCheck");

var _classCallCheck3 = _interopRequireDefault(_classCallCheck2);

var _createClass2 = require("babel-runtime/helpers/createClass");

var _createClass3 = _interopRequireDefault(_createClass2);

var _possibleConstructorReturn2 = require("babel-runtime/helpers/possibleConstructorReturn");

var _possibleConstructorReturn3 = _interopRequireDefault(_possibleConstructorReturn2);

var _get2 = require("babel-runtime/helpers/get");

var _get3 = _interopRequireDefault(_get2);

var _inherits2 = require("babel-runtime/helpers/inherits");

var _inherits3 = _interopRequireDefault(_inherits2);

var _AbstractApi2 = require("./../AbstractApi");

var _AbstractApi3 = _interopRequireDefault(_AbstractApi2);

var _RequestGroupImpi = require("../RequestGroupImpi");

var _RequestGroupImpi2 = _interopRequireDefault(_RequestGroupImpi);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/**
 * 账户api
 * @author  zuoge85 on 15/6/11.
 */
var AccountApi = function (_AbstractApi) {
  (0, _inherits3.default)(AccountApi, _AbstractApi);

  function AccountApi() {
    (0, _classCallCheck3.default)(this, AccountApi);
    return (0, _possibleConstructorReturn3.default)(this, (AccountApi.__proto__ || (0, _getPrototypeOf2.default)(AccountApi)).apply(this, arguments));
  }

  (0, _createClass3.default)(AccountApi, [{
    key: "login",

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
    value: function login() {
      var _path = null;
      return (0, _get3.default)(AccountApi.prototype.__proto__ || (0, _getPrototypeOf2.default)(AccountApi.prototype), "_request", this).call(this, "accountApi", "POST", "account/login", _path, null);
    }
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

  }, {
    key: "testLogin",
    value: function testLogin() {
      var _path = null;
      return (0, _get3.default)(AccountApi.prototype.__proto__ || (0, _getPrototypeOf2.default)(AccountApi.prototype), "_request", this).call(this, "accountApi", "GET", "account/testLogin", _path, null);
    }
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

  }, {
    key: "testNotLogin",
    value: function testNotLogin() {
      var _path = null;
      return (0, _get3.default)(AccountApi.prototype.__proto__ || (0, _getPrototypeOf2.default)(AccountApi.prototype), "_request", this).call(this, "accountApi", "GET", "baseUrl/testNotLogin", _path, null);
    }
  }]);
  return AccountApi;
}(_AbstractApi3.default);

exports.AccountApi = AccountApi;

var accountApi = new AccountApi();
accountApi._init(_RequestGroupImpi2.default);
exports.default = accountApi;