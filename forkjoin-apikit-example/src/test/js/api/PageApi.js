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

var PageApi = function (_AbstractApi) {
    (0, _inherits3["default"])(PageApi, _AbstractApi);

    function PageApi() {
        (0, _classCallCheck3["default"])(this, PageApi);

        var _this = (0, _possibleConstructorReturn3["default"])(this, (PageApi.__proto__ || Object.getPrototypeOf(PageApi)).apply(this, arguments));




        this.page = function(page, pageSize){
            var _path = {};
            _path["page"] = page;
            _path["pageSize"] = pageSize;
            return (0, _get3["default"])(PageApi.prototype.__proto__ || Object.getPrototypeOf(PageApi.prototype), "_request", _this).call(_this, "pageApi", "GET", "page/page/{page}-{pageSize}", _path, null);
        };



        this.pageString = function(page, pageSize){
            var _path = {};
            _path["page"] = page;
            _path["pageSize"] = pageSize;
            return (0, _get3["default"])(PageApi.prototype.__proto__ || Object.getPrototypeOf(PageApi.prototype), "_request", _this).call(_this, "pageApi", "GET", "page/pageString/{page}-{pageSize}", _path, null);
        };

    }

    return PageApi;
}(_AbstractApi3["default"]);

exports.PageApi = PageApi;

var pageApi = new PageApi();
pageApi._init(_HttpGroupImpi2["default"]);
exports["default"] = pageApi;
