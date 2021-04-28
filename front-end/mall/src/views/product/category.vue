<template>
  <div>
    <a-tree
      v-model="checkedKeys"
      :expanded-keys="expandedKeys"
      :auto-expand-parent="autoExpandParent"
      :selected-keys="selectedKeys"
      :tree-data="treeData"
      @expand="onExpand"
      showLine
    >
      <template v-slot:title="nodeData">
        <span>{{ nodeData.title }}</span>
        <a-button-group style="margin-left: 200px; float: right"
          ><a-button
            size="small"
            icon="plus-circle"
            title="添加同级"
          ></a-button>
          <a-button size="small" icon="share-alt" title="添加下级"></a-button>
          <a-button size="small" icon="form" title="修改"></a-button>
          <a-button
            size="small"
            @click="del(nodeData.key)"
            icon="close-circle"
            title="删除"
          ></a-button
        ></a-button-group>
      </template>
    </a-tree>
  </div>
</template>

<script>
var treeData = [];
export default {
  created() {
    this.setTreeData();
  },
  data() {
    return {
      expandedKeys: [],
      autoExpandParent: true,
      checkedKeys: [],
      selectedKeys: [],
      treeData,
    };
  },
  watch: {
    checkedKeys(val) {
      console.log("onCheck", val);
    },
  },
  methods: {
    onExpand(expandedKeys) {
      console.log("onExpand", expandedKeys);
      // if not set autoExpandParent to false, if children expanded, parent can not collapse.
      // or, you can remove all expanded children keys.
      this.expandedKeys = expandedKeys;
      this.autoExpandParent = false;
    },
    onCheck(checkedKeys) {
      console.log("onCheck", checkedKeys);
      this.checkedKeys = checkedKeys;
    },
    // onSelect(selectedKeys, info) {
    //   console.log("onSelect", info);
    //   this.selectedKeys = selectedKeys;
    // },
    setTreeData: function () {
      this.axios.get("product/category/treeList").then((response) => {
        console.log(response.data);
        if (response.data.data != null && response.data.code == 200) {
          this.treeData = this.getTreeList(response.data.data);
        }
      });
    },
    getTreeList: function (tree) {
      var treeList = [];
      tree.forEach((node) => {
        let newTree = {
          title: node.name,
          key: node.catId,
          scopedSlots: {
            title: "title",
          },
        };
        if (node.child != null) {
          newTree.children = this.getTreeList(node.child);
        }
        treeList.push(newTree);
      });
      return treeList;
    },
    del: function (id) {
      console.log(id);
      this.axios.get("product/category/canDel/" + id).then((response) => {
        if (response.data.data) {
          this.axios.delete("product/category/del/" + id).then((response) => {
            if (response.data.code == 200) {
              alert("删除成功！");
              this.router.go(0);
            } else {
              alert("删除失败！");
            }
          });
        } else {
          alert("很抱歉，该节点有子节点，不能删除！");
        }
      });
    },
  },
};
</script>