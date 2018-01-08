'use strict';

Object.defineProperty(exports, "__esModule", {
    value: true
});

var _classCallCheck2 = require('babel-runtime/helpers/classCallCheck');

var _classCallCheck3 = _interopRequireDefault(_classCallCheck2);

var _createClass2 = require('babel-runtime/helpers/createClass');

var _createClass3 = _interopRequireDefault(_createClass2);

var _HttpUtils = require('./HttpUtils');

var _HttpUtils2 = _interopRequireDefault(_HttpUtils);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var AbstractApi = function () {
    function AbstractApi() {
        (0, _classCallCheck3.default)(this, AbstractApi);
    }
    /**
     * 可以不设置
     */


    (0, _createClass3.default)(AbstractApi, [{
        key: '_init',
        value: function _init(httpGroup) {
            this.requestGroup = httpGroup;
        }
    }, {
        key: '_request',
        value: function _request(tag, method, url, pathVars, formObject) {
            return _HttpUtils2.default.request(tag, method, url, pathVars, formObject, this.requestGroup);
        }
    }]);
    return AbstractApi;
}();

exports.default = AbstractApi;