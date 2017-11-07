'use strict';

Object.defineProperty(exports, "__esModule", {
    value: true
});

var _classCallCheck2 = require('babel-runtime/helpers/classCallCheck');

var _classCallCheck3 = _interopRequireDefault(_classCallCheck2);

var _createClass2 = require('babel-runtime/helpers/createClass');

var _createClass3 = _interopRequireDefault(_createClass2);


var _BaseApi = require('./api/BaseApi');
var _TestApi = require('./api/TestApi');
var _TestNoResultApi = require('./api/TestNoResultApi');
var _SysApi = require('./api/SysApi');
var _AccountApi = require('./api/AccountApi');
var _PageApi = require('./api/PageApi');

var _HttpGroupImpi = require('./HttpGroupImpi');

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { 'default': obj }; }

var Apis = function () {
    function Apis() {
        (0, _classCallCheck3['default'])(this, Apis);

        this.httpGroup = new _HttpGroupImpi.HttpGroupImpi();

        this.baseApi = new _BaseApi.BaseApi();
        this.baseApi._init(this.httpGroup);
        this.testApi = new _TestApi.TestApi();
        this.testApi._init(this.httpGroup);
        this.testNoResultApi = new _TestNoResultApi.TestNoResultApi();
        this.testNoResultApi._init(this.httpGroup);
        this.sysApi = new _SysApi.SysApi();
        this.sysApi._init(this.httpGroup);
        this.accountApi = new _AccountApi.AccountApi();
        this.accountApi._init(this.httpGroup);
        this.pageApi = new _PageApi.PageApi();
        this.pageApi._init(this.httpGroup);
    }

    (0, _createClass3['default'])(Apis, [{
        key: 'stopAll',
        value: function stopAll() {
            this.httpGroup.stopAll();
        }
    }, {
        key: 'stop',
        value: function stop(tag) {
            this.httpGroup.stop(tag);
        }
    }]);
    return Apis;
}();

exports['default'] = Apis;
module.exports = exports['default'];