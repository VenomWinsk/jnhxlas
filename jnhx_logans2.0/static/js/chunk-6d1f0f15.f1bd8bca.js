(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6d1f0f15"],{a15b:function(t,a,e){"use strict";var s=e("23e7"),i=e("44ad"),n=e("fc6a"),l=e("a640"),o=[].join,c=i!=Object,r=l("join",",");s({target:"Array",proto:!0,forced:c||!r},{join:function(t){return o.call(n(this),void 0===t?",":t)}})},ce49:function(t,a,e){},e157:function(t,a,e){"use strict";e.r(a);var s=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"task pageSpace h100"},[e("div",[e("a-form",{attrs:{"label-width":80}},[e("a-row",{staticStyle:{margin:"0"},attrs:{gutter:[24,24]}},[e("a-col",{staticStyle:{padding:"0 12px 12px"},attrs:{xxl:8,xl:12}},[e("label",{staticClass:"searchCss",staticStyle:{color:"white"}},[t._v("分析对象")]),e("a-select",{staticClass:"searchContentCss",attrs:{allowClear:!0},on:{change:t.objChange},model:{value:t.analysisObj.value,callback:function(a){t.$set(t.analysisObj,"value",a)},expression:"analysisObj.value"}},t._l(t.analysisObjList,(function(a){return e("a-select-option",{key:a},[t._v(t._s(a))])})),1)],1),e("a-col",{staticStyle:{padding:"0 12px 12px"},attrs:{xxl:8,xl:12}},[e("label",{staticClass:"searchCss",staticStyle:{color:"white"}},[t._v("开始时间")]),e("a-date-picker",{staticClass:"searchContentCss",attrs:{format:"YYYY-MM-DD HH:mm:ss","show-time":{defaultValue:t.moment("00:00:00","HH:mm:ss")}},model:{value:t.beginTime,callback:function(a){t.beginTime=a},expression:"beginTime"}})],1),e("a-col",{staticStyle:{padding:"0 12px 12px"},attrs:{xxl:8,xl:12}},[e("label",{staticClass:"searchCss",staticStyle:{color:"white"}},[t._v("结束时间")]),e("a-date-picker",{staticClass:"searchContentCss",attrs:{format:"YYYY-MM-DD HH:mm:ss","show-time":{defaultValue:t.moment("00:00:00","HH:mm:ss")}},model:{value:t.endTime,callback:function(a){t.endTime=a},expression:"endTime"}})],1),e("a-col",{staticStyle:{padding:"0 12px 12px"},attrs:{xxl:8,xl:12}},[e("label",{staticClass:"searchCss",staticStyle:{color:"white"}},[t._v("协议")]),e("a-select",{staticClass:"searchContentCss",attrs:{mode:"multiple",filterable:"",remote:"","max-tag-count":1},model:{value:t.agrementValue.value,callback:function(a){t.$set(t.agrementValue,"value",a)},expression:"agrementValue.value"}},t._l(t.ruleGroupData,(function(a){return e("a-select-option",{key:a.id,attrs:{value:a.code}},[t._v(t._s(a.name))])})),1)],1),e("a-col",{staticStyle:{padding:"0 12px 12px"},attrs:{xxl:8,xl:12}},[e("label",{staticClass:"searchCss",staticStyle:{color:"white"}},[t._v("国家个数>=")]),e("a-input",{staticClass:"searchContentCss",attrs:{"addon-after":"次"},model:{value:t.country,callback:function(a){t.country=a},expression:"country"}})],1),e("a-col",{staticStyle:{padding:"0 12px 12px"},attrs:{xxl:8,xl:12}},[e("label",{staticClass:"searchCss",staticStyle:{color:"white"}},[t._v("地区个数>=")]),e("a-input",{staticClass:"searchContentCss",attrs:{"addon-after":"次"},model:{value:t.city,callback:function(a){t.city=a},expression:"city"}})],1)],1),e("a-row",[e("a-col",{staticStyle:{float:"right"},attrs:{lg:2}},[e("a-button",{attrs:{type:"primary",loading:t.isLoading},on:{click:t.exportCsv}},[e("a-icon",{attrs:{type:"export"}}),t._v(" 导出 ")],1)],1),e("a-col",{staticStyle:{float:"right"},attrs:{lg:2}},[e("a-button",{attrs:{type:"primary"},on:{click:t.searchData}},[e("a-icon",{attrs:{type:"search"}}),t._v(" 查询 ")],1)],1)],1)],1)],1),e("div",{staticStyle:{"margin-top":"40px"}},[e("a-table",{attrs:{columns:t.columns,"data-source":t.tableData,rowKey:"id",loading:t.loading,pagination:{showSizeChanger:!0,showQuickJumper:!0,pageSizeOptions:t.sizeList}},scopedSlots:t._u([{key:"emailName",fn:function(a,s){return e("span",{},[e("span",[t._v(t._s(s.emailName))])])}},{key:"action",fn:function(a,s){return e("span",{},[e("span",{staticClass:"t-optColor-success",staticStyle:{cursor:"pointer"},on:{click:function(a){return t.detailsChange(s)}}},[t._v("详情")])])}}])}),e("p",{staticStyle:{float:"right","margin-left":"64px","margin-top":"16px","font-size":"15px"}},[t._v("总条数："+t._s(t.total))])],1),e("a-modal",{staticClass:"modalCls",attrs:{title:t.detailsTitle,width:"60%"},model:{value:t.modalVisible,callback:function(a){t.modalVisible=a},expression:"modalVisible"}},[e("a-table",{attrs:{border:"",columns:t.deColumns,dataSource:t.detailsData,rowKey:"id",loading:t.detailLoading,pagination:t.pagination}}),e("p",[e("span",[t._v(" 当前显示: "),e("span",{staticStyle:{"font-size":"20px",color:"#2D8CF0"}},[t._v(t._s(t.detailsData.length))])])]),e("div",{attrs:{slot:"footer"},slot:"footer"},[t.detailsData.length<t.detailTotal?e("a-button",{attrs:{type:"primary",disabled:t.detailLoading},on:{click:function(a){return t.dataMore()}}},[t._v(" 加载更多 ")]):e("span",[t._v("已显示全部")])],1)],1)],1)},i=[],n=(e("99af"),e("a15b"),e("d81d"),e("5530")),l=e("c1df"),o=e.n(l),c=e("367a"),r=e("2f62"),u={name:"distanceAccessAnalyze",data:function(){return{csvHelper:c["a"],pagination:{},detailTotal:0,value1:null,loading:!1,detailsTitle:"详情记录",modalVisible:!1,total:0,sizeList:["5","10","20","30"],isLoading:!1,columns:[{dataIndex:"emailName",title:"邮箱名",ellipsis:!0,scopedSlots:{customRender:"emailName"}},{dataIndex:"countryCount",title:"国家个数",sorter:function(t,a){return distanceAccessAnalyze.countryCount-a.countryCount}},{dataIndex:"countryRealCount",title:"地区个数",sorter:function(t,a){return distanceAccessAnalyze.countryRealCount-a.countryRealCount}},{dataIndex:"ipcount",title:"ip个数",sorter:function(t,a){return distanceAccessAnalyze.ipcount-a.ipcount}},{dataIndex:"action",title:"操作",scopedSlots:{customRender:"action"}}],deColumns:[{title:"请求IP",dataIndex:"req_ip"},{title:"国家",dataIndex:"country"},{title:"城市",dataIndex:"city"}],detailsData:[],tableData:[],detailLoading:!1,beginTime:"",endTime:"",country:"2",city:"2",agrementValue:{label:"ptTag",value:[]},unitValue:{label:"unit",value:[]},analysisObj:{label:"analysis",value:""},analysisObjList:[],units:[],unitName:"",ruleGroupData:[]}},computed:Object(n["a"])({},Object(r["b"])(["selectUnitEn","objByCurrentUnit"])),methods:{moment:o.a,onChange:function(t,a){console.log(t,a)},detailsChange:function(t){console.log(t),this.detailsData=[],this.modalVisible=!0,this.getDetail(t.emailName)},getDetail:function(t){var a=this,e={ana_obj:this.analysisObj.value,rule_groups:this.agrementValue.value,units:[sessionStorage.getItem("selectUnitEn")],username:t};this.$API.getEmailDetail(e).then((function(t){1e3===t.code?a.detailsData=t.data:a.detailsData=[]}))},emailNameClick:function(t){console.log(t);var a="",e="";this.beginTime&&(a=new o.a(this.beginTime).format("YYYY-MM-DD HH:mm:ss")),this.endTime&&(e=new o.a(this.endTime).format("YYYY-MM-DD HH:mm:ss"));for(var s="",i=0;i<this.agrementValue.value.length;i++)s+="rizhi=".concat(this.agrementValue.value[i],"&");var n="/#/business/search/searchLog",l="?&beginDate=".concat(a,"&endDate=").concat(e,"&username=").concat(t.emailName,"&").concat(s,"unitList=").concat(this.unitValue.value);window.open(n+l,"_blank")},searchData:function(){this.page=1,this.tableData=[],this.getDistanceList()},exportCsv:function(){if(this.isLoading=!0,this.tableData.length){var t=this.columns.map((function(t){return{label:t.title,value:t.dataIndex}})).concat([]),a=this.agrementValue.value.length>0?this.agrementValue.value.join("-"):"异地登陆";this.csvHelper.exports("".concat(a),this.tableData,t),this.isLoading=!1}else this.$message.warning("当前没有数据可以导出"),this.isLoading=!1},getDistanceList:function(){var t=this;this.loading=!0;var a={beginTime:this.beginTime?new o.a(this.beginTime).format("YYYY-MM-DD HH:mm:ss"):"",endTime:this.endTime?new o.a(this.endTime).format("YYYY-MM-DD HH:mm:ss"):"",units:[sessionStorage.getItem("selectUnitEn")],rule_groups:this.agrementValue.value||[],ana_obj:this.analysisObj.value||"",successCountry:this.country||0,successCity:this.city||0,top:1e4,loginType:"access"};this.$API.searchDistanceList(a).then((function(a){1e3===a.code?(t.tableData=a.data,t.total=a.data.length,t.loading=!1):(t.tableData=[],t.total=0,t.$message.warning("暂无数据"),t.loading=!1)}))},unitChange:function(t){this.unitName=t,this.getAnalysisData()},getUnit:function(){var t=this;this.$API.searchUnit({}).then((function(a){1e3===a.code?(t.units=a.data,t.unitValue.value=t.units[0].ename,t.unitChange(t.unitValue.value)):t.units=[]}))},getAnalysisData:function(){var t=this;this.$API.getAnalysisObj({name:this.selectUnitEn}).then((function(a){1e3===a.code?(t.analysisObjList=a.data,t.analysisObj.value=t.analysisObjList[0],t.selectByObjectName(t.analysisObj.value)):t.analysisObjList=[]}))},objChange:function(){this.selectByObjectName(this.analysisObj.value)},selectByObjectName:function(t){var a=this,e={name:t};this.$API.selectByObjectName(e).then((function(t){1e3===t.code?(a.objId=t.data.id,a.getAgreement(a.objId)):a.objId=""}))},getAgreement:function(t){var a=this;this.$API.searchRuleGroup({objectId:t}).then((function(t){1e3===t.code?a.ruleGroupData=t.data:a.ruleGroupData=[]}))}},created:function(){this.getUnit(),this.getAnalysisData()},watch:{selectUnitEn:{handler:function(){this.$store.dispatch("getObjByUnit",this),this.getAnalysisData()}}}},d=u,m=(e("fcea"),e("2877")),h=Object(m["a"])(d,s,i,!1,null,"60bc27ad",null);a["default"]=h.exports},fcea:function(t,a,e){"use strict";var s=e("ce49"),i=e.n(s);i.a}}]);
//# sourceMappingURL=chunk-6d1f0f15.f1bd8bca.js.map