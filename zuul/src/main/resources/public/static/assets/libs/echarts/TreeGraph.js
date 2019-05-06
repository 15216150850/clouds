
function treeMenu(a){
    //列表map形式
    this.tree=a||[];
    this.groups={};
    //存放id与对应的name映射
    this.nameMap={}
    //得到每个点对应的层次,为了后期进行布局
    this.levelMap={}
    // //样式设计
    // this.style={"symbolSize":[70,60,60,60,60],"value":[30,30,30,30,30]}
};
treeMenu.prototype={
    init:function(pid){
        this.group();
        this.MapNamebyId();
        this.setIdLevel(pid);
        return this.rescusive(pid);
    },
    group:function(){
        for(var i=0;i<this.tree.length;i++){
            //存在该grops则直接添加
            if(this.groups[this.tree[i].pId]){
                this.groups[this.tree[i].pId].push(this.tree[i]);
            }else{
                this.groups[this.tree[i].pId]=[];
                this.groups[this.tree[i].pId].push(this.tree[i]);
            }
        }
    },
    //得到每个点的层次
    setIdLevel:function(pid){
        var level=1;
        this.levelMap[pid]=level;
        var gs=this.groups[pid];
        //str=JSON.stringify(gs)
        //alert("json:"+str)
        var temp=[]
        while(gs){
            level++;
            if(gs==null||gs==undefined||gs.length==0)
                break;
            temp=[]
            for(var i=0;i<gs.length;i++){
                var myid=gs[i]["id"];
                this.levelMap[myid]=level;
                subgs=this.groups[myid];
                if(subgs instanceof Array &&subgs!=null){
                    for(var j=0;j<subgs.length;j++){
                        temp.push(subgs[j]);
                    }
                }
            }
            gs=temp;
        }

    },
    //根据所在层次设计不同大小的样式
    getStyleById:function(id){
        // var level=this.levelMap[id]
        // if(level>=5)
        // 	level=5;
        // var symbolize=0
        // var value=0
        // symbolize=this.style['symbolSize'][level-1]
        // value=this.style['value'][level-1]
		//根据不同长度字设置不同长度框框
        var withLen ;
        if (typeof(this.nameMap[id])!='undefined'){
            withLen = 50+this.nameMap[id].length*10;
        }else{
            withLen = 0;
        }
        var styleValue={}
        styleValue['symbolSize']=[withLen,30]
        styleValue['value']=1
        return styleValue
    },
    MapNamebyId:function(){
        for(var i=0;i<this.tree.length;i++){
            map=this.tree[i]
            this.nameMap[map["id"]]=map["name"]
        }
    },
    //设置节点属性
    setNode:function(node,name,symbolize,value,children){
        var itemStyle={normal:{"borderWidth":2,"borderColor":"black"}}
        node['name']=name;
        node['symbolSize']=symbolize;
        node['value']=value;
        node['symbol']='rectangle';
        node['children']=children;
        node['itemStyle']=itemStyle;
        return node;
    },
    rescusive:function (number){//这里是构建数据源的重点
        var data=[]
        var node={}
        var styleValue={}
        //某个节点下的子节点
        var a=this.groups[number];
        var nodeName=this.nameMap[number];
        if(a==null||a==undefined){
            styleValue=this.getStyleById(number)
            //设置节点
            this.setNode(node,nodeName,styleValue['symbolSize'],number,[])
            return node;
        }
        for(var i=0;i<a.length;i++){
            children=this.rescusive(a[i].id);
            data.push(children);
        }
        styleValue=this.getStyleById(number)
        this.setNode(node,nodeName,styleValue['symbolSize'],number,data)
        return node;
    },
    //创建组织结构图
    createTreeVisual:function(myChart,title,data){
        var option = {
            title : {
                text: '组织结构图'
            },
            tooltip : {
                show: false,
                trigger: 'item',
                formatter: "{b}: {c}"
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : false,
            series : [
                {
                    name:'树图',
                    type:'tree',
                    orient: 'vertical',  // vertical horizontal
                    rootLocation: {x: '50%', y: '15%'}, // 根节点位置  {x: 'center',y: 10}
                    nodePadding: 20,
                    layerPadding:40,
                    symbol: 'rectangle',
                    borderColor:'black',
                    itemStyle: {
                        normal: {
                            color: '#fff',//节点背景色
                            label: {
                                show: true,
                                position: 'inside',
                                textStyle: {
                                    color: 'black',
                                    fontSize: 15,
                                    //fontWeight:  'bolder'
                                }
                            },
                            lineStyle: {
                                color: '#000',
                                width: 1,
                                type: 'broken' // 'curve'|'broken'|'solid'|'dotted'|'dashed'
                            }
                        },
                        emphasis: {
                            label: {
                                show: false
                            }
                        }
                    },
                    data:data
                }]//series
        }
        myChart.setOption(option);

        myChart.on('click', function(params) {
            var name = params.data.name;//点击的节点的name
            var value = params.data.value;//点击的节点的value
            //调用点击事件
            clickNode(name,value);
        });
    }


}
//得到数据
function getData(zNodes){
    var mytree=new treeMenu(zNodes)
    treeData=mytree.init(0)
    data=[]
    data.push(treeData)
    return data;
    //str=JSON.stringify(menu);
    //alert("responsing json:"+str)
}
function createTreeV(myChart,title,znodes){
    var mytree=new treeMenu(znodes)
    treeData=mytree.init(1)
    data=[]
    data.push(treeData)
    mytree.createTreeVisual(myChart,title,data)
}


//节点的点击事件
function clickNode(name,value){
    console.log("名称: "+name+" id: "+value);
}