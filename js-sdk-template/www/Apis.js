'use strict';

Object.defineProperty(exports, "__esModule", {
    value: true
});

var _classCallCheck2 = require('babel-runtime/helpers/classCallCheck');

var _classCallCheck3 = _interopRequireDefault(_classCallCheck2);

var _createClass2 = require('babel-runtime/helpers/createClass');

var _createClass3 = _interopRequireDefault(_createClass2);

var _AccountApi = require('./api/AccountApi');

var _RequestGroupImpi = require('./RequestGroupImpi');

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var Apis = function () {
    function Apis() {
        (0, _classCallCheck3.default)(this, Apis);

        this.requestGroup = new _RequestGroupImpi.RequestGroupImpi();
        this.accountApi = new _AccountApi.AccountApi();
        this.accountApi._init(this.requestGroup);
    }

    (0, _createClass3.default)(Apis, [{
        key: 'stopAll',
        value: function stopAll() {
            this.requestGroup.stopAll();
        }
    }, {
        key: 'stop',
        value: function stop(tag) {
            this.requestGroup.stop(tag);
        }
    }]);
    return Apis;
}();

exports.default = Apis;