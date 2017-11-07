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

var TestApi = function (_AbstractApi) {
    (0, _inherits3["default"])(TestApi, _AbstractApi);

    function TestApi() {
        (0, _classCallCheck3["default"])(this, TestApi);

        var _this = (0, _possibleConstructorReturn3["default"])(this, (TestApi.__proto__ || Object.getPrototypeOf(TestApi)).apply(this, arguments));




        this.testVoid = function(){
            var _path = null;
            return (0, _get3["default"])(TestApi.prototype.__proto__ || Object.getPrototypeOf(TestApi.prototype), "_request", _this).call(_this, "testApi", "POST", "testVoid", _path, null);
        };



        this.testObjectList = function(testForm){
            var _path = null;
            return (0, _get3["default"])(TestApi.prototype.__proto__ || Object.getPrototypeOf(TestApi.prototype), "_request", _this).call(_this, "testApi", "POST", "testObjectList", _path, testForm);
        };



        this.create = function(testForm){
            var _path = null;
            return (0, _get3["default"])(TestApi.prototype.__proto__ || Object.getPrototypeOf(TestApi.prototype), "_request", _this).call(_this, "testApi", "POST", "test", _path, testForm);
        };



        this.get = function(id){
            var _path = {};
            _path["id"] = id;
            return (0, _get3["default"])(TestApi.prototype.__proto__ || Object.getPrototypeOf(TestApi.prototype), "_request", _this).call(_this, "testApi", "GET", "test/{id}", _path, null);
        };



        this.update = function(testForm){
            var _path = null;
            return (0, _get3["default"])(TestApi.prototype.__proto__ || Object.getPrototypeOf(TestApi.prototype), "_request", _this).call(_this, "testApi", "PUT", "test", _path, testForm);
        };



        this.patchUpdate = function(testForm){
            var _path = null;
            return (0, _get3["default"])(TestApi.prototype.__proto__ || Object.getPrototypeOf(TestApi.prototype), "_request", _this).call(_this, "testApi", "PATCH", "test", _path, testForm);
        };



        this.delete = function(id){
            var _path = {};
            _path["id"] = id;
            return (0, _get3["default"])(TestApi.prototype.__proto__ || Object.getPrototypeOf(TestApi.prototype), "_request", _this).call(_this, "testApi", "DELETE", "test/{id}", _path, null);
        };



        this.deletes = function(id){
            var _path = {};
            _path["id"] = id;
            return (0, _get3["default"])(TestApi.prototype.__proto__ || Object.getPrototypeOf(TestApi.prototype), "_request", _this).call(_this, "testApi", "DELETE", "tests/{id}", _path, null);
        };



        this.search = function(id, name){
            var _path = {};
            _path["id"] = id;
            _path["name"] = name;
            return (0, _get3["default"])(TestApi.prototype.__proto__ || Object.getPrototypeOf(TestApi.prototype), "_request", _this).call(_this, "testApi", "GET", "search/{id}/{name}", _path, null);
        };



        this.testString = function(name){
            var _path = {};
            _path["name"] = name;
            return (0, _get3["default"])(TestApi.prototype.__proto__ || Object.getPrototypeOf(TestApi.prototype), "_request", _this).call(_this, "testApi", "GET", "testString/{name}", _path, null);
        };



        this.testString1 = function(name, age){
            var _path = {};
            _path["name"] = name;
            _path["age"] = age;
            return (0, _get3["default"])(TestApi.prototype.__proto__ || Object.getPrototypeOf(TestApi.prototype), "_request", _this).call(_this, "testApi", "GET", "testString1/{name}/{age}", _path, null);
        };

    }

    return TestApi;
}(_AbstractApi3["default"]);

exports.TestApi = TestApi;

var testApi = new TestApi();
testApi._init(_HttpGroupImpi2["default"]);
exports["default"] = testApi;
