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

var BaseApi = function (_AbstractApi) {
    (0, _inherits3["default"])(BaseApi, _AbstractApi);

    function BaseApi() {
        (0, _classCallCheck3["default"])(this, BaseApi);

        var _this = (0, _possibleConstructorReturn3["default"])(this, (BaseApi.__proto__ || Object.getPrototypeOf(BaseApi)).apply(this, arguments));




        this.create = function(testForm){
            var _path = null;
            return (0, _get3["default"])(BaseApi.prototype.__proto__ || Object.getPrototypeOf(BaseApi.prototype), "_request", _this).call(_this, "baseApi", "POST", "base", _path, testForm);
        };



        this.get = function(id){
            var _path = {};
            _path["id"] = id;
            return (0, _get3["default"])(BaseApi.prototype.__proto__ || Object.getPrototypeOf(BaseApi.prototype), "_request", _this).call(_this, "baseApi", "POST", "base/{id}", _path, null);
        };



        this.create = function(user){
            var _path = null;
            return (0, _get3["default"])(BaseApi.prototype.__proto__ || Object.getPrototypeOf(BaseApi.prototype), "_request", _this).call(_this, "baseApi", "GET", "baseUrl/", _path, user);
        };

    }

    return BaseApi;
}(_AbstractApi3["default"]);

exports.BaseApi = BaseApi;

var baseApi = new BaseApi();
baseApi._init(_HttpGroupImpi2["default"]);
exports["default"] = baseApi;
