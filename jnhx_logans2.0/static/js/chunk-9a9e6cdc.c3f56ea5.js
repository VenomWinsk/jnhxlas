(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-9a9e6cdc"],{"06c5":function(t,e,n){"use strict";n.d(e,"a",(function(){return a}));n("a630"),n("fb6a"),n("b0c0"),n("d3b7"),n("25f0"),n("3ca3");var r=n("6b75");function a(t,e){if(t){if("string"===typeof t)return Object(r["a"])(t,e);var n=Object.prototype.toString.call(t).slice(8,-1);return"Object"===n&&t.constructor&&(n=t.constructor.name),"Map"===n||"Set"===n?Array.from(t):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?Object(r["a"])(t,e):void 0}}},"1c7e":function(t,e,n){var r=n("b622"),a=r("iterator"),i=!1;try{var o=0,c={next:function(){return{done:!!o++}},return:function(){i=!0}};c[a]=function(){return this},Array.from(c,(function(){throw 2}))}catch(s){}t.exports=function(t,e){if(!e&&!i)return!1;var n=!1;try{var r={};r[a]=function(){return{next:function(){return{done:n=!0}}}},t(r)}catch(s){}return n}},"25f0":function(t,e,n){"use strict";var r=n("6eeb"),a=n("825a"),i=n("d039"),o=n("ad6d"),c="toString",s=RegExp.prototype,u=s[c],l=i((function(){return"/a/b"!=u.call({source:"a",flags:"b"})})),d=u.name!=c;(l||d)&&r(RegExp.prototype,c,(function(){var t=a(this),e=String(t.source),n=t.flags,r=String(void 0===n&&t instanceof RegExp&&!("flags"in s)?o.call(t):n);return"/"+e+"/"+r}),{unsafe:!0})},"6b75":function(t,e,n){"use strict";function r(t,e){(null==e||e>t.length)&&(e=t.length);for(var n=0,r=new Array(e);n<e;n++)r[n]=t[n];return r}n.d(e,"a",(function(){return r}))},"7a33":function(t,e,n){},"7b69":function(t,e,n){"use strict";var r=n("7a33"),a=n.n(r);a.a},"8de8":function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"field pageSpace h100"},[n("a-layout-content",[n("div",{attrs:{clss:"field"}},[n("div",{staticClass:"tableTopTools clearfix"},[n("div",{staticClass:"buttons fl"},[n("p",{staticClass:"toolName fl"},[t._v("工具栏：")]),n("a-button",{attrs:{type:"primary"},on:{click:t.optfield}},[n("a-icon",{attrs:{type:"plus"}}),t._v("添加字段 ")],1)],1)]),n("div",{staticClass:"tableContent"},[n("a-table",{attrs:{columns:t.columns,dataSource:t.fieldData,rowKey:"id",pagination:{pageSize:50}},scopedSlots:t._u([{key:"num",fn:function(e,r,a){return n("div",{staticClass:"clearfix"},[n("div",{staticClass:"tableNum fl"},[t._v(t._s(a+1))])])}},{key:"gmtCreate",fn:function(e,r){return n("span",{},[n("span",[t._v(t._s(t._f("dateFormatter")(r.gmtCreate,1)))])])}},{key:"gmtModified",fn:function(e,r){return n("span",{},[n("span",[t._v(t._s(t._f("dateFormatter")(r.gmtModified,1)))])])}},{key:"action",fn:function(e,r){return n("span",{},[n("a",{staticClass:"t-optColor-normal",on:{click:function(e){return t.editfield(r)}}},[t._v("编辑")]),n("a",{staticClass:"t-optColor-warning",on:{click:function(e){return t.deletefield(r)}}},[t._v("删除")])])}},{key:"isEnabled",fn:function(e,r){return n("span",{},[n("a-switch",{attrs:{size:"small"},on:{change:function(e){return t.onSwitchChange(r)}},model:{value:r.isEnabled,callback:function(e){t.$set(r,"isEnabled",e)},expression:"record.isEnabled"}})],1)}}])})],1)])])],1)},a=[],i=n("b85c"),o={name:"project-list",props:{fieldData:{type:Array,default:function(){return[]}},currentNode:{}},data:function(){return{columns:[{dataIndex:"num",title:"序号",scopedSlots:{customRender:"num"}},{dataIndex:"cname",title:"字段名称"},{dataIndex:"ename",title:"字段英文名"},{dataIndex:"type",title:"字段数据类型"},{dataIndex:"creator",title:"创建者"},{dataIndex:"gmtCreate",title:"创建时间",scopedSlots:{customRender:"gmtCreate"}},{dataIndex:"gmtModified",title:"最近更新时间",scopedSlots:{customRender:"gmtModified"}},{dataIndex:"action",title:"操作",scopedSlots:{customRender:"action"}}],pageParam:{page:1,size:500,channelId:this.$route.query.id},customPage:{page:1,size:10},searchInput:"",total:0,extraBreadcrumb:["频道字段管理",this.$route.query.cname]}},computed:{num:function(){return(this.pageParam.page-1)*this.pageParam.size}},methods:{adjust:function(t,e,n,r){var a=this;console.log(t),console.log(n),console.log(e),console.log(r);var i=[{id:t,findex:r},{id:e,findex:n}];console.log(i);var o={list:i};this.$API.bathUpdate(o).then((function(t){1e3===t.code?a.getfield():a.$message.error("调整字段顺序错误")}))},getfield:function(){var t=this;this.$API.searchField(this.pageParam).then((function(e){if(1e3===e.code){var n,r=Object(i["a"])(e.data);try{for(r.s();!(n=r.n()).done;){var a=n.value;a.isEnabled=1===a.isEnabled}}catch(o){r.e(o)}finally{r.f()}t.fieldData=e.data,t.total=e.total}else t.fieldData=[]}))},optfield:function(){console.log(this.fieldData),console.log(this.currentNode),this.$router.push({path:"/business/setting/field/add",query:{cid:this.currentNode.id,ctype:this.currentNode.ownerType}})},editfield:function(t){this.$router.push({path:"/business/setting/field/edit",query:{channelId:this.$route.query.id,fieldId:t.id,channelName:this.$route.query.cname}})},deletefield:function(t){var e=this.$createElement,n=this;this.$confirm({title:"确定删除该条数据吗？",content:function(){return e("div",{style:"color:red"},["删除后将不可恢复"])},okText:"确认删除",cancelText:"取消删除",onOk:function(){n.$API.delField(t.id).then((function(t){1e3===t.code?(n.$message.success("删除成功"),n.$emit("refresh",!0)):n.$message.warning(t.message)}))}})},pageChange:function(){this.getfield()},keywordChange:function(){this.pageParam.page=1,this.getfield()},onSwitchChange:function(t){var e=this,n={id:t.id,isEnabled:t.isEnabled?1:0};this.$API.editField(n).then((function(t){1e3===t.code?(e.$message.success("字段更新成功"),e.fieldVisible=!1,e.getfield()):e.$message.warning(t.message)}))}},created:function(){}},c=o,s=(n("7b69"),n("2877")),u=Object(s["a"])(c,r,a,!1,null,null,null);e["default"]=u.exports},a630:function(t,e,n){var r=n("23e7"),a=n("4df4"),i=n("1c7e"),o=!i((function(t){Array.from(t)}));r({target:"Array",stat:!0,forced:o},{from:a})},b85c:function(t,e,n){"use strict";n.d(e,"a",(function(){return a}));n("a4d3"),n("e01a"),n("d28b"),n("d3b7"),n("3ca3"),n("ddb0");var r=n("06c5");function a(t,e){var n;if("undefined"===typeof Symbol||null==t[Symbol.iterator]){if(Array.isArray(t)||(n=Object(r["a"])(t))||e&&t&&"number"===typeof t.length){n&&(t=n);var a=0,i=function(){};return{s:i,n:function(){return a>=t.length?{done:!0}:{done:!1,value:t[a++]}},e:function(t){throw t},f:i}}throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}var o,c=!0,s=!1;return{s:function(){n=t[Symbol.iterator]()},n:function(){var t=n.next();return c=t.done,t},e:function(t){s=!0,o=t},f:function(){try{c||null==n["return"]||n["return"]()}finally{if(s)throw o}}}}},d28b:function(t,e,n){var r=n("746f");r("iterator")},e01a:function(t,e,n){"use strict";var r=n("23e7"),a=n("83ab"),i=n("da84"),o=n("5135"),c=n("861d"),s=n("9bf2").f,u=n("e893"),l=i.Symbol;if(a&&"function"==typeof l&&(!("description"in l.prototype)||void 0!==l().description)){var d={},f=function(){var t=arguments.length<1||void 0===arguments[0]?void 0:String(arguments[0]),e=this instanceof f?new l(t):void 0===t?l():l(t);return""===t&&(d[e]=!0),e};u(f,l);var p=f.prototype=l.prototype;p.constructor=f;var g=p.toString,h="Symbol(test)"==String(l("test")),m=/^Symbol\((.*)\)[^)]+$/;s(p,"description",{configurable:!0,get:function(){var t=c(this)?this.valueOf():this,e=g.call(t);if(o(d,t))return"";var n=h?e.slice(7,-1):e.replace(m,"$1");return""===n?void 0:n}}),r({global:!0,forced:!0},{Symbol:f})}},fb6a:function(t,e,n){"use strict";var r=n("23e7"),a=n("861d"),i=n("e8b5"),o=n("23cb"),c=n("50c4"),s=n("fc6a"),u=n("8418"),l=n("b622"),d=n("1dde"),f=n("ae40"),p=d("slice"),g=f("slice",{ACCESSORS:!0,0:0,1:2}),h=l("species"),m=[].slice,v=Math.max;r({target:"Array",proto:!0,forced:!p||!g},{slice:function(t,e){var n,r,l,d=s(this),f=c(d.length),p=o(t,f),g=o(void 0===e?f:e,f);if(i(d)&&(n=d.constructor,"function"!=typeof n||n!==Array&&!i(n.prototype)?a(n)&&(n=n[h],null===n&&(n=void 0)):n=void 0,n===Array||void 0===n))return m.call(d,p,g);for(r=new(void 0===n?Array:n)(v(g-p,0)),l=0;p<g;p++,l++)p in d&&u(r,l,d[p]);return r.length=l,r}})}}]);
//# sourceMappingURL=chunk-9a9e6cdc.c3f56ea5.js.map