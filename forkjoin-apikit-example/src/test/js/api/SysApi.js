'use strict';

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

var _AbstractApi2 = require("../AbstractApi");
var _AbstractApi3 = _interopRequireDefault(_AbstractApi2);

var _HttpGroupImpi = require("../HttpGroupImpi");
var _HttpGroupImpi2 = _interopRequireDefault(_HttpGroupImpi);


function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var SysApi = function (_AbstractApi) {
    (0, _inherits3["default"])(SysApi, _AbstractApi);

    function SysApi() {
        (0, _classCallCheck3["default"])(this, SysApi);

        var _this = (0, _possibleConstructorReturn3["default"])(this, (SysApi.__proto__ || Object.getPrototypeOf(SysApi)).apply(this, arguments));




        this.status = function(){
            var _path = null;
            return (0, _get3["default"])(SysApi.prototype.__proto__ || Object.getPrototypeOf(SysApi.prototype), "_request", _this).call(_this, "sysApi", "GET", "status", _path, null);
        };



        this.login = function(){
            var _path = null;
            return (0, _get3["default"])(SysApi.prototype.__proto__ || Object.getPrototypeOf(SysApi.prototype), "_request", _this).call(_this, "sysApi", "GET", "now", _path, null);
        };

    }

    return SysApi;
}(_AbstractApi3["default"]);

exports.SysApi = SysApi;

var sysApi = new SysApi();
sysApi._init(_HttpGroupImpi2["default"]);
exports["default"] = sysApi;
