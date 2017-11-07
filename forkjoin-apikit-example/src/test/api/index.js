'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _AccountApi = require('./api/AccountApi');
var _AccountApi2 = _interopRequireDefault(_AccountApi);

var _Apis = require('./Apis');
var _Apis2 = _interopRequireDefault(_Apis);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { 'default': obj }; }

exports['default'] = _Apis2['default'];
exports.AccountApi = _AccountApi.AccountApi;
exports.accountApi = _AccountApi2['default'];