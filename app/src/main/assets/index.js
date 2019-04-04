// { "framework": "Vue"}

/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 33);
/******/ })
/************************************************************************/
/******/ ({

/***/ 33:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _index = __webpack_require__(34);

var _index2 = _interopRequireDefault(_index);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

_index2.default.el = '#root';
new Vue(_index2.default);

/***/ }),

/***/ 34:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(35)
)

/* script */
__vue_exports__ = __webpack_require__(36)

/* template */
var __vue_template__ = __webpack_require__(37)
__vue_options__ = __vue_exports__ = __vue_exports__ || {}
if (
  typeof __vue_exports__.default === "object" ||
  typeof __vue_exports__.default === "function"
) {
if (Object.keys(__vue_exports__).some(function (key) { return key !== "default" && key !== "__esModule" })) {console.error("named exports are not supported in *.vue files.")}
__vue_options__ = __vue_exports__ = __vue_exports__.default
}
if (typeof __vue_options__ === "function") {
  __vue_options__ = __vue_options__.options
}
__vue_options__.__file = "/Users/apple/awesome-app/src/index.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-2964abc9"
__vue_options__.style = __vue_options__.style || {}
__vue_styles__.forEach(function (module) {
  for (var name in module) {
    __vue_options__.style[name] = module[name]
  }
})
if (typeof __register_static_styles__ === "function") {
  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)
}

module.exports = __vue_exports__


/***/ }),

/***/ 35:
/***/ (function(module, exports) {

module.exports = {
  "introduce": {
    "marginTop": "25",
    "marginLeft": "25",
    "marginRight": "25",
    "justifyContent": "center",
    "alignItems": "center",
    "height": "1000",
    "width": "700",
    "backgroundColor": "rgba(162,217,192,0.2)"
  },
  "introduce_content": {
    "color": "#41B883",
    "textAlign": "center"
  },
  "btn_div": {
    "justifyContent": "center",
    "alignItems": "center",
    "marginTop": "60",
    "borderWidth": "2",
    "borderStyle": "solid",
    "borderColor": "#DDDDDD",
    "width": "200",
    "height": "100",
    "backgroundColor": "#F5F5F5"
  },
  "btn": {
    "fontSize": "50",
    "textAlign": "center",
    "color": "rgba(75,75,75,0.65)"
  },
  "div_father": {
    "alignItems": "center"
  }
}

/***/ }),

/***/ 36:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});
//
//
//
//
//
//
//
//
//
//
//
//

var dom = weex.requireModule('dom');
var navigator = weex.requireModule('navigator');
exports.default = {
    name: 'App',
    data: function data() {
        return {
            introduceContent: '没什么特殊功能，全是weex基础，写这个就是为了把weex基础打扎实，目前还有部分未写完'
        };
    },
    methods: {
        Intents: function Intents(event) {
            navigator.push({
                url: 'local://file://assets/dist/MainPage.js',
                animated: "true"
            }, function (event) {});
        }
    },
    mounted: function mounted() {
        //获取手机宽高
        //      this.heightLength = this.$getConfig().env.deviceHeight;
        //      this.widthLength = this.$getConfig().env.deviceWidth;
    }
};

/***/ }),

/***/ 37:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: ["div_father"]
  }, [_c('div', {
    staticClass: ["introduce"]
  }, [_c('text', {
    staticClass: ["introduce_content"]
  }, [_vm._v(_vm._s(_vm.introduceContent))])]), _c('div', {
    staticClass: ["btn_div"]
  }, [_c('a', {
    staticClass: ["btn"],
    on: {
      "click": _vm.Intents
    }
  }, [_vm._v("下一步")])])])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });