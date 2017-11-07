'use strict';


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _BaseApi = require('./api/BaseApi');
var _BaseApi2 = _interopRequireDefault(_BaseApi);
var _TestApi = require('./api/TestApi');
var _TestApi2 = _interopRequireDefault(_TestApi);
var _TestNoResultApi = require('./api/TestNoResultApi');
var _TestNoResultApi2 = _interopRequireDefault(_TestNoResultApi);
var _SysApi = require('./api/SysApi');
var _SysApi2 = _interopRequireDefault(_SysApi);
var _AccountApi = require('./api/AccountApi');
var _AccountApi2 = _interopRequireDefault(_AccountApi);
var _PageApi = require('./api/PageApi');
var _PageApi2 = _interopRequireDefault(_PageApi);

var _Apis = require('./Apis');
var _Apis2 = _interopRequireDefault(_Apis);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { 'default': obj }; }

exports['default'] = _Apis2['default'];

exports.BaseApi = _BaseApi.BaseApi;
exports.baseApi = _BaseApi2['default'];
exports.TestApi = _TestApi.TestApi;
exports.testApi = _TestApi2['default'];
exports.TestNoResultApi = _TestNoResultApi.TestNoResultApi;
exports.testNoResultApi = _TestNoResultApi2['default'];
exports.SysApi = _SysApi.SysApi;
exports.sysApi = _SysApi2['default'];
exports.AccountApi = _AccountApi.AccountApi;
exports.accountApi = _AccountApi2['default'];
exports.PageApi = _PageApi.PageApi;
exports.pageApi = _PageApi2['default'];
