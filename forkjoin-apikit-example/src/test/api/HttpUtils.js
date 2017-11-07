"use strict";

Object.defineProperty(exports, "__esModule", {
    value: true
});

var _classCallCheck2 = require("babel-runtime/helpers/classCallCheck");

var _classCallCheck3 = _interopRequireDefault(_classCallCheck2);

var _createClass2 = require("babel-runtime/helpers/createClass");

var _createClass3 = _interopRequireDefault(_createClass2);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var HttpUtils = function () {
    function HttpUtils() {
        (0, _classCallCheck3["default"])(this, HttpUtils);
    }

    (0, _createClass3["default"])(HttpUtils, null, [{
        key: "request",
        value: function request(tag, method, url, pathVars, formObject, handler) {
            return HttpUtils.impl.request(tag, method, url, pathVars, formObject, handler);
        }
    }, {
        key: "setImpoi",
        value: function setImpoi(impi) {
            HttpUtils.impl = impi;
        }
    }]);
    return HttpUtils;
}();

exports["default"] = HttpUtils;
module.exports = exports["default"];