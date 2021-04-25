<template>
  <a-tree
    v-model="checkedKeys"
    :expanded-keys="expandedKeys"
    :auto-expand-parent="autoExpandParent"
    :selected-keys="selectedKeys"
    :tree-data="treeData"
    @expand="onExpand"
    @select="onSelect"
  />
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
    onSelect(selectedKeys, info) {
      console.log("onSelect", info);
      this.selectedKeys = selectedKeys;
    },
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
        };
        if (node.child != null) {
          newTree.children = this.getTreeList(node.child);
        }
        treeList.push(newTree);
      });
      return treeList;
    },
  },
};
</script>
