(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6f976c90"],{"21e5":function(t,e,a){"use strict";var s=a("2eba"),n=a.n(s);n.a},"2eba":function(t,e,a){},fe52:function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"business"},[a("a-layout",{staticClass:"bt"},[a("a-layout-header",{staticClass:"header bt clearfix"},[a("div",{staticClass:"logo fl"},[a("span",{staticClass:"title"},[t._v(t._s(t.$global.projectName))]),a("span",{staticClass:"version"},[t._v(t._s(t.$global.version))])]),a("a-menu",{staticClass:"topmenu bt fl",attrs:{mode:"horizontal",selectedKeys:[t.defaultModule]}},t._l(t.menuList,(function(e){return a("a-menu-item",{key:e.moduleEn,on:{click:function(a){return t.toPlace(e)}}},[!e.migths||e.migths.includes(t.role)?a("div",[e.migths||1?a("div",[a("a-icon",{attrs:{type:e.icon}}),t._v(" "+t._s(e.moduleCn)+" ")],1):t._e()]):t._e()])})),1),a("div",{staticClass:"systemMenu fr clearfix"},[a("div",{staticClass:"unitTop fl"},[a("a-dropdown",[a("a",{staticClass:"ant-dropdown-link unitName",on:{click:function(t){return t.preventDefault()}}},[t._v(t._s(t._f("getUnitCnByEn")(t.selectUnitEn,t.that)))]),a("a-menu",{attrs:{slot:"overlay"},slot:"overlay"},t._l(t.unitData,(function(e){return a("a-menu-item",{key:e.ename},[a("a",{attrs:{href:"javascript:;"},on:{click:function(a){return t.choiceUnit(e)}}},[t._v(t._s(e.cname))])])})),1)],1)],1),a("div",{staticClass:"notification fl systemIcon"},[a("a-dropdown",{attrs:{trigger:["click"]},nativeOn:{click:function(e){return t.searchTaskClick(e)}},model:{value:t.dropDownFlag,callback:function(e){t.dropDownFlag=e},expression:"dropDownFlag"}},[a("a-tooltip",{attrs:{title:"离线任务通知"}},[a("div",{attrs:{id:"components-badge-demo-dot"}},[a("a-badge",{attrs:{dot:t.dotflag}},[a("a-icon",{staticStyle:{"font-size":"22px"},attrs:{type:"cloud-download"}})],1)],1)]),a("div",{attrs:{slot:"overlay"},slot:"overlay"},[a("a-card",{staticStyle:{width:"800px","background-color":"#054273"}},[a("div",[a("a-table",{attrs:{columns:t.columns,"data-source":t.tableData,rowKey:"id",size:"middle",pagination:!1},scopedSlots:t._u([{key:"action",fn:function(e,s){return a("span",{},[a("a",{staticClass:"t-optColor-normal",attrs:{disabled:"0"===s.status||"2"===s.status},on:{click:function(e){return t.downTask(s.id)}}},[a("a-icon",{attrs:{type:"download",title:"打包下载"}})],1),a("a",{staticClass:"t-optColor-warning",on:{click:function(e){return t.deleteTask(s)}}},[a("a-icon",{staticStyle:{color:"red"},attrs:{type:"delete",title:"删除"}})],1)])}},{key:"startTime",fn:function(e,s){return a("span",{},[a("span",[t._v(t._s(t._f("dateFormatter")(s.startTime,1)))])])}},{key:"endTime",fn:function(e,s){return a("span",{},[a("span",[t._v(t._s(t._f("dateFormatter")(s.endTime,1)))])])}},{key:"status",fn:function(e,s){return a("span",{},[a("span",[t._v(t._s(t._f("parseStatus")(s.status,s.status)))])])}}])}),a("div",{staticClass:"ar",staticStyle:{"margin-top":"20px"}},[a("a-pagination",{attrs:{total:t.total,"page-size":t.pageSize},on:{change:function(e){return t.pageChange()}},model:{value:t.curPage,callback:function(e){t.curPage=e},expression:"curPage"}})],1)],1)])],1)],1)],1),a("div",{staticClass:"system fl"},[a("a-dropdown",[a("a",{staticClass:"system-user",on:{click:function(t){return t.preventDefault()}}},[a("a-avatar",{attrs:{src:"/static/imagin/b2.jpg"}}),t._v(" "+t._s(t.role)+" ")],1),a("a-menu",{attrs:{slot:"overlay"},slot:"overlay"},[a("a-menu-item",[a("a",{on:{click:t.logout}},[a("a-icon",{attrs:{type:"logout"}}),t._v(" 退出系统 ")],1)])],1)],1)],1)])],1),a("a-layout-content",{staticClass:"bottomContent bt"},[a("router-view")],1)],1)],1)},n=[],i=(a("b0c0"),a("5530")),o=a("2f62"),c=a("03b1"),r={name:"business",data:function(){return{role:sessionStorage.getItem("username"),menuList:[],defaultModule:"",that:this,dropDownFlag:!1,dotflag:!0,curPage:1,pageSize:5,total:0,tableData:[],setInterName:null,columns:[{dataIndex:"file",title:"名称",ellipsis:!0},{dataIndex:"startTime",title:"开始时间",scopedSlots:{customRender:"startTime"}},{dataIndex:"endTime",title:"结束时间",scopedSlots:{customRender:"endTime"}},{dataIndex:"sizes",title:"文件大小(kb)"},{dataIndex:"status",title:"状态",scopedSlots:{customRender:"status"}},{dataIndex:"action",title:"操作",scopedSlots:{customRender:"action"}}]}},computed:Object(i["a"])({},Object(o["b"])(["unitData","selectUnitEn"])),methods:{toPlace:function(t){this.$fun.toPlace(t,this)},toHome:function(){this.$router.push({name:"home"})},logout:function(){this.$router.push({name:"login"}),sessionStorage.clear(),localStorage.clear()},choiceUnit:function(t){this.$store.commit("setSelectUnitEn",t.ename),this.$store.commit("setSelectUnitId",t.id),sessionStorage.setItem("selectUnitEn",t.ename),sessionStorage.setItem("unitId",t.id),"searchLog"===this.$route.name&&this.$router.push({name:"searchLog"})},refresh:function(t){var e=this,a={refresh:t};this.$API.forceRefreshCache(a).then((function(t){1e3===t.code?e.$message.success("刷新成功"):e.$message.warning("刷新失败")}))},refreshCountry:function(){var t=this,e={};this.$API.forceRefreshCountry(e).then((function(e){1e3===e.code?t.$message.success("刷新国家和地区成功"):t.$message.warning("刷新国家和地区失败")}))},searchTaskClick:function(){this.curPage=1,this.searchTask()},searchTask:function(){var t=this,e={page:this.curPage,size:this.pageSize};this.$API.searchOfflineTask(e).then((function(e){1e3===e.code?(t.tableData=e.data,t.total=e.count):t.tableData=[]}))},dropDownClose:function(){this.dropDownFlag=!1},pageChange:function(){this.searchTask()},downTask:function(t){this.$API.downloadOfflineFile({id:t}).then((function(t){Object(c["c"])("".concat(t.data.filepath),t.data.filename)}))},deleteTask:function(t){var e=this;e.$API.deleteOfflineTaskById(t.id).then((function(t){1e3===t.code?(e.$message.success("删除成功"),e.searchTask()):e.$message.warning("删除失败")}))}},watch:{$route:{handler:function(){this.defaultModule=this.$fun.getSecondRoute(this)},deep:!0},selectUnitEn:{handler:function(){console.log("我要根据单位请求分析对象了"),this.$store.dispatch("getObjByUnit",this)}}},created:function(){this.menuList=this.$global.menuList,this.defaultModule=this.$fun.getSecondRoute(this),this.$store.dispatch("getObjectData",this),this.$store.dispatch("getRuleGroupData",this),this.$store.dispatch("getUnitData",this),this.searchTask()},mounted:function(){},beforeDestroy:function(){clearInterval(this.setInterName)}},l=r,u=(a("21e5"),a("2877")),d=Object(u["a"])(l,s,n,!1,null,null,null);e["default"]=d.exports}}]);
//# sourceMappingURL=chunk-6f976c90.3231c11c.js.map