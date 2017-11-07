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

var TestNoResultApi = function (_AbstractApi) {
    (0, _inherits3["default"])(TestNoResultApi, _AbstractApi);

    function TestNoResultApi() {
        (0, _classCallCheck3["default"])(this, TestNoResultApi);

        var _this = (0, _possibleConstructorReturn3["default"])(this, (TestNoResultApi.__proto__ || Object.getPrototypeOf(TestNoResultApi)).apply(this, arguments));




        this.testObjectList = function(testForm){
            var _path = null;
            return (0, _get3["default"])(TestNoResultApi.prototype.__proto__ || Object.getPrototypeOf(TestNoResultApi.prototype), "_request", _this).call(_this, "testNoResultApi", "POST", "noResult/testObjectList", _path, testForm);
        };



        this.create = function(testForm){
            var _path = null;
            return (0, _get3["default"])(TestNoResultApi.prototype.__proto__ || Object.getPrototypeOf(TestNoResultApi.prototype), "_request", _this).call(_this, "testNoResultApi", "POST", "noResult/test", _path, testForm);
        };



        this.get = function(id){
            var _path = {};
            _path["id"] = id;
            return (0, _get3["default"])(TestNoResultApi.prototype.__proto__ || Object.getPrototypeOf(TestNoResultApi.prototype), "_request", _this).call(_this, "testNoResultApi", "GET", "noResult/test/{id}", _path, null);
        };



        this.update = function(testForm){
            var _path = null;
            return (0, _get3["default"])(TestNoResultApi.prototype.__proto__ || Object.getPrototypeOf(TestNoResultApi.prototype), "_request", _this).call(_this, "testNoResultApi", "PUT", "noResult/test", _path, testForm);
        };



        this.patchUpdate = function(testForm){
            var _path = null;
            return (0, _get3["default"])(TestNoResultApi.prototype.__proto__ || Object.getPrototypeOf(TestNoResultApi.prototype), "_request", _this).call(_this, "testNoResultApi", "PATCH", "noResult/test", _path, testForm);
        };



        this.delete = function(id){
            var _path = {};
            _path["id"] = id;
            return (0, _get3["default"])(TestNoResultApi.prototype.__proto__ || Object.getPrototypeOf(TestNoResultApi.prototype), "_request", _this).call(_this, "testNoResultApi", "DELETE", "noResult/test/{id}", _path, null);
        };



        this.deletes = function(id){
            var _path = {};
            _path["id"] = id;
            return (0, _get3["default"])(TestNoResultApi.prototype.__proto__ || Object.getPrototypeOf(TestNoResultApi.prototype), "_request", _this).call(_this, "testNoResultApi", "DELETE", "noResult/tests/{id}", _path, null);
        };



        this.search = function(id, name){
            var _path = {};
            _path["id"] = id;
            _path["name"] = name;
            return (0, _get3["default"])(TestNoResultApi.prototype.__proto__ || Object.getPrototypeOf(TestNoResultApi.prototype), "_request", _this).call(_this, "testNoResultApi", "GET", "noResult/search/{id}/{name}", _path, null);
        };



        this.testString = function(name){
            var _path = {};
            _path["name"] = name;
            return (0, _get3["default"])(TestNoResultApi.prototype.__proto__ || Object.getPrototypeOf(TestNoResultApi.prototype), "_request", _this).call(_this, "testNoResultApi", "GET", "noResult/testString/{name}", _path, null);
        };



        this.testString1 = function(name, age){
            var _path = {};
            _path["name"] = name;
            _path["age"] = age;
            return (0, _get3["default"])(TestNoResultApi.prototype.__proto__ || Object.getPrototypeOf(TestNoResultApi.prototype), "_request", _this).call(_this, "testNoResultApi", "GET", "noResult/testString1/{name}/{age}", _path, null);
        };



        this.page = function(){
            var _path = null;
            return (0, _get3["default"])(TestNoResultApi.prototype.__proto__ || Object.getPrototypeOf(TestNoResultApi.prototype), "_request", _this).call(_this, "testNoResultApi", "GET", "noResult/page", _path, null);
        };

    }

    return TestNoResultApi;
}(_AbstractApi3["default"]);

exports.TestNoResultApi = TestNoResultApi;

var testNoResultApi = new TestNoResultApi();
testNoResultApi._init(_HttpGroupImpi2["default"]);
exports["default"] = testNoResultApi;
