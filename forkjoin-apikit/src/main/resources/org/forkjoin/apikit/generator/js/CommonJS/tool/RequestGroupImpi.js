"use strict";

Object.defineProperty(exports, "__esModule", {
    value: true
});
exports.RequestGroupImpi = undefined;

var _classCallCheck2 = require("babel-runtime/helpers/classCallCheck");

var _classCallCheck3 = _interopRequireDefault(_classCallCheck2);

var _createClass2 = require("babel-runtime/helpers/createClass");

var _createClass3 = _interopRequireDefault(_createClass2);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/**
 * 一组Http 请求，用于管理http请求，进行批量清理等操作
 * 比如页面关闭后相关请求的停止。
 */
var RequestGroupImpi = function () {
    function RequestGroupImpi() {
        (0, _classCallCheck3.default)(this, RequestGroupImpi);

        this.requestMap = {};
        this._isStop = true;
        this._isStop = false;
    }

    (0, _createClass3.default)(RequestGroupImpi, [{
        key: "add",
        value: function add(tag, request) {
            var _this = this;

            if (this._isStop) {
                request.abort();
            } else {
                var requestArray = this.requestMap[tag];
                if (!requestArray) {
                    requestArray = [];
                    this.requestMap[tag] = requestArray;
                }
                requestArray.push(request);
                request.addEventListener("loadend", function () {
                    _this._clear(tag, request);
                });
            }
        }
    }, {
        key: "_clear",
        value: function _clear(tag, request) {
            var requestArray = this.requestMap[tag];
            if (requestArray) {
                var index = requestArray.indexOf(request);
                if (index > -1) {
                    requestArray.splice(index, 1);
                }
            }
        }
        /**
         * 停止已经发起的请求，不会阻止之后发生的任何请求
         *
         * @param {string} tag
         */

    }, {
        key: "stop",
        value: function stop(tag) {
            var requestArray = this.requestMap[tag];
            if (requestArray) {
                requestArray.forEach(function (item) {
                    item.abort();
                });
                requestArray.length = 0;
            }
        }
        /**
         * 停止全部请求，且会阻止其后发起的任何请求
         */

    }, {
        key: "stopAll",
        value: function stopAll() {
            for (var tag in this.requestMap) {
                var requestArray = this.requestMap[tag];
                if (requestArray) {
                    requestArray.forEach(function (item) {
                        item.abort();
                    });
                }
            }
            this.requestMap = {};
            this._isStop = true;
        }
    }]);
    return RequestGroupImpi;
}();

exports.RequestGroupImpi = RequestGroupImpi;
exports.default = new RequestGroupImpi();